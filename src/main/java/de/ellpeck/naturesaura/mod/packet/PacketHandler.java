package de.ellpeck.naturesaura.mod.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class PacketHandler{

    public static void dispatchVanilla(TileEntity tile){
        World world = tile.getWorld();
        if(!world.isRemote){
            SPacketUpdateTileEntity packet = tile.getUpdatePacket();
            if(packet != null){
                BlockPos tilePos = tile.getPos();

                for(EntityPlayer player : world.playerEntities){
                    if(player instanceof EntityPlayerMP){
                        if(Math.hypot(player.posX-(tilePos.getX()+0.5), player.posZ-(tilePos.getZ()+0.5)) <= 64){
                            ((EntityPlayerMP)player).connection.sendPacket(packet);
                        }
                    }
                }
            }
        }
    }
}
