package com.bc.bcplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.*;

public class Messager {

    private Messager() {
    }

    public static final String DEFAULT_PREFIX = "§e<§6Bitⓒraft§e> ";
    private static final ConsoleCommandSender console = Bukkit.getConsoleSender();

    public static List<String> asList(String... strings) {
        return new ArrayList<>(Arrays.asList(strings));
    }

    // sendMessage 위에만 사용 할 것
    public static void sendLine(CommandSender cs) {
        cs.sendMessage("§6=============================================");
    }

    public static void sendMessage(CommandSender cs, String str) {
        cs.sendMessage(DEFAULT_PREFIX + ChatColor.GREEN + str);
    }

    public static void sendSuccessMessage(CommandSender cs, String str) {
        sendLine(cs);
        cs.sendMessage(DEFAULT_PREFIX + ChatColor.AQUA + str);
    }

    public static void sendErrorMessage(CommandSender cs, String str) {
        sendLine(cs);
        cs.sendMessage(DEFAULT_PREFIX + ChatColor.RED + str);
    }

    public static void sendOpErrorMessage(CommandSender cs) {
        sendLine(cs);
        cs.sendMessage(DEFAULT_PREFIX + ChatColor.RED + "당신은 관리자가 아닙니다.");
    }

    public static void broadcastMassage(String str) {
        Bukkit.broadcastMessage(DEFAULT_PREFIX + "§b" + str);
    }

    public static void broadcastErrorMessage(String str) {
        Bukkit.broadcastMessage(ChatColor.WHITE + "[" + ChatColor.RED + "!" + ChatColor.WHITE + "] " + ChatColor.RED + str);
    }

    public static void tryCatchErrorMessage(CommandSender cs) {
        sendLine(cs);
        cs.sendMessage(DEFAULT_PREFIX + ChatColor.RED + "예기치 못한 오류가 발생했습니다.");
        cs.sendMessage(DEFAULT_PREFIX + ChatColor.RED + "개발자에게 보내주실 경우 신속히 처리하겠습니다.");
    }

    public static void sendConsoleMessage(String string) {
        console.sendMessage(DEFAULT_PREFIX + string);
    }

    public static void sendConsoleMessage(Iterable<String> strings) {
        for (String string : strings) {
            console.sendMessage(DEFAULT_PREFIX + string);
        }
    }

}