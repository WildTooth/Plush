package com.github.wildtooth.plush.enums;

public enum Hook {
    VAULT(false),
    ;

    private final boolean isBuiltIn;

    /**
     * @param paramBoolean Whether the hook is built into Plush or not.
     */
    Hook(boolean paramBoolean) {
        this.isBuiltIn = paramBoolean;
    }

    /**
     * @return Whether the hook is built into Plush or not.
     */
    public boolean isBuiltIn() {
        return isBuiltIn;
    }
}
