package net.sierr.opal;

import net.sierr.opal.fields.*;
import net.sierr.opal.fields.impl.AttackDamageFieldType;
import net.sierr.opal.fields.impl.LoreFieldType;
import net.sierr.opal.fields.impl.MaterialFieldType;
import net.sierr.opal.fields.impl.NameFieldType;
import net.sierr.opal.manager.FieldManager;
import net.sierr.opal.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.ServicePriority;

import java.util.Set;
import java.util.logging.Logger;

public class Opal
{
    private final OpalPlugin plugin;
    private final Logger logger;

    private final FieldManager fieldManager;
    private final ItemManager itemManager;

    public Opal(OpalPlugin plugin)
    {
        this.plugin = plugin;
        this.logger = Logger.getLogger("OpalAPI");

        this.fieldManager = new FieldManager(this);
        this.itemManager = new ItemManager(this);
    }

    /**
     * @param key The key in the namespace "opal".
     * @return A new {@link NamespacedKey}.
     * @since 0.0.5
     */
    public static NamespacedKey getKey(String key)
    {
        return new NamespacedKey("opal", key);
    }

    /**
     * <p>
     *     Adds this API to Bukkit's {@link org.bukkit.plugin.ServicesManager}.
     *     Also performs {@link Opal#registerProvidedFields()}.
     * </p>
     * @since 0.0.4
     */
    public void register()
    {
        Bukkit.getServicesManager().register(
                Opal.class,
                this,
                this.plugin,
                ServicePriority.Normal
        );

        // Load fields first.
        this.registerProvidedFields();
        this.itemManager.loadItems();
    }

    /**
     * Removes this API from Bukkit's {@link org.bukkit.plugin.ServicesManager}.
     * @since 0.0.4
     */
    public void unregister()
    {
        Bukkit.getServicesManager().unregister(this);
    }

    /**
     * Registers all {@link FieldEntry}(s) provided by default in this API.
     * @since 0.0.4
     */
    private void registerProvidedFields()
    {
        Set<FieldEntry<?, ?>> entries = Set.of(
                new FieldEntry<>(this, "material", MaterialFieldType.class),
                new FieldEntry<>(this, "name", NameFieldType.class),
                new FieldEntry<>(this, "lore", LoreFieldType.class),
                new FieldEntry<>(this, "attack_damage", AttackDamageFieldType.class)
        );

        for (FieldEntry<?, ?> entry : entries)
        {
            this.fieldManager.registerEntry(entry);
        }
    }

    /**
     * @return The instance of {@link OpalPlugin} for this API.
     * @since 0.0.2
     */
    public OpalPlugin getPlugin()
    {
        return this.plugin;
    }

    /**
     * @return The instance of {@link Logger} for this API.
     * @since 0.0.4
     */
    public Logger getLogger()
    {
        return this.logger;
    }

    /**
     * @return The instance of {@link FieldManager} for this API.
     * @since 0.0.4
     */
    public FieldManager getFieldManager()
    {
        return fieldManager;
    }

    /**
     * @return The instance of {@link ItemManager} for this API.
     * @since 0.0.4
     */
    public ItemManager getItemManager()
    {
        return itemManager;
    }
}
