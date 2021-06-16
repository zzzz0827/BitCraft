package com.bc.bcplugin.command.cmds;

import com.bc.bcplugin.utils.Messager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class MoneyCheckCommand {

    private static final String PLAYER_DATA_URL = "C:\\Users\\임동우\\IdeaProjects\\BitCraftPlugin\\src\\main\\java\\com\\bc\\bcplugin\\json\\";

    JSONParser jsonParser;
    JSONObject jsonObject;

    Object UUID;
    Object username;
    Object money;

    public MoneyCheckCommand(CommandSender sender) {
        Player player = (Player) sender;
        try {
            File file = new File(PLAYER_DATA_URL + player.getDisplayName() + ".json");
            if(file.exists()) {
                jsonParser = new JSONParser();
                jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));

                setUUID(jsonObject.get("UUID"));
                setUsername(jsonObject.get("name"));
                setMoney(jsonObject.get("money"));

                Messager.sendMessage(player, "§a현재 보유 금액 : §e" + getMoney());
            }else {
                Messager.sendErrorMessage(player, "§b" + username + " §c은(는) 존재하지 않습니다!");
            }
        }catch (Exception e) {
            e.printStackTrace();
            Messager.tryCatchErrorMessage(player);
        }
    }

    public Object getUUID() {
        return UUID;
    }

    public void setUUID(Object UUID) {
        this.UUID = UUID;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public Object getMoney() {
        return money;
    }

    public void setMoney(Object money) {
        this.money = money;
    }
}
