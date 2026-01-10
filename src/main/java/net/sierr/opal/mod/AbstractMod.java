package net.sierr.opal.mod;

import net.sierr.opal.fields.AbstractFieldType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <p>
 *     Represents a modification to an {@link AbstractFieldType}. This has a most direct application
 *     with {@link net.sierr.opal.item.OpalItem}. A modification should not change an {@link AbstractFieldType},
 *     only return a modified version.
 * </p>
 * @param <T> The same type of {@link AbstractFieldType} to change.
 * @since 0.0.3
 */
public abstract class AbstractMod<T, I extends AbstractFieldType<T>>
{
    protected final String id;

    public AbstractMod(String id)
    {
        this.id = id;
    }

    /**
     * @return The ID of this modification.
     * @since 0.0.3
     */
    public @NotNull String getId()
    {
        return this.id;
    }

    public @Nullable I castOrNull(@Nullable AbstractFieldType<?> fieldType)
    {
        if (fieldType != null && this.getFieldType().isInstance(fieldType))
            return this.getFieldType().cast(fieldType);
        return null;
    }

    /**
     * Returns an {@link AbstractFieldType} which is built by an operation by this modification.
     * @return The changed {@link AbstractFieldType}.
     * @since 0.0.3
     */
    public @NotNull I applyMod()
    {
        return this.applyMod(null);
    }

    /**
     * Returns an {@link AbstractFieldType} which is built by an operation by this modification.
     * @param field The {@link AbstractFieldType} to change / original. May be null if not provided.
     * @return The changed {@link AbstractFieldType}.
     * @since 0.0.3
     */
    public @NotNull I applyMod(@Nullable AbstractFieldType<?> field)
    {
        return this.executeApplication(this.castOrNull(field));
    }

    /**
     * @return The {@link Class} of the type of value this mod will modify.
     * @since 0.0.4
     */
    public abstract Class<I> getFieldType();

    /**
     * @param field The properly cast {@link AbstractFieldType}, or null if the cast failed.
     * @return A new {@link AbstractFieldType} which is built by the operation of this modification.
     * @since 0.0.4
     */
    public abstract @NotNull I executeApplication(@Nullable I field);
}
