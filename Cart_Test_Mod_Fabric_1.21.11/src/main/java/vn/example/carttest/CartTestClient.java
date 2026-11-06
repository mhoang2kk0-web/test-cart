package vn.example.carttest;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import org.lwjgl.glfw.GLFW;

public class CartTestClient implements ClientModInitializer {
    private static final KeyMapping.Category CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath("cart_test_mod", "main"));

    private static final KeyMapping TEST_KEY = KeyBindingHelper.registerKeyBinding(
            new KeyMapping(
                    "key.cart_test_mod.test",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_V,
                    CATEGORY
            )
    );

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TEST_KEY.consumeClick()) {
                if (client.player != null) {
                    client.player.displayClientMessage(
                            Component.literal("Cart Test Mod: phím V đã hoạt động!"),
                            false
                    );
                }
            }
        });
    }
}
