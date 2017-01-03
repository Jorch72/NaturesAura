package de.ellpeck.naturesaura.mod.tile;

import de.ellpeck.naturesaura.mod.packet.PacketHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityBase extends TileEntity{

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState){
        return oldState.getBlock() != newState.getBlock();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        NBTTagCompound tag = super.writeToNBT(compound);
        this.writeSyncedNBT(tag);
        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.readSyncedNBT(compound);
    }

    public void writeSyncedNBT(NBTTagCompound compound){

    }

    public void readSyncedNBT(NBTTagCompound compound){

    }

    @Override
    public final SPacketUpdateTileEntity getUpdatePacket(){
        NBTTagCompound compound = new NBTTagCompound();
        this.writeSyncedNBT(compound);
        return new SPacketUpdateTileEntity(this.pos, 0, compound);
    }

    @Override
    public final NBTTagCompound getUpdateTag(){
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet){
        super.onDataPacket(net, packet);
        this.readSyncedNBT(packet.getNbtCompound());
    }

    public void sendToClient(){
        PacketHandler.dispatchVanilla(this);
    }
}
