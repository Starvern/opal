package net.sierr.opal.item;

import net.sierr.opal.fields.AbstractFieldType;
import net.sierr.opal.mod.AbstractMod;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class OpalItemBuilder
{
    private final @NotNull String id;
    private @NotNull Set<AbstractFieldType<?>> fields;
    private @NotNull Set<AbstractMod<?, ?>> mods;

    public OpalItemBuilder(@NotNull String id)
    {
        this.id = id;
        this.fields = new HashSet<>();
        this.mods = new HashSet<>();
    }

    /**
     * @param fields A {@link Set} of {@link AbstractFieldType} to apply.
     * @return The instance of {@link OpalItemBuilder}.
     * @since 0.0.5
     */
    public OpalItemBuilder withFields(@NotNull Set<AbstractFieldType<?>> fields)
    {
        this.fields = fields;
        return this;
    }

    /**
     * @param mods A {@link Set} of {@link AbstractMod} to apply.
     * @return The instance of {@link OpalItemBuilder}.
     * @since 0.0.5
     */
    public OpalItemBuilder withMods(@NotNull Set<AbstractMod<?, ?>> mods)
    {
        this.mods = mods;
        return this;
    }

    /**
     * @return The {@link OpalItem} built from this builder.
     * @since 0.0.5
     */
    public @NotNull OpalItem build()
    {
        OpalItem item = new OpalItem(this.id, this.fields);

        for (AbstractMod<?, ?> mod : this.mods)
            item.addMod(mod);

        return item;
    }
}
