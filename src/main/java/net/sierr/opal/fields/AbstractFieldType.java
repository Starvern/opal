package net.sierr.opal.fields;

import net.sierr.opal.item.OpalItem;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <p>
 *     Represents a key-value pair which stores a specific aspect of an {@link OpalItem}.
 *     The value may either be provided directly, or indirectly by passing a {@link ConfigurationSection}
 *     to the constructor.
 * </p>
 * @param <T> The type of value this field holds.
 * @since 0.0.2
 */
public abstract class AbstractFieldType<T>
{
    protected final String key;

    protected ConfigurationSection section;
    protected T value;

    public AbstractFieldType(
            @NotNull String key,
            @NotNull T value
    ) {
        this.key = key;
        this.value = value;
    }

    public AbstractFieldType(
            @NotNull String key,
            @NotNull ConfigurationSection section
    ) {
        this.key = key;
        this.section = section;
    }

    /**
     * @return The {@link ConfigurationSection} this field pulls from.
     * @since 0.0.2
     */
    public ConfigurationSection getSection()
    {
        return this.section;
    }

    /**
     * @return The key of the value to pull from {@link AbstractFieldType#getSection()}.
     * @since 0.0.2
     */
    public String getKey()
    {
        return this.key;
    }

    /**
     * @return The {@link Class} of the type of value this field holds.
     * @since 0.0.3
     */
    public abstract Class<T> getType();

    /**
     * @return <p>
     *     Either the {@link AbstractFieldType#value} passed to this field, or if null,
     *     the value retrieved from the {@link AbstractFieldType#getSection()}.
     * </p>
     * @since 0.0.2
     */
    public abstract @Nullable T getValue();

    /**
     * @param value The {@link T} value to set to.
     * @since 0.0.4
     */
    public void setValue(@Nullable T value)
    {
        this.value = value;
    }

    /**
     * <p>
     *     Uses the value of {@link AbstractFieldType#getValue()} to
     *     make changes to an {@link ItemStack}.
     * </p>
     * @param itemStack The {@link ItemStack} to apply changes to.
     * @return The changed {@link ItemStack}.
     * @since 0.0.2
     */
    public abstract ItemStack apply(@NotNull ItemStack itemStack);
}
