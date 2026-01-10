package net.sierr.opal.mod;

import net.sierr.opal.item.AbstractField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <p>
 *     Represents a modification to an {@link AbstractField}. This has a most direct application
 *     with {@link net.sierr.opal.item.OpalItem}. A modification should not change an {@link AbstractField},
 *     only return a modified version.
 * </p>
 * @param <T> The same type of {@link AbstractField} to change.
 * @since 0.0.3
 */
public abstract class AbstractMod<T>
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

    /**
     * @return The {@link Class} of the type of value this mod will modify.
     * @since 0.0.3
     */
    public abstract Class<T> getType();

    /**
     * Returns an {@link AbstractField} which is built by an operation by this modification.
     * @return The changed {@link AbstractField}.
     * @since 0.0.3
     */
    public @NotNull AbstractField<T> applyMod()
    {
        return this.applyMod(null);
    }

    /**
     * Returns an {@link AbstractField} which is built by an operation by this modification.
     * @param field The {@link AbstractField} to change / original. May be null if not provided.
     * @return The changed {@link AbstractField}.
     * @since 0.0.3
     */
    public abstract @NotNull AbstractField<T> applyMod(@Nullable AbstractField<?> field);
}
