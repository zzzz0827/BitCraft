package com.bc.bcplugin.GUI.have;

import com.bc.bcplugin.GUI.ItemInitializer;
import com.bc.bcplugin.GUI.about.OpenCoinAboutGUIEvent;
import com.bc.bcplugin.bitcoin.Bitcoins;
import com.bc.bcplugin.utils.Messager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class CoinHaveGUIPageTwo implements Listener {

    private final Inventory inv;

    public CoinHaveGUIPageTwo() {
        inv = Bukkit.createInventory(null, 54, Messager.DEFAULT_PREFIX + "비트코인 §a종류");
        new ItemInitializer(inv, true, 2, "§e클릭 시 보유 개수와 판매 시 총 금액을 확인할 수 있습니다.");
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }

    @EventHandler
    public void onOpenToolInventory(OpenCoinHaveGUIPageTwoEvent openGUI) {
        openGUI.ent.openInventory(inv);
    }

    // 아이템 클릭
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();
        final ItemStack clickedItem = e.getCurrentItem();

        File file = new File("plugins/BitCraft/players/" + player.getDisplayName() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        if (e.getInventory() != inv) return;

        e.setCancelled(true);

        // 클릭한 아이템 없으면 리턴해서 무시
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        String bitcoinName = clickedItem.getItemMeta().getDisplayName();

        if (bitcoinName.equalsIgnoreCase("§6BTC (비트코인)") && e.isLeftClick()) {
            Bitcoins bitcoins = new Bitcoins("BTC");
            Messager.sendLine(player);
            Messager.sendMessage(player, "종류 : §6" + bitcoinName);
            Messager.sendMessage(player, "보유 개수 §e: " + config.getInt("BTC") + "개");

            if (config.get("BTC") == null || config.getInt("BTC") == 0) {
                Messager.sendMessage(player, "§c해당 비트코인을 소유하고 있지 않습니다!");
            }else {
                Messager.sendMessage(player, "총 판매 시 가격 §e: " + config.getInt("BTC") * (int) bitcoins.getClosingPrice());
            }

            player.closeInventory();
        }

        if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§a다음 페이지") && e.isLeftClick()) {
            Messager.sendErrorMessage(player, "다음 페이지가 존재하지 않습니다!");
        }else if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§a이전 페이지") && e.isLeftClick()) {
            OpenCoinHaveGUIEvent openGUI = new OpenCoinHaveGUIEvent(player);
            Bukkit.getPluginManager().callEvent(openGUI);
        }
    }

    // 드래그 캔슬
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory() == inv) {
            e.setCancelled(true);
        }
    }
}
