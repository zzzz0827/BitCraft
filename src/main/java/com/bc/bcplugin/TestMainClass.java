package com.bc.bcplugin;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestMainClass {
    public static void main(String[] args) throws Exception {
        File fileDir = new File("C:\\Users\\임동우\\IdeaProjects\\BitCraftPlugin\\src\\main\\java\\com\\bc\\bcplugin\\json");
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();

        jsonObject = (JSONObject) jsonParser.parse(new FileReader(fileDir + "\\Shouter8.json"));

        System.out.println(jsonObject.get("BTC"));

    }
}
