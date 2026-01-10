package net.sierr.opal.item;

import net.sierr.opal.OpalPlugin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;

public class OpalPluginTests
{
    private ServerMock server;
    private OpalPlugin plugin;

    @BeforeEach
    public void setup()
    {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(OpalPlugin.class);
    }

    @AfterEach
    public void tearDown()
    {
        MockBukkit.unmock();
    }
}
