package net.sierr.opal;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.sierr.opal.command.OpalCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class OpalPlugin extends JavaPlugin
{
    private Opal api;

    @Override
    public void onEnable()
    {
        this.api = new Opal(this);
        this.api.register();

        this.getLifecycleManager().registerEventHandler(
                LifecycleEvents.COMMANDS,
                commands ->
                        commands.registrar().register(
                                OpalCommand.createCommand(this.api).build(),
                                "Access Opal.",
                                List.of("o")
                        )
        );

        OpalCommand.createCommand(this.api);

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
