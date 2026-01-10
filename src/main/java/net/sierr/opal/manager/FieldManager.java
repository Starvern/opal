package net.sierr.opal.manager;

import net.sierr.opal.Opal;
import net.sierr.opal.fields.AbstractFieldType;
import net.sierr.opal.fields.FieldEntry;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class FieldManager
{
    private final Opal api;
    private final Set<FieldEntry<?, ?>> entries;

    public FieldManager(Opal api)
    {
        this.api = api;
        this.entries = new HashSet<>();
    }

    /**
     * @param entry The {@link FieldEntry} to register.
     * @param <T> The type of value for {@link AbstractFieldType}.
     * @param <I> The subclass of {@link AbstractFieldType} this entry is for.
     * @since 0.0.4
     */
    public <T, I extends AbstractFieldType<T>> void registerEntry(FieldEntry<T, I> entry)
    {
        this.api.getLogger().info("Registered new field entry: " + entry.getKey());
        this.entries.add(entry);
    }

    /**
     * @return All registered {@link FieldEntry} with this API.
     * @since 0.0.4
     */
    public Set<FieldEntry<?, ?>> getEntries()
    {
        return this.entries;
    }

    /**
     * Attempts to find a registered {@link FieldEntry}.
     * @param key The key to check against {@link FieldEntry#getKey()}.
     * @return The matching {@link FieldEntry}, or null.
     * @since 0.0.4
     */
    public @Nullable FieldEntry<?, ?> getEntryByKey(String key)
    {
        for (FieldEntry<?, ?> entry : this.entries)
        {
            if (entry.getKey().equals(key))
                return entry;
        }
        return null;
    }
}
