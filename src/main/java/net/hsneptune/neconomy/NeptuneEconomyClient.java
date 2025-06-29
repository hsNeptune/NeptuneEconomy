package net.hsneptune.neconomy;

import net.hsneptune.neconomy.NeptuneEconomy;
import net.fabricmc.api.ClientModInitializer;

public class NeptuneEconomyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NeptuneEconomy.LOGGER.info("Client Mod Initialized!");
    }
}
