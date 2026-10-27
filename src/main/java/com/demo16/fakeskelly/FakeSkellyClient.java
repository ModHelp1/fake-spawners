package com.demo16.fakeskelly;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;

public class FakeSkellyClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Right-click glass with an empty hand -> open the fake GUI (client only)
		UseBlockCallback.EVENT.register((player, world, hand, hit) -> {
			if (!world.isClient()) return ActionResult.PASS;
			if (hand != Hand.MAIN_HAND || player.isSneaking() || !player.getMainHandStack().isEmpty()) return ActionResult.PASS;
			if (!world.getBlockState(hit.getBlockPos()).isOf(Blocks.GLASS)) return ActionResult.PASS;
			MinecraftClient.getInstance().setScreen(new FakeSkellyScreen());
			return ActionResult.SUCCESS;
		});

		// Fake spawner tooltip on glass items
		ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
			if (!stack.isOf(Items.GLASS)) return;
			lines.set(0, Text.literal("Spawner").formatted(Formatting.LIGHT_PURPLE, Formatting.BOLD));
			lines.add(Text.literal("Skeleton").formatted(Formatting.YELLOW, Formatting.BOLD));
			lines.add(Text.literal("~$ ").formatted(Formatting.GREEN, Formatting.BOLD)
				.append(Text.literal("1K").formatted(Formatting.WHITE, Formatting.BOLD)));
			lines.add(Text.empty());
			lines.add(Text.literal("Interact with Spawn Egg:").formatted(Formatting.GRAY, Formatting.BOLD));
			lines.add(Text.literal("Sets Mob Type").formatted(Formatting.BLUE, Formatting.BOLD));
		});
	}
}
