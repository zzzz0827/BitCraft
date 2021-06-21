package com.bc.bcplugin.command.cmds;

import com.bc.bcplugin.utils.Messager;
import com.bc.bcplugin.utils.NumberFormatter;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

/**
 * Kind : Command
 * Purpose : 플레이어의 자산을 추가하는 커맨드
 * Admin Only : O
 * Last Version : 1.0
 */
public class MoneyGiveCommand {

    Object prevMoney;
    Object nowMoney;

    public MoneyGiveCommand(CommandSender sender, String username, String money) {
        Player player = (Player) sender;
        File file = new File("plugins/BitCraft/players/" + username + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            if (player.isOp() && file.exists()) {
                prevMoney = NumberFormatter.money(config.getInt("money"));

                config.set("money", config.getInt("money") + Integer.parseInt(money));
                config.save(file);

                nowMoney = NumberFormatter.money(config.getInt("money"));

                Messager.sendSuccessMessage(player, "성공적으로 지급되었습니다!");
                Messager.sendMessage(player, "기존 금액 : §e" + prevMoney + " §a지급 후 금액 : §e" + nowMoney);

            }else if (!player.isOp()) {
                Messager.sendOpErrorMessage(player);
            }else if (!file.exists()){
                Messager.sendPlayerNotExistErrorMessage(player, username);
            }
        }catch (Exception e) {
            e.printStackTrace();
            Messager.tryCatchErrorMessage(player);
        }
    }

}
