package com.powerhud;

import com.powerhud.huds.ClockUI;
import com.powerhud.huds.CompassUI;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class PowerHUDClient implements ClientModInitializer {

  private final ClockUI clockUI = new ClockUI();
  private final CompassUI compassUI = new CompassUI();

  @Override
  public void onInitializeClient() {
    MinecraftClient client = MinecraftClient.getInstance();

    HudRenderCallback.EVENT.register((context, tickDelta) -> {
      if (isClientPressingTab(client)) {
        return;
      }
      clockUI.drawInPlayerUI(context, client);
      compassUI.drawInPlayerUI(context, client);
    });
  }

  private boolean isClientPressingTab(MinecraftClient client) {
    return client.options.playerListKey.isPressed();
  }
}
