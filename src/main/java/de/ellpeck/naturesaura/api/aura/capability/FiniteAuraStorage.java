package de.ellpeck.naturesaura.api.aura.capability;

import de.ellpeck.naturesaura.api.aura.AuraType;

public class FiniteAuraStorage extends RestrictedAuraStorage{

    public FiniteAuraStorage(AuraType type, int startAmount, int maxExtract){
        super(startAmount, 0, maxExtract, type);
        this.setStoredAura(type, startAmount);
    }

    @Override
    public int insertAura(AuraType type, int amount, boolean simulate, boolean doInternal){
        return 0;
    }
}
