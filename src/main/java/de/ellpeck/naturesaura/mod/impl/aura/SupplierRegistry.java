package de.ellpeck.naturesaura.mod.impl.aura;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;

public final class SupplierRegistry{

    public static final String ANCIENT_LEAVES = "ancient_leaves";

    public static void preInit(){
        NaturesAuraAPI.registerAuraSupplier(ANCIENT_LEAVES, SupplierAncientLeaves.class);
    }

}
