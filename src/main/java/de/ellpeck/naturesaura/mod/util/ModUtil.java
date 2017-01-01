package de.ellpeck.naturesaura.mod.util;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;

public final class ModUtil{

    public static final String MOD_ID = NaturesAuraAPI.MOD_ID;
    public static final String NAME = "Nature's Aura";
    public static final String VERSION = "@VERSION@";

    private static final String PROXY_PATH = "de.ellpeck."+MOD_ID+".proxy.";
    public static final String CLIENT_PROXY = PROXY_PATH+"ClientProxy";
    public static final String SERVER_PROXY = PROXY_PATH+"ServerProxy";
}
