package de.ellpeck.naturesaura.mod.block.tree;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockDecayedLeaves extends BlockLeavesBase{

    public BlockDecayedLeaves(){
        super("decayed_leaves");
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Items.AIR;
    }
}
