package net.sierr.opal.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;

public class OpalPluginTests
{
    private ServerMock server;

    @BeforeEach
    public void setup()
    {
        server = MockBukkit.mock();
    }

    @AfterEach
    public void tearDown()
    {
        MockBukkit.unmock();
    }
}
