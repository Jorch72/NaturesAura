package de.ellpeck.naturesaura.mod.tile.render;

import de.ellpeck.naturesaura.mod.tile.TileEntityEyeDivine;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityEyeDivineRenderer extends TileEntitySpecialRenderer<TileEntityEyeDivine>{

    @Override
    public void renderTileEntityAt(TileEntityEyeDivine tile, double x, double y, double z, float partialTicks, int destroyStage){
        //TODO Add fancy floating in-world GUI thing that displays the amount of aura the TE has calculated
    }
}
