package me.ecminer.tos.gui.guis;

import me.ecminer.tos.TOSPlugin;
import me.ecminer.tos.game.Game;
import me.ecminer.tos.gui.GUI;
import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.target.TargetFilter;
import me.ecminer.tos.utilities.ItemUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class TargetGUI extends GUI {

    private final Role role;
    private int selected;

    public TargetGUI(Game game, Role role) {
        super("Select your target", (int) Math.max(9, Math.ceil((double) game.getAlivePlayers().size() / 9d) * 9d));
        this.role = role;
        TargetFilter filter = game.isDay() ? role.getDayTargetFilter() : role.getNightTargetFilter();
        for (Role alive : game.getAlivePlayers()) {
            if (filter == null || filter.canTarget(alive)) {
                gui.addItem(ItemUtils.setItemMeta(playerHead(),
                        ChatColor.GRAY + (alive.isOnline() ? alive.getName() : "[" + alive.getName() + "]")));
            } else {
                gui.addItem(ItemUtils.setItemMeta(glassPane(DyeColor.RED),
                        ChatColor.GRAY + (alive.isOnline() ? alive.getName() : "[" + alive.getName() + "]")));
            }
        }
    }

    @Override
    public boolean onClick(Player player, int slot, ItemStack clickedItem, ClickType clickType,
                           InventoryType.SlotType slotType, InventoryAction action) {
        if (role.getPlayer() != null && role.getPlayer() == player && clickedItem != null) {
            if (clickedItem.getType() == Material.SKULL_ITEM) {
                select(slot);
                Role selected = getSelected();
                if (selected != null) {
                    TOSPlugin.getInstance().getTargetManager().setTarget(this.role, selected);
                    this.role.onSelectTarget(selected);
                }
            } else if (clickedItem.getType() == Material.STAINED_GLASS_PANE
                    && clickedItem.getData().getData() == DyeColor.LIGHT_BLUE.getData()) {
                Role selected = getSelected();
                if (selected != null) {
                    TOSPlugin.getInstance().getTargetManager().removeTarget(this.role);
                    this.role.onUnSelectTarget(selected);
                }
                unSelect();
            }
        }
        return true;
    }

    private void select(int slot) {
        unSelect();
        this.selected = slot;
        gui.setItem(slot, ItemUtils.setDisplayName(glassPane(DyeColor.LIGHT_BLUE),
                ChatColor.AQUA + (role.isOnline() ? role.getName() : "[" + role.getName() + "]")));
    }

    private void unSelect() {
        if (selected >= 0) {
            Role role = getSelected();
            if (role != null) {
                gui.setItem(selected, ItemUtils.setDisplayName(playerHead(),
                        ChatColor.GRAY + (role.isOnline() ? role.getName() : "[" + role.getName() + "]")));
            }
        }
    }

    private Role getSelected() {
        return role.getGame()
                .getRole(ChatColor.stripColor(
                        ItemUtils.getDisplayName(gui.getItem(selected)).replace("[", "").replace("]", "")));
    }

    private ItemStack playerHead() {
        return new ItemStack(Material.SKULL_ITEM, 1, (byte) SkullType.PLAYER.ordinal());
    }

    private ItemStack glassPane(DyeColor color) {
        return new ItemStack(Material.STAINED_GLASS_PANE, 1, color.getData());
    }

    @Override
    public void onClose(Player player) {
    }

    @Override
    public void onOpen(Player player) {
    }

    public void onPlayerLeave(Role role) {
        for (int i = 0; i < gui.getSize(); i++) {
            ItemStack item = gui.getItem(i);
            if (item != null) {
                ChatColor color;
                if (item.getType() == Material.STAINED_GLASS_PANE && item.getData().getData() == DyeColor.RED
                        .getData()) {
                    color = ChatColor.RED;
                } else if (item.getType() == Material.STAINED_GLASS_PANE
                        && item.getData().getData() == DyeColor.LIGHT_BLUE.getData()) {
                    color = ChatColor.AQUA;
                } else {
                    color = ChatColor.GRAY;
                }
                ItemUtils.setDisplayName(item, color + "[" + role.getName() + "]");
            }
        }
    }
}
