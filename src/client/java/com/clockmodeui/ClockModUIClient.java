package com.clockmodeui;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ClockModUIClient implements ClientModInitializer {
	private static final int TICKS_PER_HOUR = 1000;
	private static final int MAGIC_NUMBER_DIVISION_CONSTANT_FOR_CLOCK = 375;


	@Override
	public void onInitializeClient() {
		MinecraftClient client = MinecraftClient.getInstance();

		HudRenderCallback.EVENT.register((context, tickDelta) -> {
			drawClock(context, client);
		});
	}

	private void drawClock(DrawContext context, MinecraftClient client) {

		if (!doesClientHaveClock(client)) {
			return;
		}

		if (client.world == null) {
			return;
		}

		long time = client.world.getTimeOfDay() % 24000;
		int textureTime = (int) (time / MAGIC_NUMBER_DIVISION_CONSTANT_FOR_CLOCK);
		int hours = (int) ((time / TICKS_PER_HOUR + 6) % 24); // In-game time starts at 6 AM
		int minutes = (int) ((time % TICKS_PER_HOUR) * 60 / TICKS_PER_HOUR);

		String timeString = String.format("%02d:%02d", hours, minutes);
		int width = client.getWindow().getScaledWidth();

		String formatedTexturePathWithCorrectTime = String.format("textures/item/clock_%02d.png", textureTime);
		int textWidth = client.textRenderer.getWidth(timeString);

		Identifier texture = new Identifier("minecraft", formatedTexturePathWithCorrectTime);

		context.drawTexture(texture, ((width - textWidth)  / 2) - 14, 8, 0, 0, 12, 12, 12, 12);

		context.drawTextWithShadow(client.textRenderer, Text.literal(timeString).formatted(Formatting.YELLOW), ((width - textWidth)  / 2) , 10, 0xFFFFFFFF);
	}

	private boolean doesClientHaveClock(MinecraftClient client) {
		// check if client has a clock item in their inventory
		if (client.player == null) {
			return false;
		}

		return client.player.getInventory().contains(new ItemStack(Items.CLOCK));
	}
}