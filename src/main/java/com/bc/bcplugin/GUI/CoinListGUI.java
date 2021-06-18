package com.bc.bcplugin.GUI;

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
 * Kind : GUI
 * Purpose : 비트코인의 종류를 확인하는 GUI를 설정하는 코드
 * Admin Only : X
 * Last Version : 1.0
 */
public class CoinListGUI implements Listener {

    private final Inventory inv;

    public CoinListGUI() {
        inv = Bukkit.createInventory(null, 27, Messager.DEFAULT_PREFIX + "비트코인 §a종류");
        initializeItems();
    }

    /**
     * 인벤토리 내부 아이템을 설정합니다.
     */
    public void initializeItems() {
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BTC (비트코인)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ETH (이더리움)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6LTC (라이트코인)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ETC (이더리움 클래식)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6XRP (리플)"));
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
    public void onOpenToolInventory(com.bc.bcplugin.GUI.OpenCoinListGUIEvent oclie) {
        oclie.ent.openInventory(inv);
    }

    // 아이템 클릭
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != inv) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // 클릭한 아이템 없으면 리턴해서 무시
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        final Player p = (Player) e.getWhoClicked();
    }

    // 드래그 캔슬
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory() == inv) {
            e.setCancelled(true);
        }
    }

    public Inventory getInv() {
        return inv;
    }
}