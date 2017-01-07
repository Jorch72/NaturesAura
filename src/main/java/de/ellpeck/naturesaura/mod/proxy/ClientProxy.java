package de.ellpeck.naturesaura.mod.proxy;

import de.ellpeck.naturesaura.mod.event.ClientEvents;
import de.ellpeck.naturesaura.mod.particle.ParticleMagic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{

    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);

        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }

    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);
    }

    @Override
    public void registerRenderer(ItemStack stack, ResourceLocation location, String variant){
        ModelLoader.setCustomModelResourceLocation(stack.getItem(), stack.getItemDamage(), new ModelResourceLocation(location, variant));
    }

    @Override
    public void spawnMagicParticle(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, float r, float g, float b, float scale, int maxAge){
        ParticleMagic particle = new ParticleMagic(world, posX, posY, posZ, motionX, motionY, motionZ, r, g, b, scale, maxAge);
        this.spawnParticleWithRange(particle, posX, posY, posZ, 32);
    }

    private void spawnParticleWithRange(Particle particle, double x, double y, double z, int range){
        Minecraft mc = Minecraft.getMinecraft();
        if(mc.player.getDistanceSq(x, y, z) <= range*range){
            mc.effectRenderer.addEffect(particle);
        }
    }
}
