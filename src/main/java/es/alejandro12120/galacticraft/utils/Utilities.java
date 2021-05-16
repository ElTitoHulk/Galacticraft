package es.alejandro12120.galacticraft.utils;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Utilities {

    public static void sendConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void broadcast(String message) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void clearchat() {
        for (int i = 0; i < 350; i++) {
            broadcast("");
        }
    }

    public static void clearPlayerChat(Player p) {
        for (int i = 0; i < 350; i++) {
            p.sendMessage("");
        }
    }

    public static int getPing(Player player) {
        try {
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            return (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long parseTime(String string) {
        if (string == null || string.isEmpty())
            return 0L;
        string = string.replaceAll("[^0-9smhdw]", "");
        if (string.isEmpty())
            return 0L;
        if (string.contains("w")) {
            string = string.replaceAll("[^0-9]", "");
            if (string.isEmpty())
                return 0L;
            return TimeUnit.DAYS.toSeconds(Long.parseLong(string) * 7);
        }
        TimeUnit unit = string.contains("d") ? TimeUnit.DAYS
                : string.contains("h") ? TimeUnit.HOURS
                : string.contains("m") ? TimeUnit.MINUTES
                : TimeUnit.SECONDS;
        string = string.replaceAll("[^0-9]", "");
        if (string.isEmpty())
            return 0L;
        return unit.toSeconds(Long.parseLong(string));
    }

    public static boolean isBoolean(String string) {
        try {
            Boolean.valueOf(string);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static boolean isChatColor(String s) {
        try {
            ChatColor.valueOf(s);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public static String getDecimalTimeLeft(long millis) {
        double seconds = ((double) millis / 1_000.0D);

        DecimalFormat format = new DecimalFormat("0.0");
        if(seconds > 60) {
            return convert((int) seconds);
        }else{
            return format.format(seconds)+"s";
        }
    }

    private static String convert(int seconds) {
        int h = seconds / 3600;
        int i = seconds - h * 3600;
        int m = i / 60;
        int s = i - m * 60;
        String timeF = "";
        if (h > 0) {
            if (h < 10) {
                timeF = String.valueOf(String.valueOf(timeF)) + "0";
            }
            timeF = String.valueOf(String.valueOf(timeF)) + h + ":";
        }
        if (m < 10) {
            timeF = String.valueOf(String.valueOf(timeF)) + "0";
        }
        timeF = String.valueOf(String.valueOf(timeF)) + m + ":";
        if (s < 10) {
            timeF = String.valueOf(String.valueOf(timeF)) + "0";
        }
        timeF = String.valueOf(String.valueOf(timeF)) + s;
        return timeF;
    }

    public static List<String> translateFromArray(List<String> text) {
        final List<String> messages = new ArrayList<>();
        for (final String string : text) {
            messages.add(translateFromString(string));
        }
        return messages;
    }

    public static String translateFromString(String text) {
        return StringEscapeUtils.unescapeJava(ChatColor.translateAlternateColorCodes('&', text));
    }
}

