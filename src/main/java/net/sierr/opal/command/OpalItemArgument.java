package net.sierr.opal.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import io.papermc.paper.command.brigadier.argument.CustomArgumentType;
import net.kyori.adventure.text.Component;
import net.sierr.opal.Opal;
import net.sierr.opal.item.OpalItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class OpalItemArgument implements CustomArgumentType.Converted<OpalItem, String>
{
    private final Opal api;

    public OpalItemArgument(Opal api)
    {
        this.api = api;
    }

    @Override
    public @NotNull OpalItem convert(@NotNull String nativeType) throws CommandSyntaxException
    {
        @Nullable OpalItem item = this.api.getItemManager().getItem(nativeType);

        if (item == null)
            throw new DynamicCommandExceptionType(itemId ->
                    MessageComponentSerializer.message()
                            .serialize(
                                    Component.text("Invalid item ID: " + itemId)
                            )
            ).create(nativeType);

        return item;
    }

    @Override
    public <S> @NotNull CompletableFuture<Suggestions> listSuggestions(
            @NotNull CommandContext<S> context,
            @NotNull SuggestionsBuilder builder
    ) {
        for (OpalItem item : this.api.getItemManager().getItems())
        {
            if (item.getId().startsWith(builder.getRemainingLowerCase()))
                builder.suggest(item.getId());
        }

        return builder.buildFuture();
    }

    @Override
    public @NotNull ArgumentType<String> getNativeType()
    {
        return StringArgumentType.word();
    }
}
