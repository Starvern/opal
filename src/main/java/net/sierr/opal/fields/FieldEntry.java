package net.sierr.opal.fields;

import net.sierr.opal.Opal;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *     Used for registering a key to a specified {@link AbstractFieldType}.
 *     If you extend this class, you most likely want to
 *     use {@link net.sierr.opal.manager.FieldManager#registerEntry(FieldEntry)}.
 * </p>
 * @param <T> The type of value for {@link AbstractFieldType}.
 * @param <I> The subclass of {@link AbstractFieldType} this entry is for.
 * @since 0.0.4
 */
public class FieldEntry<T, I extends AbstractFieldType<T>>
{
    private final Opal api;
    private final String key;
    private final Class<I> fieldType;

    public FieldEntry(Opal api, String key, Class<I> fieldType)
    {
        this.api = api;
        this.key = key;
        this.fieldType = fieldType;
    }

    /**
     * @return The corresponding key to the provided field.
     * @since 0.0.4
     */
    public String getKey()
    {
        return this.key;
    }

    /**
     * @return The subclass extending {@link AbstractFieldType} this entry is expecting.
     * @since 0.0.4
     */
    public Class<I> getFieldType()
    {
        return this.fieldType;
    }

    /**
     * Constructs a new {@link AbstractFieldType} from a {@link ConfigurationSection}.
     * @param section The section to supply to {@link AbstractFieldType}
     * @return A new {@link AbstractFieldType} or null if the cast fails.
     * @since 0.0.4
     */
    public @Nullable AbstractFieldType<T> construct(ConfigurationSection section)
    {
        try
        {
            return this.fieldType.getConstructor(String.class, ConfigurationSection.class)
                    .newInstance(this.getKey(), section);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e)
        {
            this.api.getLogger().severe("Failed to construct field type from entry: " + this.key);
            return null;
        }
    }
}
