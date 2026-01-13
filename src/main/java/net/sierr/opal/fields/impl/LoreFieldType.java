package net.sierr.opal.fields.impl;

import net.kyori.adventure.text.Component;
import net.sierr.opal.Formatter;
import net.sierr.opal.fields.AbstractFieldType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class LoreFieldType extends AbstractFieldType<Component[]>
{
    public LoreFieldType(@NotNull String key, @NotNull Component[] value)
    {
        super(key, value);
    }

    public LoreFieldType(@NotNull String key, @NotNull ConfigurationSection section)
    {
        super(key, section);
    }

    @Override
    public Class<Component[]> getType()
    {
        return Component[].class;
    }

    @Override
    public Component @NotNull [] getValue()
    {
        if (this.value != null)
            return this.value;

        return Formatter.colorize(this.section.getStringList(this.key)).toArray(Component[]::new);
    }

    @Override
    public ItemStack apply(@NotNull ItemStack itemStack)
    {
        itemStack.editMeta(meta -> meta.lore(Arrays.stream(this.getValue()).toList()));
        return itemStack;
    }
}
