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

/**
 * Kind : Command
 * Purpose : 플레이어의 자산을 감소시키는 커맨드
 * Admin Only : X
 * Last Version : 1.0
 */
public class MoneyReduceCommand {

    Object prevMoney;
    Object nowMoney;

    public MoneyReduceCommand(CommandSender sender, String username, String money) {
        Player player = (Player) sender;
        File file = new File("plugins/BitCraft/players/" + username + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            if (file.exists() && player.isOp()) {
                prevMoney = NumberFormatter.money(config.getInt("money"));

                int resultMoney = config.getInt("money") - Integer.parseInt(money);
                if(resultMoney < 0) {
                    Messager.sendErrorMessage(player, "차감 시 금액이 0보다 적습니다!");
                }else {
                    config.set("money", resultMoney);
                    config.save(file);

                    nowMoney = NumberFormatter.money(config.getInt("money"));

                    Messager.sendSuccessMessage(player, "성공적으로 차감되었습니다!");
                    Messager.sendMessage(player, "기존 금액 : §e" + prevMoney + " §a차감 후 금액 : §e" + nowMoney);
                }
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
