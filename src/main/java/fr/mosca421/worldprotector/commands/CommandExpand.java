package fr.mosca421.worldprotector.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import fr.mosca421.worldprotector.utils.ExpandUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.item.ItemStack;

public class CommandExpand {

	public static LiteralArgumentBuilder<CommandSource> register() {
		return Commands.literal("expand").requires(cs -> cs.hasPermissionLevel(4))
				.executes(ctx -> giveHelp(ctx.getSource()))
				.then(Commands.literal("help")
						.executes(ctx -> giveHelp(ctx.getSource())))
				.then(Commands.literal("vert").executes(ctx -> vert(ctx.getSource())));
	}

	private static int giveHelp(CommandSource source) {
		try {
			ExpandUtils.giveHelpMessage(source.asPlayer());
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private static int vert(CommandSource source) {
		try {
			ItemStack item = source.asPlayer().getHeldItemMainhand();
			ExpandUtils.expandVert(source.asPlayer(), item);
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
