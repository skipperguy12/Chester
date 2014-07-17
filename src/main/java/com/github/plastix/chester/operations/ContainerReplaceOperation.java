package com.github.plastix.chester.operations;

import com.github.plastix.chester.Chester;
import com.github.plastix.chester.filter.Filter;
import com.github.plastix.chester.filter.container.AbstractContainerFilter;
import com.github.plastix.chester.filter.item.AbstractItemFilter;
import com.github.plastix.chester.utils.RegionUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Bukkit;
import org.bukkit.block.Beacon;
import org.bukkit.block.BlockState;
import org.bukkit.block.BrewingStand;
import org.bukkit.entity.Horse;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ContainerReplaceOperation implements BlockOperation {
    protected Inventory bukkitInventory;
    protected List<AbstractContainerFilter> containerFilters = Lists.newArrayList();
    protected List<AbstractItemFilter> itemFilters = Lists.newArrayList();


    public ContainerReplaceOperation(Inventory bukkitInventory, Filter... filters) {
        this.bukkitInventory = bukkitInventory;

        for (Filter f : filters) {
            if (f instanceof AbstractContainerFilter)
                containerFilters.add((AbstractContainerFilter) f);
            else if (f instanceof AbstractItemFilter)
                itemFilters.add((AbstractItemFilter) f);
        }
    }

    @Override
    public void execute(Selection region) {
        Preconditions.checkArgument(region instanceof CuboidSelection, "At this time only CuboidSelections are supported for a ContainerReplaceOperation");

        List<InventoryHolder> blocks = RegionUtils.getRegionBlocksAndEntities((CuboidSelection) region, containerFilters);
        for (InventoryHolder block : blocks) {
            Bukkit.getScheduler().runTaskLater(Chester.get(), new ContainerReplaceRunnable(block, itemFilters, bukkitInventory.getItem(12), bukkitInventory.getItem(14)), 0);
        }
    }

    class ContainerReplaceRunnable implements Runnable {
        private InventoryHolder containerBlock;
        private List<AbstractItemFilter> filters;
        private ItemStack replaceItem;
        private ItemStack newItem;

        public ContainerReplaceRunnable(InventoryHolder block, List<AbstractItemFilter> filters, ItemStack replaceItem, ItemStack newItem) {
            this.containerBlock = block;
            this.filters = filters;
            this.replaceItem = replaceItem;
            this.newItem = newItem;
        }

        @Override
        public void run() {
            for (int i = 0; i < containerBlock.getInventory().getSize(); i++) {
                if (containerBlock.getInventory().getItem(i) == null)
                    continue;
                for (AbstractItemFilter filter : filters) {
                    if (!filter.query(containerBlock.getInventory().getItem(i), replaceItem)) {
                        continue;
                    }
                    containerBlock.getInventory().setItem(i, newItem);
                }
            }
        }
    }
}
