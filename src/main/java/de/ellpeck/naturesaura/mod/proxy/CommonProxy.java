package de.ellpeck.naturesaura.mod.proxy;

import de.ellpeck.naturesaura.mod.block.BlockRegistry;
import de.ellpeck.naturesaura.mod.item.ItemRegistry;
import de.ellpeck.naturesaura.mod.packet.PacketHandler;
import de.ellpeck.naturesaura.mod.reg.ModRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy{

    public void preInit(FMLPreInitializationEvent event){
        BlockRegistry.preInit();
        ItemRegistry.preInit();
        ModRegistry.preInit(event);

        PacketHandler.preInit();
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
