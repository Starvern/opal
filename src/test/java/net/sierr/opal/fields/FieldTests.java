package net.sierr.opal.fields;

import net.sierr.opal.fields.impl.MaterialFieldType;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldTests
{
    private ConfigurationSection testItemSection;

    @BeforeEach
    void setup()
    {
        File testItemFile = new File("src/test/resources/items/test_item.yml");
        FileConfiguration testItemConfig = YamlConfiguration.loadConfiguration(testItemFile);
        testItemSection = testItemConfig.getConfigurationSection("test_item");
    }

    @Test
    void testMaterialField()
    {
        MaterialFieldType field = new MaterialFieldType("material", this.testItemSection);
        assertEquals("material", field.key);
        assertEquals(Material.DIAMOND_SWORD, field.getValue());

        // Not implemented in MockBukkit.
        // assertEquals(Material.DIAMOND_SWORD, field.apply(new ItemStack(Material.STONE)).getType());
    }
}
