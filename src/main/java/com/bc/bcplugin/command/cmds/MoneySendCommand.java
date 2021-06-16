package com.bc.bcplugin.command.cmds;

import com.bc.bcplugin.utils.Messager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MoneySendCommand {

    private static final String PLAYER_DATA_URL = "C:\\Users\\임동우\\IdeaProjects\\BitCraftPlugin\\src\\main\\java\\com\\bc\\bcplugin\\json\\";

    JSONParser jsonParser;
    JSONObject giverJson;
    JSONObject recipientJson;

    Object sendMoney;
    Object nowMoney;

    File giverFile;
    File recipientFile;

    FileWriter giverFileWriter;
    FileWriter recipientFileWriter;

    public MoneySendCommand(CommandSender sender, String username, String money) {
        Player player = (Player) sender;
        try {
            giverFile = new File(PLAYER_DATA_URL + player.getDisplayName() + ".json");
            recipientFile = new File(PLAYER_DATA_URL + username + ".json");

            if (username == player.getDisplayName()) {
                Messager.sendErrorMessage(player, "자신에게 이체 할 수 없습니다!");
            }else if (giverFile.exists() && recipientFile.exists()) {
                jsonParser = new JSONParser();
                giverJson = (JSONObject) jsonParser.parse(new FileReader(giverFile));
                recipientJson = (JSONObject) jsonParser.parse(new FileReader(recipientFile));
                sendMoney = giverJson.get("money");

                int giverMoney = new Integer((int) ((Long) giverJson.get("money") - Long.parseLong(money)));
                int recipientMoney = new Integer((int) ((Long) recipientJson.get("money") + Long.parseLong(money)));

                if(giverMoney < 0) {
                    Messager.sendErrorMessage(player, "이체하려는 금액이 보유 금액보다 많습니다!");
                    return;
                }else {
                    giverJson.put("money", new Integer(giverMoney));
                    recipientJson.put("money", new Integer(recipientMoney));

                    giverFileWriter = new FileWriter(giverFile);
                    recipientFileWriter = new FileWriter(recipientFile);
                    giverFileWriter.write(giverJson.toJSONString());
                    recipientFileWriter.write(recipientJson.toJSONString());
                    giverFileWriter.flush();
                    recipientFileWriter.flush();

                    nowMoney = giverJson.get("money");

                    Messager.sendSuccessMessage(player, "성공적으로 이체되었습니다!");
                    Messager.sendMessage(player, "이체 금액 : §e" + sendMoney + " §a이체 후 금액 : §e" + nowMoney);

                    giverFileWriter.close();
                    recipientFileWriter.close();
                }
            }else if (!recipientFile.exists()){
                Messager.sendErrorMessage(player, "§b" + username + " §c은(는) 존재하지 않습니다!");
            }
        }catch (Exception e) {
            e.printStackTrace();
            Messager.tryCatchErrorMessage(player);
        }
    }
}
