package com.demo16.fakeskelly;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

public class FakeSkellyScreen extends Screen {
	public FakeSkellyScreen() {
		super(Text.literal("1 Skeleton Spawner"));
	}

	@Override
	protected void init() {
		int cx = this.width / 2;
		int y = this.height / 2 + 20;
		addDrawableChild(ButtonWidget.builder(Text.empty(), b -> say("Sell: fake, nothing happens")).dimensions(cx - 40, y, 20, 20).build());
		addDrawableChild(ButtonWidget.builder(Text.empty(), b -> say("Drops: fake, nothing happens")).dimensions(cx - 10, y, 20, 20).build());
		addDrawableChild(ButtonWidget.builder(Text.empty(), b -> say("Storage: fake, nothing happens")).dimensions(cx + 20, y, 20, 20).build());
	}

	private void say(String msg) {
		MinecraftClient mc = MinecraftClient.getInstance();
		if (mc.player != null) mc.player.sendMessage(Text.literal(msg), true);
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		super.render(context, mouseX, mouseY, delta);
		int cx = this.width / 2;
		int y = this.height / 2 + 20;
		context.drawCenteredTextWithShadow(this.textRenderer, this.title, cx, this.height / 2 - 20, 0xFFFFFFFF);
		context.drawItem(new ItemStack(Items.GOLD_INGOT), cx - 38, y + 2);
		context.drawItem(new ItemStack(Items.SKELETON_SKULL), cx - 8, y + 2);
		context.drawItem(new ItemStack(Items.DROPPER), cx + 22, y + 2);
	}
}
