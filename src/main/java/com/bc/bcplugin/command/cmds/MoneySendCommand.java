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
 * Purpose : 플레이어 간의 자산 이체 커맨드
 * Admin Only : X
 * Last Version : 1.0
 */
public class MoneySendCommand {

    Object sendMoney;
    Object nowMoney;

    public MoneySendCommand(CommandSender sender, String username, String money) {
        Player player = (Player) sender;
        File giverFile = new File("plugins/BitCraft/players/" + player.getDisplayName() + ".yml");
        File recipientFile = new File("plugins/BitCraft/players/" + username + ".yml");
        FileConfiguration giverConfig = YamlConfiguration.loadConfiguration(giverFile);
        FileConfiguration recipientConfig = YamlConfiguration.loadConfiguration(recipientFile);
        try {
            if (username.equals(player.getDisplayName())) {
                Messager.sendErrorMessage(player, "자신에게 이체 할 수 없습니다!");
            }else if (giverFile.exists() && recipientFile.exists()) {
                sendMoney = NumberFormatter.money(giverConfig.getInt("money"));

                int giverMoney = giverConfig.getInt("money");
                int recipientMoney = recipientConfig.getInt("money");

                if(giverMoney < Integer.parseInt(money)) {
                    Messager.sendErrorMessage(player, "이체하려는 금액이 보유 금액보다 많습니다!");
                }else {
                    giverConfig.set("money", giverMoney - Integer.parseInt(money));
                    recipientConfig.set("money", recipientMoney + Integer.parseInt(money));

                    giverConfig.save(giverFile);
                    recipientConfig.save(recipientFile);

                    nowMoney = NumberFormatter.money(giverConfig.getInt("money"));

                    Messager.sendSuccessMessage(player, "성공적으로 이체되었습니다!");
                    Messager.sendMessage(player, "이체 금액 : §e" + sendMoney + " §a이체 후 금액 : §e" + nowMoney);
                }
            }else if (!recipientFile.exists()){
                Messager.sendPlayerNotExistErrorMessage(player, username);
            }
        }catch (Exception e) {
            e.printStackTrace();
            Messager.tryCatchErrorMessage(player);
        }
    }
}
