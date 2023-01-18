package com.github.wildtooth.plush.hook;

import com.github.wildtooth.plush.interfaces.IHook;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation of IHook for the dependencies
 */
public abstract class Hook implements IHook {
    private final String name;
    private final com.github.wildtooth.plush.enums.Hook hook;
    private final boolean isEnabled;

    /**
     * Hook constructor
     *
     * @param paramString Hook name
     * @param paramHook Hook enum
     */
    public Hook(String paramString, com.github.wildtooth.plush.enums.Hook paramHook){
        this.name = paramString;
        this.hook = paramHook;
        if(paramHook.isBuiltIn())
            this.isEnabled = true;
        else this.isEnabled = Bukkit.getPluginManager().getPlugin(getName()) != null && Bukkit.getPluginManager().getPlugin(getName()).isEnabled();
    }

    /**
     *
     * @return if the hook is enabled
     */
    @Override
    public boolean isEnabled(){
        return isEnabled;
    }

    /**
     *
     * @return the name of the hook
     */
    @Override
    public @NotNull String getName() {
        return name;
    }

    /**
     *
     * @return the enum of the hook
     */
    @Override
    public @NotNull com.github.wildtooth.plush.enums.Hook getEnum() {
        return hook;
    }
}
