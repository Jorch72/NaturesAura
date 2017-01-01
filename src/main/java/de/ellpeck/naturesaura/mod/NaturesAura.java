package de.ellpeck.naturesaura.mod;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.capability.AuraCapabilities.CapabilityAura;
import de.ellpeck.naturesaura.api.aura.capability.AuraStorage;
import de.ellpeck.naturesaura.api.aura.capability.IAuraInteractor;
import de.ellpeck.naturesaura.mod.impl.MethodHandler;
import de.ellpeck.naturesaura.mod.proxy.CommonProxy;
import de.ellpeck.naturesaura.mod.reg.ModRegistry;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraftforge.common.capabilities.CapabilityManager;
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

    static{
        NaturesAuraAPI.apiHandler = new MethodHandler();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        CapabilityManager.INSTANCE.register(IAuraInteractor.class, new CapabilityAura<IAuraInteractor>(), AuraStorage.class);

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
