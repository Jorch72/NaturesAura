package de.ellpeck.naturesaura.mod.packet;

import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public final class PacketHandler{

    public static SimpleNetworkWrapper network;

    public static void preInit(){
        network = new SimpleNetworkWrapper(ModUtil.MOD_ID);
    }

}
