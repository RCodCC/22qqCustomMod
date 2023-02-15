package me.twotwoqq.cmod.utils;

import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;

import static me.twotwoqq.cmod.Main.MC;

public class EntityUtils {
    public static int getPing(PlayerEntity player) {
        if (MC.getNetworkHandler() == null) return 0;

        PlayerListEntry entry = MC.getNetworkHandler().getPlayerListEntry(player.getUuid());
        if (entry == null) return 0;
        return entry.getLatency();
    }
}