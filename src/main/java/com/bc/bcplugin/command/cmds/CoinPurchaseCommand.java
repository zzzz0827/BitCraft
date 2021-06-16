package com.bc.bcplugin.command.cmds;

import com.bc.bcplugin.bitcoin.Bitcoins;
import com.bc.bcplugin.utils.Messager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CoinPurchaseCommand {

    private static final String PLAYER_DATA_URL = "C:\\Users\\임동우\\IdeaProjects\\BitCraftPlugin\\src\\main\\java\\com\\bc\\bcplugin\\json\\";

    JSONObject jsonObject;
    JSONParser jsonParser;

    File file;
    FileWriter fileWriter;

    public CoinPurchaseCommand(CommandSender sender, String bitcoin, boolean isPurchaseAll) {
        Bitcoins bitcoins = new Bitcoins(bitcoin);
        Player player = (Player) sender;
        try {
            Long bitcoinPrice = Long.parseLong((String) bitcoins.getClosingPrice());

            file = new File(PLAYER_DATA_URL + player.getDisplayName() + ".json");
            jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));

            if(!isPurchaseAll) {
                if ((Long) jsonObject.get("money") < bitcoinPrice) {
                    Messager.sendErrorMessage(player, "보유 자산이 부족합니다!");
                    Messager.sendMessage(player, "부족한 금액 : §e" + (bitcoinPrice - (Long) jsonObject.get("money")));
                }else {
                    jsonObject.put("money", (Long) jsonObject.get("money") - bitcoinPrice);
                    if (jsonObject.get(bitcoin) != null) {
                        jsonObject.put(bitcoin, new Integer((int) ((Long) jsonObject.get(bitcoin) + 1)));
                    }
                    jsonObject.computeIfAbsent(bitcoin, k -> new Integer(1));

                    fileWriter = new FileWriter(file);

                    fileWriter.write(jsonObject.toJSONString());
                    fileWriter.flush();

                    Messager.sendSuccessMessage(player, "비트코인을 구매했습니다!");
                    Messager.sendMessage(player, "종류 : §6" + bitcoin + " §a구매가 : §e" + bitcoins.getClosingPrice() +
                            " §a보유 금액 : §e" + jsonObject.get("money") + " §a보유 개수 : §6" + jsonObject.get(bitcoin) + "개");

                    fileWriter.close();
                }
            }else {
                if ((Long) jsonObject.get("money") < bitcoinPrice) {
                    Messager.sendErrorMessage(player, "보유 자산이 부족합니다!");
                    Messager.sendMessage(player, "부족한 금액 : §e" + (bitcoinPrice - (Long) jsonObject.get("money")));
                }else {
                    jsonObject.put("money", (Long) jsonObject.get("money") - bitcoinPrice);
                    if (jsonObject.get(bitcoin) != null) {
                        jsonObject.put(bitcoin, new Integer((int) ((Long) jsonObject.get(bitcoin) + 1)));
                    }
                    jsonObject.computeIfAbsent(bitcoin, k -> new Integer(1));

                    fileWriter = new FileWriter(file);

                    fileWriter.write(jsonObject.toJSONString());
                    fileWriter.flush();

                    Messager.sendSuccessMessage(player, "비트코인을 구매했습니다!");
                    Messager.sendMessage(player, "종류 : §6" + bitcoin + " §a구매가 : §e" + bitcoins.getClosingPrice() +
                            " §a보유 금액 : §e" + jsonObject.get("money") + " §a보유 개수 : §6" + jsonObject.get(bitcoin) + "개");

                    fileWriter.close();
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            Messager.tryCatchErrorMessage(player);
        }
    }
}
