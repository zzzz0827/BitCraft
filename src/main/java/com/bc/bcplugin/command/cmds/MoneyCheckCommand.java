package com.bc.bcplugin.command.cmds;

import com.bc.bcplugin.utils.Messager;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Kind : Command
 * Purpose : 플레이어의 현재 자산을 확인하는 커맨드
 * Admin Only : X
 * Last Version : 1.0
 */
public class MoneyCheckCommand {

    public MoneyCheckCommand(CommandSender sender) {
        Player player = (Player) sender;
        File file = new File("plugins/BitCraft/players/" + player.getDisplayName() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        try {
            Messager.sendLine(player);
            Messager.sendMessage(player, "§a현재 보유 금액 : §e" + config.getString("money"));
        }catch (Exception e) {
            e.printStackTrace();
            Messager.tryCatchErrorMessage(player);
        }
    }
}
