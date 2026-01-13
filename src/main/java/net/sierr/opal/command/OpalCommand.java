package net.sierr.opal.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.sierr.opal.Opal;
import net.sierr.opal.item.OpalItem;
import org.bukkit.entity.Player;

public class OpalCommand
{
    public static LiteralArgumentBuilder<CommandSourceStack> createCommand(Opal api)
    {
        return Commands.literal("opal")
                .requires(source ->
                        source.getSender().hasPermission("opal.command")
                )
                .then(
                        giveCommand(api)
                );
    }

    private static LiteralArgumentBuilder<CommandSourceStack> giveCommand(Opal api)
    {
        return Commands.literal("give")
                .then(
                        Commands.argument("item", new OpalItemArgument(api))
                                .executes(OpalCommand::performGive)
                );
    }

    private static int performGive(CommandContext<CommandSourceStack> context)
    {
        OpalItem item = context.getArgument("item", OpalItem.class);

        if (!(context.getSource().getSender() instanceof Player player))
        {
            context.getSource().getSender().sendMessage("You must be a player to use this command.");
            return Command.SINGLE_SUCCESS;
        }

        player.give(item.buildItemStack());
        player.sendMessage("Given item: " + item.getId());

        return Command.SINGLE_SUCCESS;
    }
}
