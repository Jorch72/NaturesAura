package de.ellpeck.naturesaura;

import de.ellpeck.naturesaura.proxy.CommonProxy;
import de.ellpeck.naturesaura.reg.ModRegistry;
import de.ellpeck.naturesaura.util.ModUtil;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModUtil.MOD_ID, name = ModUtil.NAME, version = ModUtil.VERSION)
public class NaturesAura{

    @Instance
    public static NaturesAura instance;

    @SidedProxy(clientSide = ModUtil.CLIENT_PROXY, serverSide = ModUtil.SERVER_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ModRegistry.preInit(event);

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        ModRegistry.init(event);

        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        ModRegistry.postInit(event);

        proxy.postInit(event);
    }

}
