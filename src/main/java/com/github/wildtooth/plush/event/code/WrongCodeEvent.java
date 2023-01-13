package com.github.wildtooth.plush.event.code;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class WrongCodeEvent extends CodeEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    protected Player player;

    public WrongCodeEvent(String theCode, Player thePlayer) {
        super(theCode);
        this.player = thePlayer;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }
}
