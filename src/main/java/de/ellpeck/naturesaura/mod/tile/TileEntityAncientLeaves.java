package de.ellpeck.naturesaura.mod.tile;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.capability.AuraSupply;
import de.ellpeck.naturesaura.api.internal.IAuraHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityAncientLeaves extends TileEntityBase implements ITickable{

    public final AuraSupply supply = new AuraSupply(NaturesAuraAPI.AURA_LIFE, 100, 1);
    private int lastAura;

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
            NaturesAuraAPI.getAuraHandler().removeSupplier(this.world, this.pos, true);
        }
    }

    @Override
    public void onChunkUnload(){
        super.onChunkUnload();

        if(!this.world.isRemote){
            NaturesAuraAPI.getAuraHandler().removeSupplier(this.world, this.pos, true);
        }
    }

    @Override
    public void update(){
        if(!this.world.isRemote){
            this.handleSupplying();

            if(this.supply.getStoredAura() > 0 && this.world.getTotalWorldTime()%10 == 0){
                this.supply.setStoredAura(this.supply.getStoredAura()-1);
            }

            int curr = this.supply.getStoredAura();
            if(this.lastAura != curr && this.world.getTotalWorldTime()%40 == 0){
                this.lastAura = curr;

                NaturesAuraAPI.getAuraHandler().sendSupplierToClient(this.world, this.pos, false);
            }
        }
    }

    private void handleSupplying(){
        IAuraHandler handler = NaturesAuraAPI.getAuraHandler();
        boolean hasSupplier = handler.getSupplier(this.world, this.pos) != null;

        int meta = this.getBlockMetadata();
        if(meta == 0 || meta == 1){ //Decayable
            if(!hasSupplier){
                handler.addSupplier(this.world, this.pos, this.supply, true);
                this.lastAura = this.supply.getStoredAura();
            }
        }
        else if(hasSupplier){
            handler.removeSupplier(this.world, this.pos, true);
        }
    }
}
