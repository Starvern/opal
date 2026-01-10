package net.sierr.opal.item;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MaterialField extends AbstractField<Material>
{
    public MaterialField(ConfigurationSection section)
    {
        super(section, "material");
    }

    @Override
    public @Nullable Material getValue()
    {
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
