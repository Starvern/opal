package net.sierr.opal.fields;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Changes an {@link ItemStack} to the given {@link Material}.
 * @since 0.0.3
 */
public class MaterialFieldType extends AbstractFieldType<Material>
{
    public MaterialFieldType(String key, Material material)
    {
        super(key, material);
    }

    public MaterialFieldType(String key, ConfigurationSection section)
    {
        super(key, section);
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
