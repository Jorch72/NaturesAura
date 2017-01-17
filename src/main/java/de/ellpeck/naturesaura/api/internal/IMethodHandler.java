package de.ellpeck.naturesaura.api.internal;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IMethodHandler{

    IAuraHandler getAuraHandler(World world);

    @SideOnly(Side.CLIENT)
    void spawnMagicParticle(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, int color, float scale, int maxAge, float gravity, boolean collision);
}
