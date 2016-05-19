package me.ecminer.tos.gui;

import me.ecminer.tos.TOSPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class GUI {

    protected final Inventory gui;

    public GUI(String name, int size) {
        this.gui = Bukkit.createInventory(null, size, name);
    }

    public abstract boolean onClick(Player player, int slot, ItemStack clickedItem, ClickType clickType,
                                 InventoryType.SlotType slotType, InventoryAction action);

    public abstract void onClose(Player player);

    public abstract void onOpen(Player player);

    public final boolean isGUI(Inventory inventory) {
        return this.gui == inventory;
    }

    public final void open(Player player) {
        if (TOSPlugin.getInstance().getGuiManager().hasOpenGUI(player)) {
            TOSPlugin.getInstance().getGuiManager().getOpenGUI(player).onClose(player);
            TOSPlugin.getInstance().getGuiManager().closeOpenGUI(player);
        }
        player.openInventory(gui);
        TOSPlugin.getInstance().getGuiManager().setOpenGUI(player, this);
        onOpen(player);
    }
}
