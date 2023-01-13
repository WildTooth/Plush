package com.github.wildtooth.plush.event.code;

import org.bukkit.event.Event;

public abstract class CodeEvent extends Event {
    protected String code;

    public CodeEvent(String theCode) {
        this.code = theCode;
    }

    public final String getCode() {
        return code;
    }

}
