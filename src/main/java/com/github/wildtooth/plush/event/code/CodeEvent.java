package com.github.wildtooth.plush.event.code;

import org.bukkit.event.Event;

/**
 * The type Code event.
 */
public abstract class CodeEvent extends Event {
    /**
     * The Security Code.
     */
    protected String code;

    /**
     * Instantiates a new Code event.
     *
     * @param theCode the security code
     */
    public CodeEvent(String theCode) {
        this.code = theCode;
    }

    /**
     * @return the security code used in the event.
     */
    public final String getCode() {
        return code;
    }

}
