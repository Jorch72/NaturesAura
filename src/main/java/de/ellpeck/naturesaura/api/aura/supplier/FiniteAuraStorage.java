package de.ellpeck.naturesaura.api.aura.supplier;

import de.ellpeck.naturesaura.api.aura.AuraType;
import de.ellpeck.naturesaura.api.aura.capability.RestrictedAuraStorage;

public class FiniteAuraStorage extends RestrictedAuraStorage{

    public FiniteAuraStorage(AuraType type, int startAmount, int maxExtract){
        super(startAmount, 0, maxExtract, type);
        this.setStoredAura(type, startAmount);
    }
}
