package com.clockmodeui.huds;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Arrays;

public class CompassUI implements IDrawUI {
    public void drawInPlayerUI(DrawContext context, MinecraftClient client) {
        if (client.world == null) {
            return;
        }

        if (!doesPlayerHaveCompass(client)) {
            return;
        }

        String[] directionsArray = new String[] {
                "S",
                "SW",
                "W",
                "NW",
                "N",
                "NE",
                "E",
                "SE",
        };

        float directionDegree = getPlayerLookingDirection(client);
        int directionIndex = (int) Math.floor((directionDegree + 22.5) / 45) % 8;

        String direction = directionsArray[directionIndex];
        String[] newDirectionsArray = rearrangeArray(directionsArray, direction);

        String compassText = String.join("    |    ", newDirectionsArray);

        int textWidth = client.textRenderer.getWidth(compassText);

        int width = client.getWindow().getScaledWidth();

        context.drawTextWithShadow(
                client.textRenderer,
                Text.literal(compassText).formatted(Formatting.YELLOW),
                ((width - textWidth) / 2),
                22,
                0xFFFFFFFF
        );
    }

    private boolean doesPlayerHaveCompass(MinecraftClient client) {
        if (client.player == null) {
            return false;
        }

        return client.player.getInventory().contains(new ItemStack(Items.COMPASS));
    }

    private float getPlayerLookingDirection(MinecraftClient client) {
        if (client.player == null) {
            return 0;
        }

        float yaw = client.player.getYaw();
        return (yaw % 360 + 360) % 360;
    }

    public static String[] rearrangeArray(
            String[] array,
            String selectedElement
    ) {
        int n = array.length;
        int selectedIndex = -1;

        for (int i = 0; i < n; i++) {
            if (array[i].equals(selectedElement)) {
                selectedIndex = i;
                break;
            }
        }

        if (selectedIndex == -1) {
            throw new IllegalArgumentException(
                    "Selected element not found in the array."
            );
        }

        String[] newArray = new String[n];
        int middleIndex = n / 2;

        for (int i = 0; i < n; i++) {
            int newIndex = (middleIndex + i) % n;
            newArray[newIndex] = array[(selectedIndex + i) % n];
        }

        return Arrays.copyOfRange(newArray, 1, newArray.length);
    }
}
