package net.sierr.opal.item;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractField<T>
{
    protected final ConfigurationSection section;
    protected final String key;

    public AbstractField(
            @NotNull ConfigurationSection section,
            @NotNull String key
    ) {
        this.section = section;
        this.key = key;
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
     * @return The key of the value to pull from {@link AbstractField#getSection()}.
     * @since 0.0.2
     */
    public String getKey()
    {
        return this.key;
    }

    /**
     * @return The value retrieved and parsed from the config.
     * @since 0.0.2
     */
    public abstract @Nullable T getValue();

    /**
     * <p>
     *     Uses the value of {@link AbstractField#getValue()} to
     *     make changes to an {@link ItemStack}.
     * </p>
     * @param itemStack The {@link ItemStack} to apply changes to.
     * @return The changed {@link ItemStack}.
     * @since 0.0.2
     */
    public abstract ItemStack apply(@NotNull ItemStack itemStack);
}
