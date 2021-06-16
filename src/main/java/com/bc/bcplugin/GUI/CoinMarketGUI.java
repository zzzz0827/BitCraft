package com.bc.bcplugin.GUI;

import com.bc.bcplugin.bitcoin.Bitcoins;
import com.bc.bcplugin.command.cmds.CoinPurchaseCommand;
import com.bc.bcplugin.command.cmds.CoinSaleCommand;
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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * 비트코인을 구매하는 GUI를 여는 코드입니다.
 */
public class CoinMarketGUI implements Listener {

    private final Inventory inv;

    public CoinMarketGUI() {
        inv = Bukkit.createInventory(null, 54, Messager.DEFAULT_PREFIX + "비트코인 §a시장");
        initializeItems();
    }

    /**
     * 인벤토리 내부 아이템을 설정합니다.
     */
    public void initializeItems() {
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BTC (비트코인)", "§e비트코인을 §a구매§e하거나 §b판매§e합니다."));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ETH (이더리움)", "§e비트코인을 §a구매§e하거나 §b판매§e합니다."));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6LTC (라이트코인)", "§e비트코인을 §a구매§e하거나 §b판매§e합니다."));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ETC (이더리움 클래식)", "§e비트코인을 §a구매§e하거나 §b판매§e합니다."));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6XRP (리플)", "§e비트코인을 §a구매§e하거나 §b판매§e합니다."));
        
        inv.setItem(53, createGuiItem(Material.PAPER, "§a구매/판매 방법",
                "§eLeft : 비트코인을 1개 구매합니다.",
                "§eRight : 비트코인을 1개 판매합니다.",
                "§eShift + Left : 보유 금액에 맞게 비트코인을 전부 구매합니다.",
                "§eShift + Right : 소유 비트코인을 전부 판매합니다."));
    }

    /**
     *
     * @param material    아이템 종류
     * @param name        아이템 이름
     * @param lore        아이템 설명
     * @return
     *
     * 인벤토리 내부 아이템 정보를 설정합니다.
     */
    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

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

        // 클릭한 아이템 없으면 리턴해서 무시
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§6BTC (비트코인)") && e.isLeftClick()) {
            new CoinPurchaseCommand(player, "BTC", false);
        }

        if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§6BTC (비트코인)") && e.isRightClick()) {
            new CoinSaleCommand(player, "BTC", false);
        }

        if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§6BTC (비트코인)") && (e.isLeftClick() && e.isShiftClick())) {
            new CoinSaleCommand(player, "BTC", true);
        }

        if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("§6BTC (비트코인)") && (e.isRightClick() && e.isShiftClick())) {
            new CoinSaleCommand(player, "BTC", true);
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