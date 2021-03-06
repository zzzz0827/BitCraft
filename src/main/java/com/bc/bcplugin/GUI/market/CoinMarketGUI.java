package com.bc.bcplugin.GUI.market;

import com.bc.bcplugin.GUI.ItemInitializer;
import com.bc.bcplugin.command.cmds.CoinPurchaseCommand;
import com.bc.bcplugin.command.cmds.CoinSaleCommand;
import com.bc.bcplugin.utils.Messager;
import com.bc.bcplugin.utils.StringExtractor;
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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Kind : GUI
 * Purpose : 비트코인의을 구매/판매가 가능한 GUI를 설정하는 코드
 * Admin Only : X
 * Last Version : 1.0
 */
public class CoinMarketGUI implements Listener {

    private final Inventory inv;

    public CoinMarketGUI() {
        inv = Bukkit.createInventory(null, 54, Messager.DEFAULT_PREFIX + "비트코인 §a시장");
        new ItemInitializer(inv, true, 1, "§e비트코인을 §a구매§e하거나 §b판매§e합니다.");
        inv.setItem(49, createGuiItem(
                "§eLeft : 비트코인을 1개 구매합니다.",
                "§eRight : 비트코인을 1개 판매합니다.",
                "§eShift + Left : 보유 금액에 맞게 비트코인을 전부 구매합니다.",
                "§eShift + Right : 소유 비트코인을 전부 판매합니다."));
    }

    protected ItemStack createGuiItem(final String... lore) {
        final ItemStack item = new ItemStack(Material.PAPER, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§a구매/판매 방법");
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);

        return item;
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }

    @EventHandler
    public void onOpenToolInventory(OpenCoinMarketGUIEvent openGUI) {
        openGUI.ent.openInventory(inv);
    }

    // 아이템 클릭
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (e.getInventory() != inv) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        try {
            // 클릭한 아이템 없으면 리턴해서 무시
            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

            String bitcoin = StringExtractor.extractAlphabet(clickedItem.getItemMeta().getDisplayName());

            if (clickedItem.getType() == Material.GOLD_NUGGET) {
                if (e.isLeftClick() && !e.isShiftClick()) new CoinPurchaseCommand(player, bitcoin, false);
                else if (e.isLeftClick() && e.isShiftClick()) new CoinPurchaseCommand(player, bitcoin, true);
                else if (e.isRightClick() && !e.isShiftClick()) new CoinSaleCommand(player, bitcoin, false);
                else if (clickedItem.getType() == Material.GOLD_NUGGET) new CoinSaleCommand(player, bitcoin, true);
            }

            if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§a다음 페이지") && e.isLeftClick()) {
                OpenCoinMarketGUIPageTwoEvent openGUI = new OpenCoinMarketGUIPageTwoEvent(player);
                Bukkit.getPluginManager().callEvent(openGUI);
            } else if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§a이전 페이지") && e.isLeftClick()) {
                Messager.sendErrorMessage(player, "이전 페이지가 존재하지 않습니다!");
            }

        }catch (Exception ex) {
            Messager.tryCatchErrorMessage(player);
            ex.printStackTrace();
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