package com.bc.bcplugin.command;

import com.bc.bcplugin.utils.Messager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class CommandConfig {

    private Set<Condition> conditions;
    private Map<String, CommandConfig> subCommands = null;

    public CommandConfig(Condition... conditions) {
        this.conditions = new HashSet<>(Arrays.asList(conditions));
    }

    public CommandConfig() {
        this.conditions = null;
    }

    public final void addSubCommand(String label, CommandConfig commandConfig) {
        if (subCommands == null) subCommands = new HashMap<>();
        subCommands.putIfAbsent(label.toLowerCase(), commandConfig);
        if (this.conditions != null) {
            if (commandConfig.conditions == null) commandConfig.conditions = new HashSet<>(conditions);
            else commandConfig.conditions.addAll(conditions);
        }
    }

    public final boolean hasSubCommands() {
        return subCommands != null;
    }

    public final void handleCommand(final CommandSender sender, final String command, final String[] args) {
        if (args.length > 0 && hasSubCommands()) {
            String label = args[0].toLowerCase();
            if (subCommands.containsKey(label)) {
                subCommands.get(label).handleCommand(sender, command, Arrays.copyOfRange(args, 1, args.length));
                return;
            }
        }
        if (conditions != null) {
            for (Condition condition : conditions) {
                if (!condition.test(sender)) {
                    Messager.sendErrorMessage(sender, condition.message);
                    return;
                }
            }
        }
        if (!onCommand(sender, command, args)) {
            if (args.length == 0) Messager.sendErrorMessage(sender, "존재하지 않는 명령어입니다.");
            else
                Messager.sendErrorMessage(sender, " 존재하지 않는 명령어입니다.");
        }
    }

    protected boolean onCommand(final CommandSender sender, final String command, final String[] args) {
        return true;
    }

    public enum Condition {

        OP("이 명령어를 사용하려면 OP 권한이 있어야 합니다.") {
            @Override
            protected boolean test(CommandSender sender) {
                return sender.isOp();
            }
        },
        PLAYER("콘솔에서 사용할 수 없는 명령어입니다.") {
            @Override
            protected boolean test(CommandSender sender) {
                return sender instanceof Player;
            }
        };

        private final String message;

        Condition(String message) {
            this.message = message;
        }

        protected abstract boolean test(CommandSender sender);
    }
}