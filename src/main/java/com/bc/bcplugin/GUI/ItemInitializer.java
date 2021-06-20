package com.bc.bcplugin.GUI;

import com.bc.bcplugin.utils.Messager;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * GUI에 사용되는 모든 아이템을 제작하는 코드입니다.
 */
public class ItemInitializer {

    private final Inventory inv;

    public ItemInitializer(Inventory inv, boolean isWriteLore, int page, String... lore) {
        this.inv = inv;
        if (!isWriteLore && page == 1) initializeItems();
        else if (!isWriteLore && page == 2) initializeItemsPage2();
        else if (isWriteLore && page == 1) initializeItemsWithLore(lore);
        else if (isWriteLore && page == 2) initializeItemsWithLorePage2(lore);
    }

    public void initializeItems() {
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BTC (비트코인)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ETH (이더리움)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6LTC (라이트코인)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ETC (이더리움 클래식)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6XRP (리플)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BCH (비트코인 캐시)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6QTUM (퀸텀)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BTG (비트코인 골드)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6EOS (이오스)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ICX (아이콘)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6TRX (트론)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ELF (엘프)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6OMG (오미세고)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6KNC (카이버 네트워크)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6GLM (골렘)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ZIL (질리카)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6WAXP (왁스)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6POWR (파워렛저)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6LRC (루프링)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6STEEM (스팀)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6STRAX (스트라티스)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ZRX (제로엑스)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6REP (어거)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6XEM (넴)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6SNT (스테이터스네트워크토큰)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ADA (아다)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BAT (베이직어텐션토큰)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6THETA (쎼타토큰)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6LOOM (룸네트워크)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6WAVES (웨이브)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6LINK (체인링크)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ENJ (엔진코인)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6VET (비체인)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6MTL (메탈)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6IOST (아이오에스티)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6QKC (쿼크체인)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BSV (비트코인에스브이)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ORBS (오브스)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6TFUEL (쎄타퓨엘)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ANKR (앵커)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6LAMB (람다)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6CRO (그립토닷컴체인)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6MBL (무비블록)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6FCT (피르마체인)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6SXP (스와이프)"));

        inv.setItem(45, createGuiItem(Material.WRITABLE_BOOK, "§a이전 페이지"));
        inv.setItem(49, createGuiItem(Material.GOLD_INGOT, Messager.DEFAULT_PREFIX, "§eMade By : §b임동우"));
        inv.setItem(53, createGuiItem(Material.WRITABLE_BOOK, "§a다음 페이지"));
    }

    public void initializeItemsPage2() {
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6COS (코스모스)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BASIC (베이직어텐션토큰)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6HIVE (하이브)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BORA (보라)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6AERGO (아르고)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6SRM (세럼)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6QTCON (퀴즈톡)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6SAND (샌드박스)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6OBSR (옵저버)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6AQT (알파쿼크)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6MANA (디센트럴랜드)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6PUNDIX (펀디엑스)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6CHZ (칠리즈)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6XLM (스텔라루멘)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BTT (비트토렌트)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ONT (온톨로지)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6META (메타디움)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ONG (온톨로지가스)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6JST (저스트)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6XTZ (테조스)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6MLK (밀크)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6DOT (폴카닷)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ATOM (코스모스)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6SSX (썸씽)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BCHA (비트코인캐시에이비씨)"));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6DOGE (도지코인)"));

        inv.setItem(45, createGuiItem(Material.WRITABLE_BOOK, "§a이전 페이지"));
        inv.setItem(49, createGuiItem(Material.GOLD_INGOT, Messager.DEFAULT_PREFIX, "§eMade By : §b임동우"));
        inv.setItem(53, createGuiItem(Material.WRITABLE_BOOK, "§a다음 페이지"));
    }

    public void initializeItemsWithLore(String... lore) {
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BTC (비트코인)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ETH (이더리움)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6LTC (라이트코인)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ETC (이더리움 클래식)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6XRP (리플)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BCH (비트코인 캐시)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6QTUM (퀸텀)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BTG (비트코인 골드)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6EOS (이오스)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ICX (아이콘)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6TRX (트론)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ELF (엘프)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6OMG (오미세고)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6KNC (카이버 네트워크)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6GLM (골렘)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ZIL (질리카)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6WAXP (왁스)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6POWR (파워렛저)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6LRC (루프링)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6STEEM (스팀)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6STRAX (스트라티스)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ZRX (제로엑스)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6REP (어거)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6XEM (넴)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6SNT (스테이터스네트워크토큰)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ADA (아다)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BAT (베이직어텐션토큰)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6THETA (쎼타토큰)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6LOOM (룸네트워크)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6WAVES (웨이브)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6LINK (체인링크)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ENJ (엔진코인)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6VET (비체인)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6MTL (메탈)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6IOST (아이오에스티)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6QKC (쿼크체인)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BSV (비트코인에스브이)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ORBS (오브스)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6TFUEL (쎄타퓨엘)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ANKR (앵커)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6LAMB (람다)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6CRO (그립토닷컴체인)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6MBL (무비블록)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6FCT (피르마체인)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6SXP (스와이프)", lore));

        inv.setItem(45, createGuiItem(Material.WRITABLE_BOOK, "§a이전 페이지"));
        inv.setItem(49, createGuiItem(Material.GOLD_INGOT, Messager.DEFAULT_PREFIX, "§eMade By : §b임동우"));
        inv.setItem(53, createGuiItem(Material.WRITABLE_BOOK, "§a다음 페이지"));
    }

    public void initializeItemsWithLorePage2(String... lore) {
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6COS (코스모스)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BASIC (베이직어텐션토큰)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6HIVE (하이브)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BORA (보라)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6AERGO (아르고)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6SRM (세럼)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6QTCON (퀴즈톡)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6SAND (샌드박스)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6OBSR (옵저버)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6AQT (알파쿼크)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6MANA (디센트럴랜드)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6PUNDIX (펀디엑스)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6CHZ (칠리즈)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6XLM (스텔라루멘)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BTT (비트토렌트)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ONT (온톨로지)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6META (메타디움)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ONG (온톨로지가스)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6JST (저스트)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6XTZ (테조스)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6MLK (밀크)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6DOT (폴카닷)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6ATOM (코스모스)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6SSX (썸씽)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6BCHA (비트코인캐시에이비씨)", lore));
        inv.addItem(createGuiItem(Material.GOLD_NUGGET, "§6DOGE (도지코인)", lore));

        inv.setItem(45, createGuiItem(Material.WRITABLE_BOOK, "§a이전 페이지"));
        inv.setItem(49, createGuiItem(Material.GOLD_INGOT, Messager.DEFAULT_PREFIX, "§eMade By : §b임동우"));
        inv.setItem(53, createGuiItem(Material.WRITABLE_BOOK, "§a다음 페이지"));
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);

        return item;
    }
}
