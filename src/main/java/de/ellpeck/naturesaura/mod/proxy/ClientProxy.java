package de.ellpeck.naturesaura.mod.proxy;

import de.ellpeck.naturesaura.mod.event.ClientEvents;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
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
    public void addScheduledTask(Runnable task){
        Minecraft.getMinecraft().addScheduledTask(task);
    }
}
