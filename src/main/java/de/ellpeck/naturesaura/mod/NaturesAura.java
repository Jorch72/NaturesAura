package de.ellpeck.naturesaura.mod;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.capability.AuraCapabilities.CapabilityAura;
import de.ellpeck.naturesaura.api.aura.capability.AuraStorage;
import de.ellpeck.naturesaura.api.aura.capability.IAuraStorage;
import de.ellpeck.naturesaura.mod.impl.MethodHandler;
import de.ellpeck.naturesaura.mod.proxy.CommonProxy;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import de.ellpeck.naturesaura.mod.util.WorldData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = ModUtil.MOD_ID, name = ModUtil.NAME, version = ModUtil.VERSION)
public class NaturesAura{

    @Instance
    public static NaturesAura instance;

    @SidedProxy(clientSide = ModUtil.CLIENT_PROXY, serverSide = ModUtil.SERVER_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        CapabilityManager.INSTANCE.register(IAuraStorage.class, new CapabilityAura<IAuraStorage>(), AuraStorage.class);
        NaturesAuraAPI.apiHandler = new MethodHandler();

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }

    @EventHandler
    public void serverStarted(FMLServerStartedEvent event){
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if(server != null){
            World world = server.getEntityWorld();
            if(world != null){
                WorldData.get(world, true).markDirty();
            }
        }
    }

    @EventHandler
    public void serverStopped(FMLServerStoppedEvent event){
        WorldData.clear();
    }

}
