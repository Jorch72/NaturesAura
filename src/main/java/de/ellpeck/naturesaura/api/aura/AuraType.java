package de.ellpeck.naturesaura.api.aura;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;

public class AuraType{

    protected final int color;
    protected final String name;

    public AuraType(String name, int color){
        this.name = name;
        this.color = color;
    }

    public String getRegisteredName(){
        return this.name;
    }

    public int getColor(){
        return this.color;
    }

    public AuraType register(){
        NaturesAuraAPI.registerAuraType(this);
        return this;
    }
}
