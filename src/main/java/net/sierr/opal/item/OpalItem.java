package net.sierr.opal.item;

import net.sierr.opal.mod.AbstractMod;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *     Represents an instance of an actual item. This can either be built from scratch via config, or
 *     through the API via an existing {@link ItemStack}.
 * </p>
 * @since 0.0.3
 */
public class OpalItem
{
    private final String id;
    private final Set<AbstractField<?>> fields;
    private final Set<AbstractMod<?>> mods;

    public OpalItem(String id, Set<AbstractField<?>> fields)
    {
        this.id = id;
        this.fields = fields;
        this.mods = new HashSet<>();
    }

    /**
     * @return The ID of this item, as appearing in a config.
     * @since 0.0.3
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * @param type The type to check for.
     * @return An {@link AbstractField} where {@link AbstractField#getType()} equals the given type, or null.
     * @since 0.0.3
     */
    private @Nullable AbstractField<?> getByType(Class<?> type)
    {
        for (AbstractField<?> field : this.fields)
        {
            if (field.getType().equals(type))
                return field;
        }
        return null;
    }

    /**
     * @return An {@link ItemStack} built from {@link OpalItem#fields}.
     * @since 0.0.3
     */
    public ItemStack buildItemStack()
    {
        ItemStack itemStack = ItemStack.of(Material.STONE);

        for (AbstractField<?> field : this.fields)
        {
            itemStack = field.apply(itemStack);
        }

        for (AbstractMod<?> mod : this.mods)
        {
            @Nullable AbstractField<?> correspondingField = getByType(mod.getType());
            if (correspondingField == null)
            {
                itemStack = mod.applyMod().apply(itemStack);
                continue;
            }

            itemStack = mod.applyMod(correspondingField).apply(itemStack);
        }

        return itemStack;
    }
}
