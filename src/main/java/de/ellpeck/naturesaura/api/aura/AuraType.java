package de.ellpeck.naturesaura.api.aura;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;

public class AuraType{

    protected final String name;

    public AuraType(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public AuraType register(){
        NaturesAuraAPI.registerAuraType(this);
        return this;
    }
}
