package de.ellpeck.naturesaura.mod.block.tree.ancient;

import de.ellpeck.naturesaura.mod.block.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockAncientBark extends BlockBase{

    public BlockAncientBark(){
        super(Material.WOOD, "ancient_bark");

        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setHardness(2.0F);
        this.setSoundType(SoundType.WOOD);
    }

}
