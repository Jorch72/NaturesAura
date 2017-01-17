package de.ellpeck.naturesaura.mod.impl;

import de.ellpeck.naturesaura.api.internal.IAuraHandler;
import de.ellpeck.naturesaura.api.internal.IMethodHandler;
import de.ellpeck.naturesaura.mod.NaturesAura;
import de.ellpeck.naturesaura.mod.util.WorldData;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MethodHandler implements IMethodHandler{

    @Override
    public IAuraHandler getAuraHandler(World world){
        return WorldData.get(world).auraHandler;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void spawnMagicParticle(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, int color, float scale, int maxAge, float gravity, boolean collision){
        NaturesAura.proxy.spawnMagicParticle(world, posX, posY, posZ, motionX, motionY, motionZ, color, scale, maxAge, gravity, collision);
    }
}
