package com.github.lkapitman.util;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

public final class Utility {
    private static final int CENTER_PX = 154;

    public static Class<?> getClass(String classname) {
        String servversion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        try {
            return Class.forName("net.minecraft.server." + servversion + "." + classname);
        } catch (ClassNotFoundException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static void sendMsg(CommandSender b, String msg) {
        b.sendMessage(TransColor(msg));
    }

    public static void sendConsole(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static String TransColor(String c) {
        return ChatColor.translateAlternateColorCodes('&', c);
    }

    public static String[] TransColor(String[] c) {
        String strf = "";
        int length = c.length;
        int cr = 0;
        int i = c.length;

        for (String str : c) {
            ++cr;
            if (cr != length) {
                strf = String.valueOf(strf) + str + ";";
            } else {
                strf = String.valueOf(strf) + str;
            }
        }

        strf = TransColor(strf);
        return strf.split(";");
    }

    public static List<String> TransColor(List<String> strlist) {
        strlist.replaceAll(Utility::TransColor);

        return strlist;
    }

    public static void PlaySoundAt(World w, Location p, Sound s, Float vol, Float pit) {
        w.playSound(p, s, vol, pit);
    }

    public static void PlaySound(Player p, Sound s, Float vol, Float pit) {
        p.playSound(p.getLocation(), s, vol, pit);
    }

    public static ArrayList<Player> near(Entity loc, int radius) {
        ArrayList<Player> nearby = new ArrayList<>();

        for (Entity e : loc.getNearbyEntities(radius, radius, radius)) {
            if (e instanceof Player) {
                nearby.add((Player) e);
            }
        }

        return nearby;
    }

    public static void PlayParticle(World world, Location loc, Effect particle, int count) {
        world.playEffect(loc, particle, count);
    }

    public static void spawnParticle(World world, Particle particle, Location loc, Double Xoff, Double Yoff, Double Zoff, int count) {
        world.spawnParticle(particle, loc.getX(), loc.getY(), loc.getZ(), count, Xoff, Yoff, Zoff);
    }

    public static String normalizeTime(int seconds) {
        int sec = seconds;
        int min = 0;
        int hour = 0;

        int day;
        for(day = 0; sec >= 60; sec -= 60) {
            ++min;
        }

        while(min >= 60) {
            ++hour;
            min -= 60;
        }

        while(hour >= 24) {
            ++day;
            hour -= 24;
        }

        if (sec == 0 && min == 0 && hour == 0 && day == 0) {
            return "&a&lZERO!";
        } else if (min == 0 && hour == 0 && day == 0) {
            return String.valueOf(sec) + " Seconds";
        } else if (hour == 0 && day == 0 && min > 0) {
            return String.valueOf(min) + " Minutes " + sec + " Seconds";
        } else if (day == 0 && hour > 0) {
            return String.valueOf(hour) + " Hours " + min + " Minutes " + sec + " Seconds";
        } else {
            return day > 0 ? String.valueOf(day) + " Days " + hour + " Hours " + min + " Minutes " + sec + " Seconds" : "&a&lZERO!";
        }
    }

    public static String normalizeTime2(int seconds) {
        int sec = seconds;
        int min = 0;
        int hour = 0;

        int day;
        for(day = 0; sec >= 60; sec -= 60) {
            ++min;
        }

        while(min >= 60) {
            ++hour;
            min -= 60;
        }

        while(hour >= 24) {
            ++day;
            hour -= 24;
        }

        if (sec == 0 && min == 0 && hour == 0 && day == 0) {
            return "&a&lZERO!";
        } else if (min == 0 && hour == 0 && day == 0) {
            return String.valueOf(sec) + " sec";
        } else if (hour == 0 && day == 0 && min > 0) {
            return String.valueOf(min) + " min " + sec + " sec";
        } else if (day == 0 && hour > 0) {
            return String.valueOf(hour) + " h " + min + " min " + sec + " sec";
        } else {
            return day > 0 ? String.valueOf(day) + " day " + hour + " h " + min + " min " + sec + " sec" : "&a&lZERO!";
        }
    }

    public static boolean isEmpty(Inventory inv) {
        int size = inv.getSize();

        for(int i = 0; i < size; ++i) {
            if (inv.getItem(i) == null) {
                return true;
            }
        }

        return false;
    }

    public static boolean isEmpty(PlayerInventory inv) {
        return isEmpty(inv);
    }

    public static enum DefaultFontInfo {
        A('A', 5),
        a('a', 5),
        B('B', 5),
        b('b', 5),
        C('C', 5),
        c('c', 5),
        D('D', 5),
        d('d', 5),
        E('E', 5),
        e('e', 5),
        F('F', 5),
        f('f', 4),
        G('G', 5),
        g('g', 5),
        H('H', 5),
        h('h', 5),
        I('I', 3),
        i('i', 1),
        J('J', 5),
        j('j', 5),
        K('K', 5),
        k('k', 4),
        L('L', 5),
        l('l', 1),
        M('M', 5),
        m('m', 5),
        N('N', 5),
        n('n', 5),
        O('O', 5),
        o('o', 5),
        P('P', 5),
        p('p', 5),
        Q('Q', 5),
        q('q', 5),
        R('R', 5),
        r('r', 5),
        S('S', 5),
        s('s', 5),
        T('T', 5),
        t('t', 4),
        U('U', 5),
        u('u', 5),
        V('V', 5),
        v('v', 5),
        W('W', 5),
        w('w', 5),
        X('X', 5),
        x('x', 5),
        Y('Y', 5),
        y('y', 5),
        Z('Z', 5),
        z('z', 5),
        NUM_1('1', 5),
        NUM_2('2', 5),
        NUM_3('3', 5),
        NUM_4('4', 5),
        NUM_5('5', 5),
        NUM_6('6', 5),
        NUM_7('7', 5),
        NUM_8('8', 5),
        NUM_9('9', 5),
        NUM_0('0', 5),
        EXCLAMATION_POINT('!', 1),
        AT_SYMBOL('@', 6),
        NUM_SIGN('#', 5),
        DOLLAR_SIGN('$', 5),
        PERCENT('%', 5),
        UP_ARROW('^', 5),
        AMPERSAND('&', 5),
        ASTERISK('*', 5),
        LEFT_PARENTHESIS('(', 4),
        RIGHT_PERENTHESIS(')', 4),
        MINUS('-', 5),
        UNDERSCORE('_', 5),
        PLUS_SIGN('+', 5),
        EQUALS_SIGN('=', 5),
        LEFT_CURL_BRACE('{', 4),
        RIGHT_CURL_BRACE('}', 4),
        LEFT_BRACKET('[', 3),
        RIGHT_BRACKET(']', 3),
        COLON(':', 1),
        SEMI_COLON(';', 1),
        DOUBLE_QUOTE('"', 3),
        SINGLE_QUOTE('\'', 1),
        LEFT_ARROW('<', 4),
        RIGHT_ARROW('>', 4),
        QUESTION_MARK('?', 5),
        SLASH('/', 5),
        BACK_SLASH('\\', 5),
        LINE('|', 1),
        TILDE('~', 5),
        TICK('`', 2),
        PERIOD('.', 1),
        COMMA(',', 1),
        SPACE(' ', 3),
        DEFAULT('a', 4);

        private final char character;
        private final int length;

        private DefaultFontInfo(char character, int length) {
            this.character = character;
            this.length = length;
        }

        public final char getCharacter() {
            return this.character;
        }

        public final int getLength() {
            return this.length;
        }

        public final int getBoldLength() {
            return this == SPACE ? this.getLength() : this.length + 1;
        }

        public static DefaultFontInfo getDefaultFontInfo(char c) {
            DefaultFontInfo[] arrayOfDefaultFontInfo;
            int i = (arrayOfDefaultFontInfo = values()).length;

            for(byte b = 0; b < i; ++b) {
                DefaultFontInfo dFI = arrayOfDefaultFontInfo[b];
                if (dFI.getCharacter() == c) {
                    return dFI;
                }
            }

            return DEFAULT;
        }
    }
}
