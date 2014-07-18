package com.github.plastix.chester.utils;

import com.github.plastix.chester.filter.container.AbstractBlockFilter;
import com.github.plastix.chester.filter.entity.AbstractEntityFilter;
import com.google.common.collect.Lists;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.Vector2D;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.*;
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

    public static List<InventoryHolder> getRegionBlocksAndEntities(CuboidSelection region, List<AbstractBlockFilter> blockFilters, List<AbstractEntityFilter> entityFilters) {
        List<InventoryHolder> inventories = Lists.newArrayList();

        try {
            for (Vector2D chunk : region.getRegionSelector().getRegion().getChunks()) {
                Chunk bukkitChunk = region.getWorld().getChunkAt(chunk.getBlockX(), chunk.getBlockZ());
                for (Entity bukkitEntity : bukkitChunk.getEntities()) {
                    Location loc = bukkitEntity.getLocation();
                    if (loc.getX() < region.getMinimumPoint().getBlockX() ||
                        loc.getX() > region.getMaximumPoint().getBlockX() ||
                        loc.getZ() < region.getMinimumPoint().getBlockZ() ||
                        loc.getZ() > region.getMaximumPoint().getBlockZ())
                        continue;
                    for (AbstractEntityFilter f : entityFilters) {
                        if (f.query(bukkitEntity))
                            inventories.add((InventoryHolder) bukkitEntity);
                    }
                }
            }
        } catch (IncompleteRegionException e) {
            e.printStackTrace();
        }

        List<Block> blocks = getRegionBlocks(region, blockFilters);
        System.out.println(blocks.size());
        for (Block block : blocks) {
            BlockState state = block.getState();
            inventories.add((InventoryHolder) state);
        }

        return inventories;
    }

    public static List<Block> getRegionBlocks(CuboidSelection region, List<AbstractBlockFilter> filters) {
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
    public static List<Block> getRegionBlocks(World world, Location pos1, Location pos2, List<AbstractBlockFilter> filters) {
        List<Block> blocks = new ArrayList<>();

        for (double x = pos1.getX(); x <= pos2.getX(); x++) {
            for (double y = pos1.getY(); y <= pos2.getY(); y++) {
                for (double z = pos1.getZ(); z <= pos2.getZ(); z++) {
                    Location loc = new Location(world, x, y, z);
                    Block block = loc.getBlock();
                    for (AbstractBlockFilter f : filters) {
                        if (f.query(block)) {
                            blocks.add(block);
                            break;
                        }
                    }
                }
            }
        }

        return blocks;
    }
}
