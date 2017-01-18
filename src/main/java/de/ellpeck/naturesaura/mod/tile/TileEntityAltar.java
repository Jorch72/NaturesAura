package de.ellpeck.naturesaura.mod.tile;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.AuraType;
import de.ellpeck.naturesaura.api.aura.capability.AuraStorage;
import de.ellpeck.naturesaura.api.aura.capability.IAuraStorage;
import de.ellpeck.naturesaura.mod.NaturesAura;
import de.ellpeck.naturesaura.mod.packet.PacketHandler;
import de.ellpeck.naturesaura.mod.packet.PacketParticleStream;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

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
                List<BlockPos> suppliers = NaturesAuraAPI.getAuraHandler().getSupplierPositionsInArea(this.world, this.pos, 15);

                if(!suppliers.isEmpty()){
                    Collections.shuffle(suppliers);

                    for(BlockPos pos : suppliers){
                        IAuraStorage supplier = NaturesAuraAPI.getAuraHandler().getSupplier(this.world, pos);

                        AuraType type = supplier.getCurrentType();
                        int wouldReceive = supplier.extractAura(type, 1, true, false);
                        if(wouldReceive > 0){
                            int received = this.storage.insertAura(type, wouldReceive, false, true);
                            if(received > 0){
                                supplier.extractAura(type, received, false, false);

                                IMessage packet = new PacketParticleStream(pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, this.pos.getX()+0.5, this.pos.getY()+0.5, this.pos.getZ()+0.5, 0.04, type.getColor(), 0.5F);
                                PacketHandler.sendToAllAround(this.world, this.pos, packet);

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
            if(this.world.getTotalWorldTime()%10 == 0){
                float amountRatio = (float)this.storage.getStoredAura()/(float)this.storage.getAuraLimit();

                if(amountRatio > 0){
                    for(int x = 0; x <= 1; x++){
                        for(int z = 0; z <= 1; z++){
                            if(this.world.rand.nextFloat() <= 0.75F*amountRatio){
                                double startX = x == 0 ? 0.28125 : 0.71875;
                                double startZ = z == 0 ? 0.28125 : 0.71875;

                                double posX = this.pos.getX()+startX+this.world.rand.nextGaussian()*0.02;
                                double posY = this.pos.getY()+1.2+this.world.rand.nextGaussian()*0.02;
                                double posZ = this.pos.getZ()+startZ+this.world.rand.nextGaussian()*0.02;

                                NaturesAura.proxy.spawnMagicParticle(this.world, posX, posY, posZ, 0, 0.01, 0, 0xFF6A00, amountRatio*0.5F, this.world.rand.nextInt(50)+30, 0F, false);
                            }
                        }
                    }
                }
            }
        }
    }
}
