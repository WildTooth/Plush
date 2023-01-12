package com.github.wildtooth.plush.security.threat;

import com.github.wildtooth.plush.security.ThreatHandler;
import com.github.wildtooth.plush.util.ColorUtils;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class WrongCodeThreat extends Threat {

    private final Player cause;

    public WrongCodeThreat(String string, ThreatLevel threatLevel, Player player) {
        super(string, threatLevel);
        this.cause = player;
    }

    @Override
    public void execute(ThreatHandler handler) {
        if (severity().equals(ThreatLevel.LOW)) return;

        if (severity().equals(ThreatLevel.MEDIUM)) {
            cause.kickPlayer(ColorUtils.getColored("&cYou have been kicked, use the right code!"));
            return;
        }

        if (severity().equals(ThreatLevel.HIGH)) {
            cause.getServer().getBanList(BanList.Type.NAME).addBan(ChatColor.stripColor(
                    cause.getName()),
                    ColorUtils.getColored(
                            "&cYou have been banned, contact server-administration! &4&lError Code: &c&lWRONG_CODE"
                    ),
                    null,
                    null);
            cause.kickPlayer("&cYou have been banned, contact server-administration! &4&lError Code: &c&lWRONG_CODE");
        }
    }

    public Player getCause() {
        return cause;
    }

    public ThreatLevel severity() {
        return getThreatLevel();
    }
}