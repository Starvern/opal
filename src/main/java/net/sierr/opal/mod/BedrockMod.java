package net.sierr.opal.mod;

import net.sierr.opal.item.AbstractField;
import net.sierr.opal.item.MaterialField;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Changes an {@link org.bukkit.inventory.ItemStack} to {@link Material#BEDROCK}.
 * @since 0.0.3
 */
public class BedrockMod extends AbstractMod<Material>
{
    public BedrockMod()
    {
        super("bedrock");
    }

    @Override
    public Class<Material> getType()
    {
        return Material.class;
    }

    @Override
    public @NotNull MaterialField applyMod(@Nullable AbstractField<?> field)
    {
        return new MaterialField(Material.BEDROCK);
    }
}
