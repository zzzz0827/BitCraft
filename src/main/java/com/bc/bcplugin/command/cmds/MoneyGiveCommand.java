package com.bc.bcplugin.command.cmds;

import com.bc.bcplugin.utils.Messager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MoneyGiveCommand {

    private static final String PLAYER_DATA_URL = "C:\\Users\\임동우\\IdeaProjects\\BitCraftPlugin\\src\\main\\java\\com\\bc\\bcplugin\\json\\";

    JSONParser jsonParser;
    JSONObject jsonObject;

    Object prevMoney;
    Object nowMoney;

    File file;
    FileWriter fileWriter;

    public MoneyGiveCommand(CommandSender sender, String username, String money) {
        Player player = (Player) sender;
        try {
            file = new File(PLAYER_DATA_URL + username + ".json");

            if (file.exists() && player.isOp()) {
                jsonParser = new JSONParser();
                jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
                fileWriter = new FileWriter(file);
                prevMoney = jsonObject.get("money");

                jsonObject.put("money", new Integer((int) ((Long) jsonObject.get("money") + Long.parseLong(money))));
                fileWriter.write(jsonObject.toJSONString());
                fileWriter.flush();

                nowMoney = jsonObject.get("money");

                Messager.sendSuccessMessage(player, "성공적으로 지급되었습니다!");
                Messager.sendMessage(player, "기존 금액 : §e" + prevMoney + " §a지급 후 금액 : §e" + nowMoney);

                fileWriter.close();
            }else if (!player.isOp()) {
                Messager.sendOpErrorMessage(player);
            }else if (!file.exists()){
                Messager.sendErrorMessage(player, "§b" + username + " §c은(는) 존재하지 않습니다!");
            }
        }catch (Exception e) {
            e.printStackTrace();
            Messager.tryCatchErrorMessage(player);
        }
    }

}
