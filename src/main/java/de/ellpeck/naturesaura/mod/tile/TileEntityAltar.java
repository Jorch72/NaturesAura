package de.ellpeck.naturesaura.mod.tile;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.capability.AuraStorage;
import de.ellpeck.naturesaura.api.aura.capability.IAuraInteractor;
import de.ellpeck.naturesaura.mod.NaturesAura;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

import java.util.Collections;
import java.util.List;

public class TileEntityAltar extends TileEntityBase implements ITickable{

    public final AuraStorage storage = new AuraStorage(1000, 0, 10);
    private int lastAura;

    @Override
    public void writeSyncedNBT(NBTTagCompound compound){
        this.storage.writeToNBT(compound);
        super.writeSyncedNBT(compound);
    }

    @Override
    public void readSyncedNBT(NBTTagCompound compound){
        this.storage.readFromNBT(compound);
        super.readSyncedNBT(compound);
    }

    @Override
    public void update(){
        if(!this.world.isRemote){
            if(!this.storage.isFull()){
                List<IAuraInteractor> suppliers = NaturesAuraAPI.getAuraHandler().getSuppliersInArea(this.world, this.pos, 15);

                if(!suppliers.isEmpty()){
                    Collections.shuffle(suppliers);

                    for(IAuraInteractor supplier : suppliers){
                        int wouldReceive = supplier.extractAura(supplier.getCurrentType(), 1, true, false);
                        if(wouldReceive > 0){
                            int received = this.storage.insertAura(supplier.getCurrentType(), wouldReceive, false, true);
                            if(received > 0){
                                supplier.extractAura(supplier.getCurrentType(), received, false, false);

                                break;
                            }
                        }
                    }
                }
            }

            int curr = this.storage.getStoredAura();
            if(this.lastAura != curr && this.world.getTotalWorldTime()%40 == 0){
                this.lastAura = curr;

                this.sendToClient();
            }
        }
        else{
            if(this.world.getTotalWorldTime()%60 == 0){
                NaturesAura.proxy.spawnMagicParticle(this.world, this.pos.getX()+0.5, this.pos.getY()+1.5, this.pos.getZ()+0.5, 0, 0.025, 0, 1, 0, 0, 2F, 100);
            }
        }
    }
}
