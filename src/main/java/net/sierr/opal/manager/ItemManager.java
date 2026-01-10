package net.sierr.opal.manager;

import net.sierr.opal.Opal;
import net.sierr.opal.fields.AbstractFieldType;
import net.sierr.opal.fields.FieldEntry;
import net.sierr.opal.item.OpalItem;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ItemManager
{
    private final Opal api;
    private final File itemFolder;
    private final Set<OpalItem> items;

    public ItemManager(Opal api)
    {
        this.api = api;
        this.itemFolder = new File(this.api.getPlugin().getDataFolder(), "items");

        if (!this.itemFolder.exists() && this.itemFolder.mkdirs())
            this.api.getLogger().info("Created items directory.");

        this.items = new HashSet<>();
    }

    /**
     * Load an {@link OpalItem} from a {@link ConfigurationSection}.
     * @param key The key / id of the new {@link OpalItem}.
     * @param section The {@link ConfigurationSection} to pull data from.
     * @return A new {@link OpalItem}.
     * @since 0.0.4
     */
    public OpalItem loadItem(@NotNull String key, @NotNull ConfigurationSection section)
    {
        Set<AbstractFieldType<?>> fields = new HashSet<>();

        for (String fieldKey : section.getKeys(false))
        {
            @Nullable FieldEntry<?, ?> entry = this.api.getFieldManager().getEntryByKey(fieldKey);
            if (entry == null)
                continue;

            fields.add(entry.construct(section));
        }

        return new OpalItem(key, fields);
    }

    /**
     * Loads all possible items in the {@link File}.
     * @param file The {@link File} to pull data from.
     * @return All {@link OpalItem}(s) within the {@link File}.
     * @since 0.0.4
     */
    public Set<OpalItem> loadItems(File file)
    {
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Set<OpalItem> items = new HashSet<>();

        for (String key : config.getKeys(false))
        {
            @Nullable ConfigurationSection section = config.getConfigurationSection(key);

            if (section == null)
            {
                this.api.getLogger().warning("Invalid key '" + key + "' in /items/" + file.getName());
                continue;
            }

            items.add(loadItem(key, section));
        }

        return items;
    }

    /**
     * Loads all {@link OpalItem}(s) found within the /items/ folder.
     * @since 0.0.4
     */
    public void loadItems()
    {
        this.items.clear();

        File [] itemFiles = itemFolder.listFiles();

        if (itemFiles == null)
        {
            this.api.getLogger().warning("No items in directory /items/");
            return;
        }

        for (File itemFile : itemFiles)
        {
            this.items.addAll(loadItems(itemFile));
        }
    }

    /**
     * @return All {@link OpalItem}(s) registered in the API.
     * @since 0.0.4
     */
    public Set<OpalItem> getItems()
    {
        return this.items;
    }

    /**
     * Manually register an {@link OpalItem}.
     * @param item The {@link OpalItem} to register.
     * @since 0.0.4
     */
    public void addItem(OpalItem item)
    {
        this.items.add(item);
    }
}
