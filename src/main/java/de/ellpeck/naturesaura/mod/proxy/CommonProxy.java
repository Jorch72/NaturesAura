package de.ellpeck.naturesaura.mod.proxy;

import de.ellpeck.naturesaura.mod.block.BlockRegistry;
import de.ellpeck.naturesaura.mod.reg.ModRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy{

    public void preInit(FMLPreInitializationEvent event){
        BlockRegistry.preInit();

        ModRegistry.preInit(event);
    }

    public void init(FMLInitializationEvent event){
        ModRegistry.init(event);
    }

    public void postInit(FMLPostInitializationEvent event){
        ModRegistry.postInit(event);
    }

    public void registerRenderer(ItemStack stack, ResourceLocation location, String variant){

    }
}
