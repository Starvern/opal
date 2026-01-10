package net.sierr.opal.item;

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
        Set<AbstractField<?>> fields = new HashSet<>(Set.of(
                new MaterialField(Material.DIAMOND_SWORD)
        ));

        this.item = new OpalItem("test_item", fields);
    }

    @Test
    public void testItem()
    {
        Set<AbstractField<?>> appliedFields = this.item.applyMods();

        assertEquals(
                Material.DIAMOND_SWORD,
                appliedFields.stream().findFirst().get().getValue()
        );
    }

    @Test
    public void testItemWithMods()
    {
        this.item.addMod(new BedrockMod());
        Set<AbstractField<?>> appliedFields = this.item.applyMods();

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
