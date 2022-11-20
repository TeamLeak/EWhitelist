package com.github.lkapitman.commands;

import java.util.Iterator;

import com.github.lkapitman.App;
import com.github.lkapitman.util.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class WLCmd implements CommandExecutor {
    String prefix = "&6&lE - &e&lWhiteList > &7";

    public boolean onCommand(CommandSender arg0, @NotNull Command arg1, @NotNull String arg2, String[] arg3) {
        if (arg0.hasPermission("easywhitelist.admin")) {
            this.remanage(arg0, arg3);
        }
        return true;
    }

    private void remanage(CommandSender snd, String[] args) {
        if (args.length == 0) {
            Utility.sendMsg(snd, "&a&lWhitelist &7>");
            Utility.sendMsg(snd, "&e> &7/easywl &aadd &f<name>");
            Utility.sendMsg(snd, "&e> &7/easywl &cremove &f<name>");
            Utility.sendMsg(snd, "&e> &7/easywl &flist");
            Utility.sendMsg(snd, "&e> &7/easywl &a&lon");
            Utility.sendMsg(snd, "&e> &7/easywl &c&loff");
            Utility.sendMsg(snd, "&e> &7/easywl &creload");
        } else {
            String str;
            String name;
            switch ((str = args[0].toLowerCase()).hashCode()) {
                case -934641255:

                    App.getStorage().reload();
                    return;
                case -934610812:
                    if (str.equals("remove")) {
                        if (args.length < 2) {
                            Utility.sendMsg(snd, "&7Please input a name!");
                            return;
                        }

                        name = args[1];
                        if (args.length > 2) {
                            name = name + " " + args[2];
                        }

                        if (args.length > 3) {
                            name = name + " " + args[2] + " " + args[3];
                        }

                        App.getStorage().removeWhitelist(name);
                        Utility.sendMsg(snd, this.prefix + "Whitelist removed for &c" + name);
                        return;
                    }
                case 3551:
                    if (str.equals("on")) {
                        App.getStorage().setWhitelist(true);
                        Utility.sendMsg(snd, this.prefix + "&fWhitelist is now &a&lON&f!");
                        return;
                    }
                case 96417:
                    if (str.equals("add")) {
                        if (args.length < 2) {
                            Utility.sendMsg(snd, "&7Please input a name!");
                            return;
                        }

                        name = args[1];
                        if (args.length > 2) {
                            name = name + " " + args[2];
                        }

                        if (args.length > 3) {
                            name = name + " " + args[2] + " " + args[3];
                        }

                        App.getStorage().addWhitelist(name);
                        Utility.sendMsg(snd, this.prefix + "Whitelisted '&a" + name + "&7'");
                        return;
                    }
                case 109935:
                    if (str.equals("off")) {
                        App.getStorage().setWhitelist(false);
                        Utility.sendMsg(snd, this.prefix + "&8Whitelist is &c&lOFF!&8");
                        return;
                    }
                case 3322014:
                    if (str.equals("list")) {
                        name = "";

                        String str1;
                        for(Iterator<String> var6 = App.getStorage().getWhiteLists().iterator(); var6.hasNext(); name = name + str1 + "&e&l, &7") {
                            str1 = var6.next();
                        }

                        Utility.sendMsg(snd, "&a&lWhitelisted: &7" + name);
                        return;
                    }
                default:
                    Utility.sendMsg(snd, "&a&lWhitelist &7>");
                    Utility.sendMsg(snd, "&e> &7/easywl &aadd &f<name>");
                    Utility.sendMsg(snd, "&e> &7/easywl &cremove &f<name>");
                    Utility.sendMsg(snd, "&e> &7/easywl &flist");
                    Utility.sendMsg(snd, "&e> &7/easywl &a&lon");
                    Utility.sendMsg(snd, "&e> &7/easywl &c&loff");
                    Utility.sendMsg(snd, "&e> &7/easywl &creload");
            }
        }
    }
}

