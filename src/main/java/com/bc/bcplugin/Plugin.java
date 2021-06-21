package com.bc.bcplugin;

import com.bc.bcplugin.GUI.about.CoinAboutGUI;
import com.bc.bcplugin.GUI.about.CoinAboutGUIPageTwo;
import com.bc.bcplugin.GUI.admin.PlayerManagementGUI;
import com.bc.bcplugin.GUI.have.CoinHaveGUI;
import com.bc.bcplugin.GUI.have.CoinHaveGUIPageTwo;
import com.bc.bcplugin.GUI.list.CoinListGUI;
import com.bc.bcplugin.GUI.list.CoinListGUIPageTwo;
import com.bc.bcplugin.GUI.market.CoinMarketGUI;
import com.bc.bcplugin.GUI.market.CoinMarketGUIPageTwo;
import com.bc.bcplugin.command.Commands;
import com.bc.bcplugin.utils.Messager;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import java.io.File;

/**
 * @author 임동우
 * @version 1.0
 * @since 2021-05-10
 *
 * 빗썸 API (https://apidocs.bithumb.com/docs/ticker)를 참조한 마인크래프트 비트코인 플러그인입니다.
 * It's the minecraft plugin that referenced to Bit Sum
 */
public class Plugin extends JavaPlugin implements Listener {

    private final Commands commands = new Commands(this);

    @SneakyThrows
    @Override
    public void onEnable() {

        getLogger().info("DWPlugin is running now!");
        getLogger().info("Have a fun during play this plugin.");

        Bukkit.getPluginCommand("bc").setExecutor(commands);

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new CoinListGUI(), this);
        getServer().getPluginManager().registerEvents(new CoinListGUIPageTwo(), this);
        getServer().getPluginManager().registerEvents(new CoinAboutGUI(), this);
        getServer().getPluginManager().registerEvents(new CoinAboutGUIPageTwo(), this);
        getServer().getPluginManager().registerEvents(new CoinMarketGUI(), this);
        getServer().getPluginManager().registerEvents(new CoinMarketGUIPageTwo(), this);
        getServer().getPluginManager().registerEvents(new CoinHaveGUI(), this);
        getServer().getPluginManager().registerEvents(new CoinHaveGUIPageTwo(), this);
        getServer().getPluginManager().registerEvents(new PlayerManagementGUI(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("DWPlugin has been terminated!");
        getLogger().info("Hope you have been played this plugin funny.");
    }

    /**
     * 플레이어가 서버 입장시 발생하는 이벤트 핸들러입니다.
     * 첫 입장시 com.dw.plugin.json 패키지 안에 (DisplayName).json 파일이 생성됩니다.
     */
    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        File file = new File("plugins/BitCraft/players/" + player.getDisplayName() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            if(!file.exists()) {
                config.set("name", player.getDisplayName());
                config.set("money", 50000);
                e.setJoinMessage(Messager.DEFAULT_PREFIX + "§b" + player.getDisplayName() + "§a님이 §c처음으로 §e서버에 §a입장했습니다! §c 모두 환영해줍시다!");
            }
            config.save(file);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
