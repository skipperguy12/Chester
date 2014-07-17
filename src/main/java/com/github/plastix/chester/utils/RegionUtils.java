package com.github.plastix.chester.utils;

import com.github.plastix.chester.filter.Filter;
import com.github.plastix.chester.filter.container.AbstractContainerFilter;
import com.google.common.collect.Lists;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.Vector2D;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.inventory.InventoryHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Utils for finding blocks in regions
 */
public class RegionUtils {

    public static List<InventoryHolder> getRegionBlocksAndEntities(CuboidSelection region, List<AbstractContainerFilter> filters) {
        List<InventoryHolder> entities = Lists.newArrayList();
        try {
            for (Vector2D chunk : region.getRegionSelector().getRegion().getChunks()) {
                Chunk bukkitChunk = region.getWorld().getChunkAt(chunk.getBlockX(), chunk.getBlockZ());
                for (Entity bukkitEntity : bukkitChunk.getEntities()) {
                    if (!(bukkitEntity instanceof Minecart))
                        continue;
//                    if (entity.getX() < region.getMinimumPoint().getBlockX() ||
//                        entity.getX() > region.getMaximumPoint().getBlockX() ||
//                        entity.getZ() < region.getMinimumPoint().getBlockY() ||
//                        entity.getZ() > region.getMaximumPoint().getBlockY())
//                        continue;
                    if (bukkitEntity instanceof StorageMinecart || bukkitEntity instanceof HopperMinecart)
                        entities.add((InventoryHolder) bukkitEntity);
                }
            }
        } catch (IncompleteRegionException e) {
            e.printStackTrace();
        }

        List<Block> blocks = getRegionBlocks(region, filters);
        System.out.println(blocks.size());
        for (Block block : blocks) {
            if (block instanceof InventoryHolder) {
                entities.add((InventoryHolder) block);
            }
        }

        return entities;
    }

    public static List<Block> getRegionBlocks(CuboidSelection region, List<AbstractContainerFilter> filters) {
        return getRegionBlocks(region.getWorld(), region.getMinimumPoint(), region.getMaximumPoint(), filters);
    }

    /**
     * Loop through blocks in a cuboid region.
     *
     * @param world The world of the selection
     * @param pos1  position 1 of the selection
     * @param pos2  position 2 of the selection
     * @return a list of all blocks in the region
     */
    public static List<Block> getRegionBlocks(World world, Location pos1, Location pos2, List<AbstractContainerFilter> filters) {
        List<Block> blocks = new ArrayList<Block>();

        for (double x = pos1.getX(); x <= pos2.getX(); x++) {
            for (double y = pos1.getY(); y <= pos2.getY(); y++) {
                for (double z = pos1.getZ(); z <= pos2.getZ(); z++) {
                    Location loc = new Location(world, x, y, z);
                    Block block = loc.getBlock();
                    for (Filter f : filters)
                        if (!f.query(block))
                            continue;
                    blocks.add(block);
                }
            }
        }

        return blocks;
    }
}
