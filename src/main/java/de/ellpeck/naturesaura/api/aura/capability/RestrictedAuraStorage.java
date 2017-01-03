package de.ellpeck.naturesaura.api.aura.capability;

import de.ellpeck.naturesaura.api.aura.AuraType;

public class RestrictedAuraStorage extends AuraStorage{

    protected final AuraType restriction;

    public RestrictedAuraStorage(int maxTotalAmount, int maxInsert, int maxExtract, AuraType restriction){
        super(maxTotalAmount, maxInsert, maxExtract);
        this.restriction = restriction;
    }

    @Override
    protected boolean fitsType(AuraType type){
        return type == this.restriction && super.fitsType(type);
    }
}
