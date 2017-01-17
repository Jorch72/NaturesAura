package de.ellpeck.naturesaura.mod.packet;

import de.ellpeck.naturesaura.mod.item.ItemEyeDivine;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSyncAreaAura implements IMessage{

    private int amount;
    private int limit;

    public PacketSyncAreaAura(){

    }

    public PacketSyncAreaAura(int amount, int limit){
        this.amount = amount;
        this.limit = limit;
    }

    @Override
    public void fromBytes(ByteBuf buf){
        this.amount = buf.readInt();
        this.limit = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf){
        buf.writeInt(this.amount);
        buf.writeInt(this.limit);
    }

    public static class Handler implements IMessageHandler<PacketSyncAreaAura, IMessage>{

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketSyncAreaAura message, MessageContext ctx){
            ItemEyeDivine.areaAuraAmount = message.amount;
            ItemEyeDivine.areaAuraLimit = message.limit;

            return null;
        }
    }
}
