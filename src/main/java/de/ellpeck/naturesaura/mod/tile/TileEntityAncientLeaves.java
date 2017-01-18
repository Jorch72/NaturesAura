package de.ellpeck.naturesaura.mod.tile;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.capability.FiniteAuraStorage;
import de.ellpeck.naturesaura.mod.block.BlockRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityAncientLeaves extends TileEntityBase implements ITickable{

    public final FiniteAuraStorage supply = new FiniteAuraStorage(NaturesAuraAPI.AURA_LIFE, 10, 1);
    private int lastAura;

    @Override
    public void writeSyncedNBT(NBTTagCompound compound){
        this.supply.writeToNBT(compound);
        super.writeSyncedNBT(compound);
    }

    @Override
    public void readSyncedNBT(NBTTagCompound compound){
        this.supply.readFromNBT(compound);
        super.readSyncedNBT(compound);
    }

    @Override
    public void invalidate(){
        super.invalidate();

        NaturesAuraAPI.getAuraHandler().removeSupplier(this.world, this.pos);
    }

    @Override
    public void onChunkUnload(){
        super.onChunkUnload();

        NaturesAuraAPI.getAuraHandler().removeSupplier(this.world, this.pos);
    }

    @Override
    public void update(){
        if(NaturesAuraAPI.getAuraHandler().getSupplier(this.world, this.pos) == null){
            NaturesAuraAPI.getAuraHandler().addSupplier(this.world, this.pos, this.supply);
        }

        if(!this.world.isRemote){
            int curr = this.supply.getStoredAura();

            if(curr <= 0){
                this.world.setBlockState(this.pos, BlockRegistry.blockDecayedLeaves.getDefaultState(), 2);
            }
            else if(this.lastAura != curr && this.world.getTotalWorldTime()%40 == 0){
                this.lastAura = curr;

                this.sendToClient();
            }
        }
    }
}
