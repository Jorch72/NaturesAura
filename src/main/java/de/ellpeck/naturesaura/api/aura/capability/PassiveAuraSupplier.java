package de.ellpeck.naturesaura.api.aura.capability;

import de.ellpeck.naturesaura.api.aura.AuraType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PassiveAuraSupplier implements IAuraInteractor{

    public final World world;
    public final BlockPos pos;

    private final AuraType type;
    private int amount;
    private final int maxDrain;


    public PassiveAuraSupplier(World world, BlockPos pos, AuraType type, int amount, int maxDrain){
        this.world = world;
        this.pos = pos;
        this.type = type;
        this.amount = amount;
        this.maxDrain = maxDrain;
    }

    @Override
    public int insertAura(AuraType type, int amount, boolean simulate){
        return 0;
    }

    @Override
    public int extractAura(AuraType type, int amount, boolean simulate){
        if(type == this.type){
            int drained = Math.min(amount, Math.min(this.amount, this.maxDrain));

            if(!simulate){
                this.amount -= drained;
            }

            return drained;
        }
        else{
            return 0;
        }
    }

    @Override
    public int getStoredAura(AuraType type){
        return type == this.type ? this.amount : 0;
    }
}
