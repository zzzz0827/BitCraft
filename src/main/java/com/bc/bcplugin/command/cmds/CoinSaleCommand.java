package com.bc.bcplugin.command.cmds;

import com.bc.bcplugin.bitcoin.Bitcoins;
import com.bc.bcplugin.utils.Messager;
import com.bc.bcplugin.utils.NumberFormatter;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Kind : Command
 * Purpose : CoinMarketGUI 에서 비트코인을 판매하기 위한 커맨드
 * Admin Only : X
 * Last Version : 1.0
 */
public class CoinSaleCommand {

    public CoinSaleCommand(CommandSender sender, String bitcoin, boolean isSaleAll) {
        Bitcoins bitcoins = new Bitcoins(bitcoin);
        Player player = (Player) sender;
        File file = new File("plugins/BitCraft/players/" + player.getDisplayName() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        try {
            double bitcoinPrice = Double.parseDouble((String) bitcoins.getClosingPrice());

            if(!isSaleAll) {
                if (config.getInt(bitcoin) <= 0 || config.get(bitcoin) == null) {
                    Messager.sendErrorMessage(player, "해당 비트코인을 보유하고 있지 않습니다!");
                }else {
                    config.set("money", config.getInt("money") + bitcoinPrice);
                    config.set(bitcoin, config.getInt(bitcoin) - 1);
                    config.save(file);

                    Messager.sendSuccessMessage(player, "비트코인을 판매했습니다!");
                    Messager.sendMessage(player, "종류 : §6" + bitcoin +
                            " §a판매가 : §e" + NumberFormatter.money(bitcoins.getClosingPrice()));
                    Messager.sendMessage(player, "보유 금액 : §e" + NumberFormatter.money(config.getInt("money")) +
                            " §a보유 개수 : §6" + NumberFormatter.number(config.getInt(bitcoin)) + "개");
                }
            }else {
                if (config.getInt(bitcoin) <= 0 || config.get(bitcoin) == null) {
                    Messager.sendErrorMessage(player, "해당 비트코인을 보유하고 있지 않습니다!");
                }else {
                    int bitcoinAmount = config.getInt(bitcoin);

                    config.set("money", config.getInt("money") + (bitcoinAmount * bitcoinPrice));
                    config.set(bitcoin, 0);
                    config.save(file);

                    Messager.sendSuccessMessage(player, "비트코인을 전부 판매했습니다!");
                    Messager.sendMessage(player, "종류 : §6" + bitcoin +
                            " §a총 판매가 : §e" + NumberFormatter.money(bitcoinPrice * bitcoinAmount));
                    Messager.sendMessage(player, "보유 금액 : §e" + NumberFormatter.money(config.getInt("money")));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            Messager.tryCatchErrorMessage(player);
        }
    }
}
