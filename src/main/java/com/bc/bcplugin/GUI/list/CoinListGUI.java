package com.bc.bcplugin.GUI.list;

import com.bc.bcplugin.GUI.ItemInitializer;
import com.bc.bcplugin.utils.Messager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Kind : GUI
 * Purpose : 비트코인의 종류를 확인하는 GUI를 설정하는 코드
 * Admin Only : X
 * Last Version : 1.0
 */
public class CoinListGUI implements Listener {

    private final Inventory inv;

    public CoinListGUI() {
        inv = Bukkit.createInventory(null, 54, Messager.DEFAULT_PREFIX + "비트코인 §a종류");
        new ItemInitializer(inv, false, 1);
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }

    @EventHandler
    public void onOpenToolInventory(OpenCoinListGUIEvent openGUI) {
        openGUI.ent.openInventory(inv);
    }

    // 아이템 클릭
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();
        final ItemStack clickedItem = e.getCurrentItem();

        if (e.getInventory() != inv) return;

        e.setCancelled(true);

        // 클릭한 아이템 없으면 리턴해서 무시
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§a다음 페이지") && e.isLeftClick()) {
            OpenCoinListGUIPageTwoEvent openGUI = new OpenCoinListGUIPageTwoEvent(player);
            Bukkit.getPluginManager().callEvent(openGUI);
        }else if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§a이전 페이지") && e.isLeftClick()) {
            Messager.sendErrorMessage(player, "이전 페이지가 존재하지 않습니다!");
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