package me.ecminer.tos.utilities;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.ecminer.tos.TOSPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

import static me.ecminer.tos.utilities.ReflectionUtils.*;

public class BookUtils {

    public static void openBookGUI(Player player, List<String> lines) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.setPages(lines);
        book.setItemMeta(meta);

        int handSlot = player.getInventory().getHeldItemSlot();
        ItemStack hand = player.getInventory().getItem(handSlot);

        player.setItemInHand(book);
        sendBookPacket(player);

        Bukkit.getScheduler().scheduleSyncDelayedTask(TOSPlugin.getInstance(), new Runnable() {

            @Override
            public void run() {
                player.getInventory().setItem(handSlot, hand);
            }

        }, 1);
    }

    private static void sendBookPacket(Player player) {
        Object nmsPlayer = invokeMethod(player, "getHandle", null, null);
        Object playerConnection = get(nmsPlayer, "playerConnection");
        Object packetDataSerializer =
                newInstance(getNMSClass("PacketDataSerializer"), new Class<?>[]{ByteBuf.class}, new Object[]{
                        Unpooled.buffer()});
        if (packetDataSerializer != null) {
            Object packet = newInstance(getNMSClass("PacketPlayOutCustomPayload"),
                    new Class<?>[]{String.class, packetDataSerializer.getClass()},
                    new Object[]{"MC|BOpen", packetDataSerializer});
            invokeMethod(playerConnection, "sendPacket", new Class<?>[]{getNMSClass("Packet")}, new Object[]{packet});
        }
    }

}
