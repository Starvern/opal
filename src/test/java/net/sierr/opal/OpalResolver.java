package net.sierr.opal;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.ParameterDeclarations;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;

import java.util.stream.Stream;

public class OpalResolver implements ArgumentsProvider
{
    @Override
    public @NotNull Stream<? extends Arguments> provideArguments(
            @NotNull ParameterDeclarations parameters,
            @NotNull ExtensionContext context
    ) {
        MockBukkit.unmock();
        ServerMock server = MockBukkit.mock();
        OpalPlugin plugin = MockBukkit.load(OpalPlugin.class);
        OpalMock opal = new OpalMock(server, plugin, plugin.getApi());
        return Stream.of(Arguments.of(opal));
    }
}
