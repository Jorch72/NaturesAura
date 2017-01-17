package de.ellpeck.naturesaura.mod.impl.aura;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.AuraType;
import de.ellpeck.naturesaura.api.aura.capability.AuraStorage;
import de.ellpeck.naturesaura.api.aura.capability.IAuraStorage;
import de.ellpeck.naturesaura.api.aura.supplier.IAuraSupplier;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AuraSupplier implements IAuraSupplier{

    private int lastAmount;
    private final AuraStorage storage = this.createStorage();
    private final BlockPos pos;

    public AuraSupplier(BlockPos pos){
        this.pos = pos;
    }

    protected abstract AuraStorage createStorage();

    @Override
    public BlockPos getPos(){
        return this.pos;
    }

    @Override
    public IAuraStorage getSupply(){
        return this.storage;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        this.storage.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        this.storage.readFromNBT(compound);
    }

    @Override
    public void toPacket(PacketBuffer buffer){
        buffer.writeString(this.storage.getCurrentType().getRegisteredName());
        buffer.writeInt(this.storage.getStoredAura());
    }

    @Override
    public void fromPacket(PacketBuffer buffer){
        AuraType type = NaturesAuraAPI.AURA_REGISTRY.get(buffer.readString(32));
        this.storage.setStoredAura(type, buffer.readInt());
    }

    @Override
    public boolean shouldSync(World world){
        int amount = this.storage.getStoredAura();

        if(this.lastAmount != amount){
            this.lastAmount = amount;
            return true;
        }
        else{
            return false;
        }
    }
}
