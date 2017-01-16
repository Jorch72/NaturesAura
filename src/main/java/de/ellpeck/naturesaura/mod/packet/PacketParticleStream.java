package de.ellpeck.naturesaura.mod.packet;

import de.ellpeck.naturesaura.mod.NaturesAura;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketParticleStream implements IMessage{

    private double startX;
    private double startY;
    private double startZ;

    private double endX;
    private double endY;
    private double endZ;

    private double speed;
    private int color;
    private float scale;

    public PacketParticleStream(){

    }

    public PacketParticleStream(double startX, double startY, double startZ, double endX, double endY, double endZ, double speed, int color, float scale){
        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;
        this.endX = endX;
        this.endY = endY;
        this.endZ = endZ;
        this.speed = speed;
        this.color = color;
        this.scale = scale;
    }

    @Override
    public void fromBytes(ByteBuf buf){
        this.startX = buf.readDouble();
        this.startY = buf.readDouble();
        this.startZ = buf.readDouble();
        this.endX = buf.readDouble();
        this.endY = buf.readDouble();
        this.endZ = buf.readDouble();
        this.speed = buf.readDouble();
        this.color = buf.readInt();
        this.scale = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf){
        buf.writeDouble(this.startX);
        buf.writeDouble(this.startY);
        buf.writeDouble(this.startZ);
        buf.writeDouble(this.endX);
        buf.writeDouble(this.endY);
        buf.writeDouble(this.endZ);
        buf.writeDouble(this.speed);
        buf.writeInt(this.color);
        buf.writeFloat(this.scale);
    }

    public static class Handler implements IMessageHandler<PacketParticleStream, IMessage>{

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(final PacketParticleStream message, MessageContext ctx){
            final Minecraft mc = Minecraft.getMinecraft();
            mc.addScheduledTask(new Runnable(){
                @Override
                public void run(){
                    double distX = message.endX-message.startX;
                    double distY = message.endY-message.startY;
                    double distZ = message.endZ-message.startZ;
                    double motionX = distX*message.speed;
                    double motionY = distY*message.speed;
                    double motionZ = distZ*message.speed;

                    double totalDist = Math.sqrt(distX*distX+distY*distY+distZ*distZ);
                    int maxAge = (int)(message.speed*totalDist*60);

                    NaturesAura.proxy.spawnMagicParticle(mc.world, message.startX, message.startY, message.startZ, motionX, motionY, motionZ, message.color, message.scale, maxAge, 0F, false);
                }
            });

            return null;
        }
    }
}
