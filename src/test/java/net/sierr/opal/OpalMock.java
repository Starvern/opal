package net.sierr.opal;

import org.mockbukkit.mockbukkit.ServerMock;

public class OpalMock
{
    private final ServerMock server;
    private final OpalPlugin plugin;
    private final Opal api;

    public OpalMock(ServerMock server, OpalPlugin plugin, Opal api)
    {
        this.server = server;
        this.plugin = plugin;
        this.api = api;
    }

    public ServerMock getServer()
    {
        return server;
    }

    public OpalPlugin getPlugin()
    {
        return plugin;
    }

    public Opal getApi()
    {
        return api;
    }
}
