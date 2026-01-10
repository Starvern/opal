package net.sierr.opal.manager;

import net.sierr.opal.OpalMock;
import net.sierr.opal.OpalResolver;
import net.sierr.opal.item.OpalItem;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemManagerTests
{
    @ParameterizedTest
    @ArgumentsSource(OpalResolver.class)
    public void testLoadingFile(OpalMock opal)
    {
        ItemManager itemManager = opal.getApi().getItemManager();
        File testItemFile = new File("src/test/resources/items/test_item.yml");

        for (OpalItem item : itemManager.loadItems(testItemFile))
        {
            itemManager.addItem(item);
        }

        assertEquals(3, itemManager.getItems().size());
    }
}
