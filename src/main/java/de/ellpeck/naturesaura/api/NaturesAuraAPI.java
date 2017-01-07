package de.ellpeck.naturesaura.api;

import de.ellpeck.naturesaura.api.aura.AuraType;
import de.ellpeck.naturesaura.api.internal.IAuraHandler;
import de.ellpeck.naturesaura.api.internal.IMethodHandler;

import java.util.HashMap;
import java.util.Map;

public final class NaturesAuraAPI{

    public static final String MOD_ID = "naturesaura";
    public static final String API_ID = MOD_ID+"api";
    public static final String API_VERSION = "1";

    public static final Map<String, AuraType> AURA_REGISTRY = new HashMap<String, AuraType>();

    public static final AuraType AURA_LIFE = new AuraType("life").register(); //Overworld
    public static final AuraType AURA_INFINITY = new AuraType("infinity").register(); //End

    public static IMethodHandler apiHandler;

    public static IAuraHandler getAuraHandler(){
        return apiHandler.getAuraHandler();
    }

    public static void registerAuraType(AuraType type){
        AURA_REGISTRY.put(type.getName(), type);
    }
}
