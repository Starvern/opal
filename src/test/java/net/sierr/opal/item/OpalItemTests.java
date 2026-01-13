package net.sierr.opal.item;

import net.sierr.opal.fields.AbstractFieldType;
import net.sierr.opal.fields.impl.MaterialFieldType;
import net.sierr.opal.mod.BedrockMod;
import org.bukkit.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpalItemTests
{
    private OpalItem item;
    @BeforeEach
    public void setup()
    {
        Set<AbstractFieldType<?>> fields = new HashSet<>(Set.of(
                new MaterialFieldType("material", Material.DIAMOND_SWORD)
        ));

        this.item = new OpalItem("test_item", fields);
    }

    @Test
    public void testItem()
    {
        Set<AbstractFieldType<?>> appliedFields = this.item.applyMods();

        assertEquals(
                Material.DIAMOND_SWORD,
                appliedFields.stream().findFirst().get().getValue()
        );
    }

    @Test
    public void testItemWithMods()
    {
        this.item.addMod(new BedrockMod());
        Set<AbstractFieldType<?>> appliedFields = this.item.applyMods();

        assertEquals(
                Material.BEDROCK,
                appliedFields.stream().findFirst().get().getValue()
        );

        /*
        Not implemented in MockBukkit.
        assertEquals(Material.BEDROCK, this.item.buildItemStack().getType());
         */
    }
}
