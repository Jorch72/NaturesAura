package de.ellpeck.naturesaura.mod.reg;

import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IColorProvidingBlock{

    @SideOnly(Side.CLIENT)
    IBlockColor getBlockColor();

}
