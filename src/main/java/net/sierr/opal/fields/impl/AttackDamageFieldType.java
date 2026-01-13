package net.sierr.opal.fields.impl;

import net.sierr.opal.fields.AbstractFieldType;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AttackDamageFieldType extends AbstractFieldType<Double>
{
    public AttackDamageFieldType(@NotNull String key, @NotNull Double value)
    {
        super(key, value);
    }

    public AttackDamageFieldType(@NotNull String key, @NotNull ConfigurationSection section)
    {
        super(key, section);
    }

    @Override
    public Class<Double> getType()
    {
        return Double.class;
    }

    @Override
    public @NotNull Double getValue()
    {
        if (this.value != null)
            return this.value;

        return this.section.getDouble(this.key);
    }

    @Override
    public ItemStack apply(@NotNull ItemStack itemStack)
    {
        itemStack.editMeta(meta -> {
            meta.addAttributeModifier(
                    Attribute.ATTACK_DAMAGE,
                    new AttributeModifier(
                            new NamespacedKey("opal", "attack_damage"),
                            this.getValue(),
                            AttributeModifier.Operation.ADD_SCALAR
                    )
            );
        });

        return itemStack;
    }
}
