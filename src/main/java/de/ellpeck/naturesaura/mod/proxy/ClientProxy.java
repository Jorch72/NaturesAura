package de.ellpeck.naturesaura.mod.proxy;

import de.ellpeck.naturesaura.mod.event.ClientEvents;
import de.ellpeck.naturesaura.mod.particle.ParticleHandler;
import de.ellpeck.naturesaura.mod.particle.ParticleMagic;
import de.ellpeck.naturesaura.mod.reg.IColorProvidingBlock;
import de.ellpeck.naturesaura.mod.reg.IColorProvidingItem;
import de.ellpeck.naturesaura.mod.tile.TileEntityEyeDivine;
import de.ellpeck.naturesaura.mod.tile.render.TileEntityEyeDivineRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEyeDivine.class, new TileEntityEyeDivineRenderer());
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
    public void addColorProvidingItem(IColorProvidingItem item){
        ItemColors colors = Minecraft.getMinecraft().getItemColors();
        IItemColor color = item.getItemColor();

        if(item instanceof Item){
            colors.registerItemColorHandler(color, (Item)item);
        }
        else if(item instanceof Block){
            colors.registerItemColorHandler(color, (Block)item);
        }
    }

    @Override
    public void addColorProvidingBlock(IColorProvidingBlock block){
        if(block instanceof Block){
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(block.getBlockColor(), (Block)block);
        }
    }

    @Override
    public void spawnMagicParticle(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, int color, float scale, int maxAge, float gravity, boolean collision){
        ParticleMagic particle = new ParticleMagic(world, posX, posY, posZ, motionX, motionY, motionZ, color, scale, maxAge, gravity, collision);
        ParticleHandler.spawnParticle(particle, posX, posY, posZ, 32);
    }
}
