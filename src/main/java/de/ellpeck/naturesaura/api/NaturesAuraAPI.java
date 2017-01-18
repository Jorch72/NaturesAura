package de.ellpeck.naturesaura.api;

import de.ellpeck.naturesaura.api.aura.AuraType;
import de.ellpeck.naturesaura.api.internal.IAuraHandler;
import de.ellpeck.naturesaura.api.internal.IMethodHandler;
import de.ellpeck.naturesaura.api.scroll.IScrollPage;

import java.util.HashMap;
import java.util.Map;

public final class NaturesAuraAPI{

    public static final String MOD_ID = "naturesaura";
    public static final String API_ID = MOD_ID+"api";
    public static final String API_VERSION = "1";

    public static final Map<String, AuraType> AURA_REGISTRY = new HashMap<String, AuraType>();
    public static final Map<String, IScrollPage> SCROLL_PAGE_REGISTRY = new HashMap<String, IScrollPage>();

    public static final AuraType AURA_LIFE = new AuraType("life", 0x3DFFB4).register(); //Overworld
    public static final AuraType AURA_INFINITY = new AuraType("infinity", 0xFFFFFF).register(); //End

    public static IMethodHandler apiHandler;

    public static IAuraHandler getAuraHandler(){
        return apiHandler.getAuraHandler();
    }

    public static void registerAuraType(AuraType type){
        AURA_REGISTRY.put(type.getName(), type);
    }

    public static void registerScrollPage(IScrollPage page){
        SCROLL_PAGE_REGISTRY.put(page.getRegisteredName(), page);
    }
}
