package de.ellpeck.naturesaura.mod.block.tree.ancient;

import de.ellpeck.naturesaura.mod.block.tree.BlockSaplingBase;
import de.ellpeck.naturesaura.mod.gen.WorldGenAncientTree;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockAncientSapling extends BlockSaplingBase{

    public BlockAncientSapling(){
        super("ancient_sapling");
    }

    @Override
    protected void generate(World world, BlockPos pos, IBlockState state, Random rand){
        WorldGenAncientTree gen = new WorldGenAncientTree(true);
        gen.generate(world, rand, pos);
    }
}
