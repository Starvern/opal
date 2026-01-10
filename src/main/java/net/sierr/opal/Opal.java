package net.sierr.opal;

public class Opal
{
    private final OpalPlugin plugin;

    public Opal(OpalPlugin plugin)
    {
        this.plugin = plugin;
    }

    /**
     * @return The instance of {@link OpalPlugin} for this API.
     * @since 0.0.2
     */
    public OpalPlugin getPlugin()
    {
        return this.plugin;
    }
}
