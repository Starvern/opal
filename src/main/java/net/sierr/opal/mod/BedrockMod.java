package net.sierr.opal.mod;

import net.sierr.opal.fields.MaterialFieldType;
import org.bukkit.Material;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Changes an {@link org.bukkit.inventory.ItemStack} to {@link Material#BEDROCK}.
 * @since 0.0.3
 * @apiNote This mod is used for testing only.
 */
@ApiStatus.Experimental
@ApiStatus.Internal
public class BedrockMod extends AbstractMod<Material, MaterialFieldType>
{
    public BedrockMod()
    {
        super("bedrock");
    }

    @Override
    public Class<MaterialFieldType> getFieldType()
    {
        return MaterialFieldType.class;
    }

    @Override
    public @NotNull MaterialFieldType executeApplication(@Nullable MaterialFieldType field)
    {
        return new MaterialFieldType("material", Material.BEDROCK);
    }
}
