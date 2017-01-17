package de.ellpeck.naturesaura.mod.impl.aura;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.capability.AuraStorage;
import de.ellpeck.naturesaura.api.aura.supplier.FiniteAuraStorage;
import net.minecraft.util.math.BlockPos;

public class SupplierAncientLeaves extends AuraSupplier{

    public SupplierAncientLeaves(BlockPos pos){
        super(pos);
    }

    @Override
    protected AuraStorage createStorage(){
        return new FiniteAuraStorage(NaturesAuraAPI.AURA_LIFE, 10, 1);
    }

    @Override
    public String getRegisteredName(){
        return SupplierRegistry.ANCIENT_LEAVES;
    }
}
