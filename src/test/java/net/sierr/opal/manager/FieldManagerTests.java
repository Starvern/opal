package net.sierr.opal.manager;

import net.sierr.opal.OpalMock;
import net.sierr.opal.OpalResolver;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldManagerTests
{
    @ParameterizedTest
    @ArgumentsSource(OpalResolver.class)
    public void testLoadingFile(OpalMock opal)
    {
        FieldManager fieldManager = opal.getApi().getFieldManager();

        assertEquals(4, fieldManager.getEntries().size());
    }
}
