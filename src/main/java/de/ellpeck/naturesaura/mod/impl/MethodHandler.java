package de.ellpeck.naturesaura.mod.impl;

import de.ellpeck.naturesaura.api.internal.IAuraHandler;
import de.ellpeck.naturesaura.api.internal.IMethodHandler;

public class MethodHandler implements IMethodHandler{

    private final IAuraHandler auraHandler = new AuraHandler();

    @Override
    public IAuraHandler getAuraHandler(){
        return this.auraHandler;
    }
}
