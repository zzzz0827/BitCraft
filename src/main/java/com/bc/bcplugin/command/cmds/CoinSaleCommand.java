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

public class CoinSaleCommand {

    private static final String PLAYER_DATA_URL = "C:\\Users\\임동우\\IdeaProjects\\BitCraftPlugin\\src\\main\\java\\com\\bc\\bcplugin\\json\\";

    JSONObject jsonObject;
    JSONParser jsonParser;

    File file;
    FileWriter fileWriter;

    public CoinSaleCommand(CommandSender sender, String bitcoin, boolean isSaleAll) {
        Bitcoins bitcoins = new Bitcoins(bitcoin);
        Player player = (Player) sender;
        try {
            Long bitcoinPrice = Long.parseLong((String) bitcoins.getClosingPrice());

            file = new File(PLAYER_DATA_URL + player.getDisplayName() + ".json");
            jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));

            if(!isSaleAll) {
                if ((Long) jsonObject.get("BTC") <= 0 || jsonObject.get("BTC") == null) {
                    Messager.sendErrorMessage(player, "해당 비트코인을 보유하고 있지 않습니다!");
                }else {
                    jsonObject.put("money", (Long) jsonObject.get("money") + bitcoinPrice);
                    jsonObject.put(bitcoin, new Integer((int) ((Long) jsonObject.get(bitcoin) - 1)));

                    fileWriter = new FileWriter(file);

                    fileWriter.write(jsonObject.toJSONString());
                    fileWriter.flush();

                    Messager.sendSuccessMessage(player, "비트코인을 판매했습니다!");
                    Messager.sendMessage(player, "종류 : §6" + bitcoin + " §a판매가 : §e" + bitcoins.getClosingPrice() +
                            " §a보유 금액 : §e" + jsonObject.get("money") + " §a보유 개수 : §6" + jsonObject.get(bitcoin) + "개");

                    fileWriter.close();
                }
            }else {
                if ((Long) jsonObject.get("BTC") <= 0 || jsonObject.get("BTC") == null) {
                    Messager.sendErrorMessage(player, "해당 비트코인을 보유하고 있지 않습니다!");
                }else {
                    Long bitcoinAmount = (Long) jsonObject.get(bitcoin);

                    jsonObject.put("money", (Long) jsonObject.get("money") + (bitcoinPrice * bitcoinAmount));
                    jsonObject.put(bitcoin, new Integer(0));

                    fileWriter = new FileWriter(file);

                    fileWriter.write(jsonObject.toJSONString());
                    fileWriter.flush();

                    Messager.sendSuccessMessage(player, "비트코인을 전부 판매했습니다!");
                    Messager.sendMessage(player, "종류 : §6" + bitcoin + " §a총 판매가 : §e" + (bitcoinPrice * bitcoinAmount) +
                            " §a보유 금액 : §e" + jsonObject.get("money"));

                    fileWriter.close();
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            Messager.tryCatchErrorMessage(player);
        }
    }
}
