package de.ellpeck.naturesaura.mod.block;

import de.ellpeck.naturesaura.mod.tile.TileEntityEyeDivine;
import net.minecraft.block.material.Material;

public class BlockEyeDivine extends BlockContainerBase{

    public BlockEyeDivine(){
        super(Material.GLASS, "eye_divine", TileEntityEyeDivine.class, "eye_divine");
    }
}
