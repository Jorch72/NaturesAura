package de.ellpeck.naturesaura.mod.packet;

import com.google.common.base.Charsets;
import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.AuraType;
import de.ellpeck.naturesaura.api.aura.capability.AuraSupply;
import de.ellpeck.naturesaura.api.aura.capability.IAuraInteractor;
import de.ellpeck.naturesaura.api.internal.IAuraHandler;
import de.ellpeck.naturesaura.mod.NaturesAura;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSendAuraSupplier implements IMessage{

    private boolean remove;
    private BlockPos pos;
    private AuraType type;
    private int startAmount;
    private int maxExtract;
    private int currAmount;

    public PacketSendAuraSupplier(){

    }

    public PacketSendAuraSupplier(BlockPos pos, AuraType type, int startAmount, int maxExtract, int currAmount, boolean remove){
        this.remove = remove;
        this.pos = pos;
        this.type = type;
        this.startAmount = startAmount;
        this.maxExtract = maxExtract;
        this.currAmount = currAmount;
    }

    @Override
    public void fromBytes(ByteBuf buf){
        this.remove = buf.readBoolean();
        this.pos = BlockPos.fromLong(buf.readLong());
        this.startAmount = buf.readInt();
        this.maxExtract = buf.readInt();
        this.currAmount = buf.readInt();

        int length = buf.readInt();
        this.type = NaturesAuraAPI.AURA_REGISTRY.get(new String(buf.readBytes(length).array(), Charsets.UTF_8));
    }

    @Override
    public void toBytes(ByteBuf buf){
        buf.writeBoolean(this.remove);
        buf.writeLong(this.pos.toLong());
        buf.writeInt(this.startAmount);
        buf.writeInt(this.maxExtract);
        buf.writeInt(this.currAmount);

        byte[] bytes = this.type.getName().getBytes(Charsets.UTF_8);
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }

    public static class Handler implements IMessageHandler<PacketSendAuraSupplier, IMessage>{

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(final PacketSendAuraSupplier message, MessageContext ctx){
            NaturesAura.proxy.addScheduledTask(new Runnable(){
                @Override
                public void run(){
                    World world = Minecraft.getMinecraft().world;
                    IAuraHandler aura = NaturesAuraAPI.getAuraHandler();

                    if(message.remove){
                        aura.removeSupplier(world, message.pos, false);
                    }
                    else{
                        IAuraInteractor supply = aura.getSupplier(world, message.pos);

                        if(supply == null){
                            supply = new AuraSupply(message.type, message.startAmount, message.maxExtract);
                            aura.addSupplier(world, message.pos, supply, false);
                        }

                        supply.setStoredAura(message.currAmount);
                    }
                }
            });
            return null;
        }
    }
}
