package net.sierr.opal;

import org.bukkit.plugin.java.JavaPlugin;

public class OpalPlugin extends JavaPlugin
{
    private Opal api;

    @Override
    public void onEnable()
    {
        this.api = new Opal(this);
        this.api.register();
    }

    @Override
    public void onDisable()
    {
        this.api.unregister();
        this.api = null;
    }

    /**
     * @return The running instance of {@link Opal} for this plugin.
     * @since 0.0.4
     */
    public Opal getApi()
    {
        return this.api;
    }
}
