package de.ellpeck.naturesaura.mod.packet;

import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public final class PacketHandler{

    public static SimpleNetworkWrapper wrapper;

    public static void init(){
        wrapper = new SimpleNetworkWrapper(ModUtil.MOD_ID);
    }

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
