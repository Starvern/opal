package net.sierr.opal.item;

import net.sierr.opal.fields.AbstractFieldType;
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
    private final Set<AbstractFieldType<?>> fields;
    private final Set<AbstractMod<?, ?>> mods;

    public OpalItem(String id, Set<AbstractFieldType<?>> fields)
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
     * Add a mod to this item.
     * @param mod The {@link AbstractMod} to add to this item.
     * @since 0.0.3
     */
    public void addMod(AbstractMod<?, ?> mod)
    {
        this.mods.add(mod);
    }

    /**
     * @param type The type to check for.
     * @return An {@link AbstractFieldType} where {@link AbstractFieldType#getType()} equals the given type, or null.
     * @since 0.0.3
     */
    private @Nullable AbstractFieldType<?> getByType(Class<?> type, Set<AbstractFieldType<?>> fields)
    {
        for (AbstractFieldType<?> field : fields)
        {
            if (type.isInstance(field))
                return field;
        }
        return null;
    }

    /**
     * <p>
     *     Starting with the original value of {@link OpalItem#fields}, this method will
     *     change each field according to any {@link AbstractMod}(s) this item may have
     * </p>
     * @return <p>
     *     A {@link Set} containing the final {@link AbstractFieldType}(s) to be pushed on
     *     the item.
     * </p>
     * @since 0.0.3
     */
    public Set<AbstractFieldType<?>> applyMods()
    {
        Set<AbstractFieldType<?>> applyFields = new HashSet<>(this.fields);

        for (AbstractMod<?, ?> mod : this.mods)
        {
            @Nullable AbstractFieldType<?> correspondingField = getByType(mod.getFieldType(), applyFields);
            if (correspondingField == null)
            {
                applyFields.add(mod.applyMod());
                continue;
            }

            applyFields.remove(correspondingField);
            applyFields.add(mod.applyMod(mod.castOrNull(correspondingField)));
        }

        return applyFields;
    }

    /**
     * @return An {@link ItemStack} built from {@link OpalItem#fields}.
     * @since 0.0.3
     */
    public ItemStack buildItemStack()
    {
        Set<AbstractFieldType<?>> applyFields = applyMods();

        ItemStack itemStack = ItemStack.of(Material.STONE);

        for (AbstractFieldType<?> field : applyFields)
        {
            itemStack = field.apply(itemStack);
        }

        return itemStack;
    }
}
