package de.ellpeck.naturesaura.mod.tile;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.capability.AuraSupply;
import de.ellpeck.naturesaura.api.internal.IAuraHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityAncientLeaves extends TileEntityBase implements ITickable{

    private final AuraSupply supply = new AuraSupply(NaturesAuraAPI.AURA_LIFE, 100, 1);

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        this.supply.writeToNBT(compound);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        this.supply.readFromNBT(compound);
        super.readFromNBT(compound);
    }

    @Override
    public void invalidate(){
        super.invalidate();

        if(!this.world.isRemote){
            NaturesAuraAPI.getAuraHandler().removeSupplier(this.world, this.pos);
        }
    }

    @Override
    public void onChunkUnload(){
        super.onChunkUnload();

        if(!this.world.isRemote){
            NaturesAuraAPI.getAuraHandler().removeSupplier(this.world, this.pos);
        }
    }

    @Override
    public void update(){
        if(!this.world.isRemote){
            IAuraHandler handler = NaturesAuraAPI.getAuraHandler();
            if(handler.getSupplier(this.world, this.pos) == null){
                handler.addSupplier(this.world, this.pos, this.supply);
            }
        }
    }
}
