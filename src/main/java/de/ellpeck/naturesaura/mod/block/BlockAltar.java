package de.ellpeck.naturesaura.mod.block;

import de.ellpeck.naturesaura.mod.tile.TileEntityAltar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockAltar extends BlockContainerBase{

    public BlockAltar(){
        super(Material.ROCK, "altar", TileEntityAltar.class, "altar");

        this.setHardness(2.5F);
        this.setResistance(20.0F);
        this.setSoundType(SoundType.STONE);
    }
}
