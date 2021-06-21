package com.bc.bcplugin.command;

import com.bc.bcplugin.GUI.about.OpenCoinAboutGUIEvent;
import com.bc.bcplugin.GUI.admin.PlayerManagementGUI;
import com.bc.bcplugin.GUI.admin.PlayerManagementGUIEvent;
import com.bc.bcplugin.GUI.have.OpenCoinHaveGUIEvent;
import com.bc.bcplugin.GUI.list.OpenCoinListGUIEvent;
import com.bc.bcplugin.GUI.market.OpenCoinMarketGUIEvent;
import com.bc.bcplugin.Plugin;
import com.bc.bcplugin.command.cmds.MoneyCheckCommand;
import com.bc.bcplugin.command.cmds.MoneyGiveCommand;
import com.bc.bcplugin.command.cmds.MoneyReduceCommand;
import com.bc.bcplugin.command.cmds.MoneySendCommand;
import com.bc.bcplugin.utils.Messager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * 명령어 실행문 코드입니다.
 */
public class Commands implements CommandExecutor, TabCompleter {

    private final Plugin plugin;
    private final CommandConfig mainCommandConfig;

    public CommandConfig getCommand() {
        return mainCommandConfig;
    }

    public Commands(Plugin plugin) {
        this.plugin = plugin;
        this.mainCommandConfig = new CommandConfig() {
            @Override
            protected boolean onCommand(CommandSender sender, String command, String[] args) {
                Player player = (Player) sender;

                // 이 플러그인의 기초 정보 출력
                if(args.length == 0) {
                    player.sendMessage("§7===================§a[ §eBitCraft §a]§7===================");
                    player.sendMessage("§eMade By : §a임동우");
                    player.sendMessage("§eEmail : §azzzz0827@naver.com");
                    player.sendMessage("§ePlugin Version : §a1.0");
                    player.sendMessage("§e/bc help로 §a사용 할 수 있는 §c명령어§a를 확인하세요!");
                    player.sendMessage("§e/bc §cadmin§e으로 §c관리자가 §a사용 할 수 있는 §c명령어§a를 확인하세요!");
                    player.sendMessage("§7===================§a[ §eBitCraft §a]§7===================");

                    return true;
                }
                return false;
            }
        };

        // 서브 커맨드를 추가하는 코드입니다.

        // /bc help 입력 시
        mainCommandConfig.addSubCommand("help", new CommandConfig() {
            @Override
            protected boolean onCommand(CommandSender sender, String command, String[] args) {
                Player player = (Player) sender;
                player.sendMessage("§7=====================§a[ §eCommand §a]§7=====================");
                player.sendMessage("§e/bc §aabout : §b비트코인의 현재 정보를 확인할 수 있는 GUI를 출력합니다.");
                player.sendMessage("§e/bc §ahave : §b현재 보유중인 비트코인을 확인할 수 있는 GUI를 출력합니다.");
                player.sendMessage("§e/bc §alist : §b비트코인의 종류를 보여주는 GUI를 출력합니다.");
                player.sendMessage("§e/bc §amarket : §b비트코인을 구매/판매 할 수 있는 시장 GUI를 출력합니다.");
                player.sendMessage("§e/bc §amoney : §b보유 자산을 관리하는 명령어를 출력합니다.");
                player.sendMessage("§7=====================§a[ §eCommand §a]§7=====================");

                return true;
            }
        });

        // /bc list 입력 시
        mainCommandConfig.addSubCommand("list", new CommandConfig() {
            @Override
            protected boolean onCommand(CommandSender sender, String command, String[] args) {
                OpenCoinListGUIEvent openGUI = new OpenCoinListGUIEvent((Player) sender);
                Bukkit.getPluginManager().callEvent(openGUI);
                return true;
            }
        });
        
        // /bc about 입력 시
        mainCommandConfig.addSubCommand("about", new CommandConfig() {
            @Override
            protected boolean onCommand(CommandSender sender, String command, String[] args) {
                OpenCoinAboutGUIEvent openGUI = new OpenCoinAboutGUIEvent((Player) sender);
                Bukkit.getPluginManager().callEvent(openGUI);
                return true;
            }
        });

        // /bc market 입력 시
       mainCommandConfig.addSubCommand("market", new CommandConfig() {
            @Override
            protected boolean onCommand(CommandSender sender, String command, String[] args) {
                OpenCoinMarketGUIEvent openGUI = new OpenCoinMarketGUIEvent((Player) sender);
                Bukkit.getPluginManager().callEvent(openGUI);
                return true;
            }
        });

       // /bc have 입력 시
        mainCommandConfig.addSubCommand("have", new CommandConfig() {
            @Override
            protected boolean onCommand(CommandSender sender, String command, String[] args) {
                OpenCoinHaveGUIEvent openGUI = new OpenCoinHaveGUIEvent((Player) sender);
                Bukkit.getPluginManager().callEvent(openGUI);
                return true;
            }
        });
        
        // /bc manage (Admin Only) 입력 시
        mainCommandConfig.addSubCommand("manage", new CommandConfig() {
            @Override
            protected boolean onCommand(CommandSender sender, String command, String[] args) {
                PlayerManagementGUIEvent openGUI = new PlayerManagementGUIEvent((Player) sender);
                Bukkit.getPluginManager().callEvent(openGUI);
                return true;
            }
        });

        // /bc admin ?? 입력 시
        final CommandConfig helpCommand = new CommandConfig() {
            {
                // /bc admin money 입력 시
                addSubCommand("money", new CommandConfig() {
                    @Override
                    protected boolean onCommand(CommandSender sender, String command, String[] args) {
                        Player player = (Player) sender;

                        if (player.isOp()) {
                            player.sendMessage("§7==============§a[ §cAdmin §eMoney Commands §a]§7==============");
                            player.sendMessage("§e/bc §amoney §ccheck [플레이어] : §b[플레이어]의 보유 금액을 확인합니다. §c(Admin Only).");
                            player.sendMessage("§e/bc §amoney §cgive [플레이어] [금액] : §b[플레이어]에게 [금액]만큼 돈을 지급합니다. §c(Admin Only).");
                            player.sendMessage("§e/bc §amoney §creduce [플레이어] [금액] : §b[플레이어]의 돈을 [금액]만큼 차감합니다. §c(Admin Only).");
                            player.sendMessage("§7==============§a[ §cAdmin §eMoney Commands §a]§7==============");
                        }else {
                            Messager.sendOpErrorMessage(player);
                        }
                        return true;
                    }
                });
            }
            
            // /bc admin 입력 시
            @Override
            protected boolean onCommand(CommandSender sender, String command, String[] args) {
                Player player = (Player) sender;

                if (player.isOp()) {
                    if (args.length == 0) {
                        player.sendMessage("§7=================§a[ §cAdmin §eCommands §a]§7=================");
                        player.sendMessage("§e/bc §amanage §c[플레이어] : §b[플레이어]의 비트코인을 관리할 수 있는 GUI를 출력합니다. §c(Admin Only)");
                        player.sendMessage("§e/bc §aadd §c[플레이어] [비트코인] [개수] : §b[플레이어]의 [비트코인]을 [개수]민큼 추가합니다. §c(Admin Only)");
                        player.sendMessage("§e/bc §aremove §c[플레이어] [비트코인] [개수] : §b[플레이어]의 [비트코인]을 [개수]민큼 감소합니다. §c(Admin Only)");
                        player.sendMessage("§e/bc §aset §c[플레이어] [비트코인] [개수] : §b[플레이어]의 [비트코인]을 [개수]민큼 설정합니다. §c(Admin Only)");
                        player.sendMessage("§e/bc §aadmin §cmoney : §b어드민이 사용 가능 한 자산 관리 명령어를 출력합니다. §c(Admin Only)");
                        player.sendMessage("§7=================§a[ §cAdmin §eCommands §a]§7=================");
                    }
                }else if (!player.isOp()) {
                    Messager.sendOpErrorMessage(player);
                }
                return true;
            }
        };
        mainCommandConfig.addSubCommand("admin", helpCommand);

        // /bc money ?? 입력 시
        final CommandConfig moneyCommand = new CommandConfig() {
            {
                // /bc money check 또는 /bc money check [플레이어] (Admin Only) 입력 시
                addSubCommand("check", new CommandConfig() {
                    @Override
                    protected boolean onCommand(CommandSender sender, String command, String[] args) {
                        if (args.length == 0) new MoneyCheckCommand(sender);
                        else if (args.length == 1) new MoneyCheckCommand(sender, args[0]);
                        return true;
                    }
                });

                // /bc money give (Admin Only) 입력 시
                addSubCommand("give", new CommandConfig() {
                    @Override
                    protected boolean onCommand(CommandSender sender, String command, String[] args) {
                        Player player = (Player) sender;
                        if(args.length == 0) Messager.sendErrorMessage(player, "플레이어를 입력해 주세요!");
                        else if (args.length == 1) Messager.sendErrorMessage(player, "금액을 입력해 주세요!");
                        else {
                            new MoneyGiveCommand(player, args[0], args[1]);
                        }
                        return true;
                    }
                });

                // /bc money reduce (Admin Only) 입력 시
                addSubCommand("reduce", new CommandConfig() {
                    @Override
                    protected boolean onCommand(CommandSender sender, String command, String[] args) {
                        Player player = (Player) sender;
                        if(args.length == 0) Messager.sendErrorMessage(player, "플레이어를 입력해 주세요!");
                        else if (args.length == 1) Messager.sendErrorMessage(player, "금액을 입력해 주세요!");
                        else {
                            new MoneyReduceCommand(player, args[0], args[1]);
                        }
                        return true;
                    }
                });

                // /bc money send 입력 시
                addSubCommand("send", new CommandConfig() {
                    @Override
                    protected boolean onCommand(CommandSender sender, String command, String[] args) {
                        Player player = (Player) sender;
                        if(args.length == 0) Messager.sendErrorMessage(player, "플레이어를 입력해 주세요!");
                        else if (args.length == 1) Messager.sendErrorMessage(player, "금액을 입력해 주세요!");
                        else {
                            new MoneySendCommand(player, args[0], args[1]);
                        }
                        return true;
                    }
                });
            }

            // /bc money 입력 시
            @Override
            protected boolean onCommand (CommandSender sender, String command, String[]args){
                Player player = (Player) sender;
                player.sendMessage("§7=================§a[ §eMoney Command §a]§7=================");
                player.sendMessage("§e/bc §amoney §ccheck : §b현재 자신의 보유 자산을 출력합니다.");
                player.sendMessage("§e/bc §amoney §csend [플레이어] [금액] : §b[플레이어]에게 [금액]만큼의 돈을 이체합니다.");
                player.sendMessage("§7=================§a[ §eMoney Command §a]§7=================");

                return true;
            }
        };
        mainCommandConfig.addSubCommand("money", moneyCommand);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        mainCommandConfig.handleCommand(sender, label, args);

        if(args.length == 0) return false;

        return true;
    }

    /**
     * 다중 명령어 (EX. /command subcmd1 subcmd2)를 제작하는 메소드입니다.
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (label.equalsIgnoreCase("bc")) {
            switch (args.length) {
                case 1:
                    if (player.isOp()) {
                        List<String> subCommands = Messager.asList("list", "about", "market", "money", "have", "help", "admin", "manage");
                        if (!args[0].isEmpty()) {
                            subCommands.removeIf(cmd -> !cmd.toLowerCase().startsWith(args[0].toLowerCase()));
                        }
                        return subCommands;
                    }else {
                        List<String> subCommands = Messager.asList("list", "about", "market", "money", "have", "help");
                        if (!args[0].isEmpty()) {
                            subCommands.removeIf(cmd -> !cmd.toLowerCase().startsWith(args[0].toLowerCase()));
                        }
                        return subCommands;
                    }

                case 2:
                    if (player.isOp()) {
                        if (args[0].equalsIgnoreCase("money")) {
                            List<String> getLists = Messager.asList("check", "give", "reduce", "send");
                            if (!args[1].isEmpty()) {
                                getLists.removeIf(getList -> !getList.toLowerCase().startsWith(args[1].toLowerCase()));
                            }
                            return getLists;
                        } else if (args[0].equalsIgnoreCase("admin")) {
                            List<String> getLists = Messager.asList("money");
                            if (!args[1].isEmpty()) {
                                getLists.removeIf(getList -> !getList.toLowerCase().startsWith(args[1].toLowerCase()));
                            }
                            return getLists;
                        }
                    }else {
                        if (args[0].equalsIgnoreCase("money")) {
                            List<String> getLists = Messager.asList("check", "send");
                            if (!args[1].isEmpty()) {
                                getLists.removeIf(getList -> !getList.toLowerCase().startsWith(args[1].toLowerCase()));
                            }
                            return getLists;
                        }
                    }
                case 4:
                    return Messager.asList("§");
            }
        }

        return null;
    }
}
