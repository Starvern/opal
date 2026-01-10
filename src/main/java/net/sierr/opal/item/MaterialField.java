package net.sierr.opal.item;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Changes an {@link ItemStack} to the given {@link Material}.
 * @since 0.0.3
 */
public class MaterialField extends AbstractField<Material>
{
    public MaterialField(Material material)
    {
        super("material", material);
    }

    public MaterialField(ConfigurationSection section)
    {
        super("material", section);
    }

    @Override
    public Class<Material> getType()
    {
        return Material.class;
    }

    @Override
    public @Nullable Material getValue()
    {
        if (this.value != null)
            return this.value;

        String materialName = this.section.getString(this.key);

        if (materialName == null)
            return null;

        return Material.matchMaterial(materialName);
    }

    @Override
    public ItemStack apply(@NotNull ItemStack itemStack)
    {
        @Nullable Material material = this.getValue();

        if (material == null)
            return itemStack;

        return itemStack.withType(material);
    }
}
