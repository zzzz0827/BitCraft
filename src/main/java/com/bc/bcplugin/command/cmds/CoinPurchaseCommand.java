package com.bc.bcplugin.command.cmds;

import com.bc.bcplugin.bitcoin.Bitcoins;
import com.bc.bcplugin.utils.Messager;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Kind : Command
 * Purpose : CoinMarketGUI 에서 비트코인을 구매하기 위한 커맨드
 * Admin Only : X
 * Last Version : 1.0
 */
public class CoinPurchaseCommand {

    public CoinPurchaseCommand(CommandSender sender, String bitcoin, boolean isPurchaseAll) {
        Bitcoins bitcoins = new Bitcoins(bitcoin);
        Player player = (Player) sender;
        File file = new File("plugins/BitCraft/players/" + player.getDisplayName() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        try {
            Long bitcoinPrice = Long.parseLong((String) bitcoins.getClosingPrice());

            if(!isPurchaseAll) {
                if (config.getInt("money") < bitcoinPrice) {
                    Messager.sendErrorMessage(player, "보유 자산이 부족합니다!");
                    Messager.sendMessage(player, "부족한 금액 : §e" + (bitcoinPrice - config.getInt("money")));
                }else {
                    config.set(bitcoin, config.getInt(bitcoin) + 1);
                    config.set("money", config.getInt("money") - bitcoinPrice);
                    config.save(file);

                    Messager.sendSuccessMessage(player, "비트코인을 구매했습니다!");
                    Messager.sendMessage(player, "종류 : §6" + bitcoin + " §a구매가 : §e" + bitcoins.getClosingPrice() +
                            " §a보유 금액 : §e" + config.getInt("money") + " §a보유 개수 : §6" + config.getInt(bitcoin) + "개");
                }
            }else {
                int totalPurchaseAmount = 0;
                int totalPurchaseMoney = 0;
                while (true) {
                    if (config.getInt("money") < bitcoinPrice) {
                        Messager.sendErrorMessage(player, "보유 자산이 부족합니다!");
                        Messager.sendMessage(player, "부족한 금액 : §e" + (bitcoinPrice - config.getInt("money")));
                        break;
                    }else if(totalPurchaseMoney < config.getInt("money")) {
                            totalPurchaseMoney += bitcoinPrice;
                            totalPurchaseAmount++;
                    }else {
                        totalPurchaseAmount--;
                        totalPurchaseMoney -= bitcoinPrice;

                        config.set(bitcoin, config.getInt(bitcoin) + totalPurchaseAmount);
                        config.set("money", config.getInt("money") - totalPurchaseMoney);
                        config.save(file);

                        Messager.sendSuccessMessage(player, "비트코인을 전부 구매했습니다!");
                        Messager.sendMessage(player, "종류 : §6" + bitcoin + " §a구매가 : §e" + totalPurchaseMoney);
                        Messager.sendMessage(player, "§a보유 금액 : §e" + config.getInt("money") + " §a구매 개수 : §6" + totalPurchaseAmount + "개");
                        Messager.sendMessage(player, "§a보유 개수 : §6" + config.getInt(bitcoin) + "개");

                        break;
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            Messager.tryCatchErrorMessage(player);
        }
    }
}
