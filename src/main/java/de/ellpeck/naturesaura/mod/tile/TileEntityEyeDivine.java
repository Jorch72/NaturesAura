package de.ellpeck.naturesaura.mod.tile;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.capability.IAuraStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class TileEntityEyeDivine extends TileEntityBase implements ITickable{

    public int currentAmount = -1;
    public int maxAmount = -1;

    @Override
    public void readSyncedNBT(NBTTagCompound compound){
        super.readSyncedNBT(compound);

        this.currentAmount = compound.getInteger("Curr");
        this.maxAmount = compound.getInteger("Max");
    }

    @Override
    public void writeSyncedNBT(NBTTagCompound compound){
        super.writeSyncedNBT(compound);

        compound.setInteger("Curr", this.currentAmount);
        compound.setInteger("Max", this.maxAmount);
    }

    @Override
    public void update(){
        if(!this.world.isRemote){
            if(this.world.getTotalWorldTime()%20 == 0 || this.currentAmount == -1 || this.maxAmount == -1){
                this.currentAmount = 0;
                this.maxAmount = 0;

                List<BlockPos> suppliers = NaturesAuraAPI.getAuraHandler().getSupplierPositionsInArea(this.world, this.pos, 15);
                for(BlockPos pos : suppliers){
                    IAuraStorage supplier = NaturesAuraAPI.getAuraHandler().getSupplier(this.world, pos);
                    if(supplier != null){
                        this.currentAmount += supplier.getStoredAura();
                        this.maxAmount += supplier.getAuraLimit();
                    }
                }

                this.sendToClient();
            }
        }
    }
}
