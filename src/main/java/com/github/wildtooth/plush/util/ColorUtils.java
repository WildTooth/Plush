package com.github.wildtooth.plush.util;

import org.bukkit.ChatColor;

import java.util.List;

/**
 * A utility class for converting between {@link ChatColor} and minecraft chat color codes.
 *
 * @since 0.0.1
 * @version 0.0.1
 * @author wildtooth
 */
public final class ColorUtils {
    /**
     * Translates a string using an alternate color code character
     * into an array of Strings that uses the
     * <p>internal {@link ChatColor}.COLOR.</p>
     *
     * @param stringList The string(s) to translate.
     *
     * @return The translated string(s)
     */
    public static String[] getColored(String... stringList){
        if(stringList == null) return null;
        for(int i = 0; i< stringList.length;i++)
            stringList[i] = getColored(stringList[i]);
        return stringList;
    }

    /**
     * Translates a list of strings using an alternate color code character
     * into a list of Strings that uses the internal {@link ChatColor}.COLOR.
     *
     * @param stringList The string(s) to translate.
     *
     * @return The translated string(s)
     */
    public static List<String> getColored(List<String> stringList){
        if(stringList == null) return null;
        for(int i = 0; i< stringList.size();i++)
            stringList.set(i, getColored(stringList.get(i)));
        return stringList;
    }

    /**
     * Translates a string using an alternate color code character
     * into a string that uses the <p>internal {@link ChatColor}.COLOR.</p>
     *
     * @param s The string to translate.
     *
     * @return The translated string
     */
    public static String getColored(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
