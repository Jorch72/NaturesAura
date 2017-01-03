package de.ellpeck.naturesaura.mod.packet;

import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class PacketHandler{

    public static SimpleNetworkWrapper network;

    public static void preInit(){
        network = new SimpleNetworkWrapper(ModUtil.MOD_ID);

        network.registerMessage(PacketSendAuraSupplier.Handler.class, PacketSendAuraSupplier.class, 0, Side.CLIENT);
    }

}
