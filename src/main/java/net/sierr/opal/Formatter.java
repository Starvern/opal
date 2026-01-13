package net.sierr.opal;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Formatter
{
    /**
     * @param input The {@link String} to colorize.
     * @param resolvers Any {@link TagResolver}(s) to parse.
     * @return The string as a {@link Component}.
     * @since 0.0.5
     */
    public static @NotNull Component colorize(@NotNull String input, @NotNull TagResolver ... resolvers)
    {
        return MiniMessage.miniMessage()
                .deserialize(input, resolvers)
                .decoration(TextDecoration.ITALIC, false);
    }

    /**
     * @param input The {@link String} to colorize.
     * @param resolvers Any {@link TagResolver}(s) to parse.
     * @return The string as a {@link Component}.
     * @since 0.0.5
     */
    public static @NotNull List<Component> colorize(@NotNull List<String> input, @NotNull TagResolver ... resolvers)
    {
        return input.stream()
                .map(in -> colorize(in, resolvers))
                .toList();
    }

    /**
     * @param input The {@link String} to colorize.
     * @return The string as a {@link Component}.
     * @since 0.0.5
     */
    public static @NotNull Component colorize(@NotNull String input)
    {
        return colorize(input, new TagResolver[0]);
    }

    /**
     * @param input The {@link String} to colorize.
     * @return The string as a {@link Component}.
     * @since 0.0.5
     */
    public static @NotNull List<Component> colorize(@NotNull List<String> input)
    {
        return colorize(input, new TagResolver[0]);
    }
}
