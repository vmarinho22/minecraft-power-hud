package com.clockmodeui.huds;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public interface IDrawUI {
    void drawInPlayerUI(DrawContext context, MinecraftClient client);
}
