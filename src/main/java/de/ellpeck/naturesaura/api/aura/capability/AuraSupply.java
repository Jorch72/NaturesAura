package de.ellpeck.naturesaura.api.aura.capability;

import de.ellpeck.naturesaura.api.aura.AuraType;

public class AuraSupply extends AuraStorage{

    public AuraSupply(AuraType type, int startAmount, int maxExtract){
        super(startAmount, 0, maxExtract, type);
        this.setStoredAura(type, startAmount);
    }
}
