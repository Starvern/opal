package net.sierr.opal.fields.impl;

import net.kyori.adventure.text.Component;
import net.sierr.opal.Formatter;
import net.sierr.opal.fields.AbstractFieldType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Applies a custom name to a {@link ItemStack}.
 * @since 0.0.5
 */
public class NameFieldType extends AbstractFieldType<Component>
{
    public NameFieldType(@NotNull String key, @NotNull ConfigurationSection section)
    {
        super(key, section);
    }

    public NameFieldType(@NotNull String key, @NotNull Component value)
    {
        super(key, value);
    }

    @Override
    public Class<Component> getType()
    {
        return Component.class;
    }

    @Override
    public @Nullable Component getValue()
    {
        if (this.value != null)
            return this.value;

        String name = this.section.getString(this.key);

        if (name == null)
            return null;

        return Formatter.colorize(name);
    }

    @Override
    public ItemStack apply(@NotNull ItemStack itemStack)
    {
        itemStack.editMeta(meta -> meta.customName(this.getValue()));
        return itemStack;
    }
}
