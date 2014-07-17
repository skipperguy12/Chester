package com.github.plastix.chester.utils;

import com.github.plastix.chester.filter.Filter;
import com.github.plastix.chester.filter.container.AbstractContainerFilter;
import com.google.common.collect.Lists;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.Vector2D;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.StorageMinecart;

import java.util.ArrayList;
import java.util.List;

/**
 * Utils for finding blocks in regions
 */
public class RegionUtils {

    public static List<BlockState> getRegionBlocksAndEntities(CuboidSelection region, AbstractContainerFilter... filters) {
        List<BlockState> entities = Lists.newArrayList();
        try {
            for (Vector2D chunk : region.getRegionSelector().getRegion().getChunks()) {
                Chunk bukkitChunk = region.getWorld().getChunkAt(chunk.getBlockX(), chunk.getBlockZ());
                for (BlockState entity : bukkitChunk.getTileEntities()) {
                    if (Math.abs(entity.getX()) > Math.abs(region.getMinimumPoint().getBlockX())
                        || Math.abs(entity.getZ()) > Math.abs(region.getMaximumPoint().getBlockZ()))
                        continue;
                    if (entity instanceof StorageMinecart || entity instanceof HopperMinecart)
                        entities.add(entity);
                }
            }
        } catch (IncompleteRegionException e) {
            e.printStackTrace();
        }

        List<Block> blocks = getRegionBlocks(region, filters);
        for (Block block : blocks) {
            if (entities.contains(block.getState()))
                continue;
            entities.add(block.getState());
        }

        return entities;
    }

    public static List<Block> getRegionBlocks(CuboidSelection region, AbstractContainerFilter... filters) {
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
    public static List<Block> getRegionBlocks(World world, Location pos1, Location pos2, AbstractContainerFilter... filters) {
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

    /**
     * Find all blocks of certain type in a cuboid region.
     *
     * @param world The world of the selection
     * @param pos1  position 1 of the selection
     * @param pos2  position 2 of the selection
     * @param query the material to find in the region
     * @return a list of every block that matches the query in the region
     */
    public static List<Block> findBlocksInRegion(World world, Location pos1, Location pos2, Material query) {
        List<Block> blocks = getRegionBlocks(world, pos1, pos2);

        List<Material> queryList = new ArrayList<Material>();
        queryList.add(query);

        return findBlocksInList(blocks, queryList);
    }

    /**
     * Find all blocks of certain type in a cuboid region.
     *
     * @param world The world of the selection
     * @param pos1  position 1 of the selection
     * @param pos2  position 2 of the selection
     * @param query the list of materials to find in the region
     * @return a list of every block that matches the query in the region
     */
    public static List<Block> findBlocksInRegion(World world, Location pos1, Location pos2, List<Material> query) {
        List<Block> blocks = getRegionBlocks(world, pos1, pos2);
        return findBlocksInList(blocks, query);
    }

    /**
     * Find all matching blocks in a list.
     *
     * @param search The list to find the blocks in
     * @param query  The types of blocks to find
     * @return a list of every block that matches the query
     */
    public static List<Block> findBlocksInList(List<Block> search, List<Material> query) {
        List<Block> results = new ArrayList<Block>();

        for (Block block : search) {
            if (query.contains(block.getType())) results.add(block);
        }
        return results;
    }
}
