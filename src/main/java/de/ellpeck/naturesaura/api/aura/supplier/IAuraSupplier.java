package de.ellpeck.naturesaura.api.aura.supplier;

import de.ellpeck.naturesaura.api.aura.capability.IAuraStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IAuraSupplier{

    String getRegisteredName();

    BlockPos getPos();

    IAuraStorage getSupply();

    void writeToNBT(NBTTagCompound compound);

    void readFromNBT(NBTTagCompound compound);

    void toPacket(PacketBuffer buffer);

    void fromPacket(PacketBuffer buffer);

    boolean shouldSync(World world);
}
