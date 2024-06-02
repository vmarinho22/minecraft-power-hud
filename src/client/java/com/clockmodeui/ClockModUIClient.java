package com.clockmodeui;

import com.clockmodeui.huds.ClockUI;
import com.clockmodeui.huds.CompassUI;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class ClockModUIClient implements ClientModInitializer {
  private final ClockUI clockUI = new ClockUI();
  private final CompassUI compassUI = new CompassUI();

  @Override
  public void onInitializeClient() {
    MinecraftClient client = MinecraftClient.getInstance();

    HudRenderCallback.EVENT.register((context, tickDelta) -> {
      clockUI.drawInPlayerUI(context, client);
      compassUI.drawInPlayerUI(context, client);
    });
  }
}
