package com.bc.bcplugin;

import com.bc.bcplugin.GUI.CoinAboutGUI;
import com.bc.bcplugin.GUI.CoinListGUI;
import com.bc.bcplugin.GUI.CoinMarketGUI;
import com.bc.bcplugin.command.Commands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    private static final String JSON_URL = "C:\\Users\\임동우\\IdeaProjects\\BitCraftPlugin\\src\\main\\java\\com\\bc\\bcplugin\\json\\";

    @Override
    public void onEnable() {
        File fileDir = new File("C:\\Users\\임동우\\IdeaProjects\\BitCraftPlugin\\src\\main\\java\\com\\bc\\bcplugin\\json");

        getLogger().info("DWPlugin is running now!");
        getLogger().info("Have a fun during play this plugin.");

        Bukkit.getPluginCommand("bc").setExecutor(commands);

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new CoinListGUI(), this);
        getServer().getPluginManager().registerEvents(new CoinAboutGUI(), this);
        getServer().getPluginManager().registerEvents(new CoinMarketGUI(), this);
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
    public static void playerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        JSONObject jsonObject = new JSONObject();

        try {
            File file = new File(JSON_URL + player.getDisplayName() + ".json");

            if(!file.exists()) {
                FileWriter fileWriter = new FileWriter(file);

                jsonObject.put("money", new Integer(50000));
                jsonObject.put("name", player.getDisplayName());
                jsonObject.put("UUID", String.valueOf(player.getUniqueId()));

                fileWriter.write(jsonObject.toJSONString());
                fileWriter.flush();

                fileWriter.close();
            }
        }catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
