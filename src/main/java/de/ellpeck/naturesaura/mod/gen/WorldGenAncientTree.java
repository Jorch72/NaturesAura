package de.ellpeck.naturesaura.mod.gen;

import de.ellpeck.naturesaura.mod.block.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenAncientTree extends WorldGenAbstractTree{

    public WorldGenAncientTree(boolean notify){
        super(notify);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos){
        int height = rand.nextInt(3)+8;

        for(int x = -1; x <= 1; x++){
            for(int z = -1; z <= 1; z++){
                for(int y = 0; y <= height; y++){
                    if(x != 0 || y != 0 || z != 0){
                        BlockPos offset = pos.add(x, y, z);
                        if(!this.isReplaceable(world, offset)){
                            return false;
                        }
                    }
                }
            }
        }

        for(int y = 0; y <= height-2; y++){
            this.setBlockAndNotifyAdequately(world, pos.up(y), BlockRegistry.blockAncientLog.getDefaultState().withProperty(BlockLog.LOG_AXIS, EnumAxis.Y));
        }

        for(int x = -2; x <= 2; x++){
            for(int z = -2; z <= 2; z++){
                if(x != 0 || z != 0){
                    for(int y = -1; y <= 3-Math.abs(x) && y <= 3-Math.abs(z); y++){
                        if(rand.nextInt(3) >= Math.abs(x) && rand.nextInt(3) >= Math.abs(z)){
                            BlockPos offset = pos.add(x, y, z);

                            if(world.getBlockState(offset.down()).isFullBlock() && this.isReplaceable(world, offset)){
                                this.setBlockAndNotifyAdequately(world, offset, BlockRegistry.blockAncientBark.getDefaultState());
                            }
                        }
                    }
                }
            }
        }

        int numBranches = rand.nextInt(4)+5;
        for(int i = 0; i < numBranches; i++){
            BlockPos branchEnd = pos.add(rand.nextInt(7)-3, height-3+rand.nextInt(6), rand.nextInt(7)-3);
            this.generateBranch(world, pos.up(height-3), branchEnd, BlockRegistry.blockAncientLog);

            int nodeSize = 3;
            for(int x = -nodeSize; x <= nodeSize; x++){
                for(int z = -nodeSize; z <= nodeSize; z++){
                    for(int y = -nodeSize; y <= nodeSize; y++){
                        BlockPos offset = branchEnd.add(x, y, z);

                        if(offset.distanceSq(branchEnd) < nodeSize*nodeSize){
                            if(this.isReplaceable(world, offset) && world.getBlockState(offset).getBlock() != BlockRegistry.blockAncientLog){
                                this.setBlockAndNotifyAdequately(world, offset, BlockRegistry.blockAncientLeaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    //Copied from WorldGenBigTree
    private void generateBranch(World world, BlockPos from, BlockPos to, Block block){
        BlockPos distancePos = to.add(-from.getX(), -from.getY(), -from.getZ());
        int i = this.getGreatestDistance(distancePos);
        float f = (float)distancePos.getX()/(float)i;
        float f1 = (float)distancePos.getY()/(float)i;
        float f2 = (float)distancePos.getZ()/(float)i;

        for(int j = 0; j <= i; ++j){
            BlockPos pos = from.add((double)(0.5F+(float)j*f), (double)(0.5F+(float)j*f1), (double)(0.5F+(float)j*f2));
            EnumAxis axis = this.getLogAxis(from, pos);
            this.setBlockAndNotifyAdequately(world, pos, block.getDefaultState().withProperty(BlockLog.LOG_AXIS, axis));
        }
    }

    //Copied from WorldGenBigTree
    private int getGreatestDistance(BlockPos posIn){
        int i = Math.abs(posIn.getX());
        int j = Math.abs(posIn.getY());
        int k = Math.abs(posIn.getZ());
        return k > i && k > j ? k : (j > i ? j : i);
    }

    //Copied from WorldGenBigTree
    private EnumAxis getLogAxis(BlockPos from, BlockPos to){
        int i = Math.abs(to.getX()-from.getX());
        int j = Math.abs(to.getZ()-from.getZ());
        int k = Math.max(i, j);

        if(k > 0){
            if(i == k){
                return EnumAxis.X;
            }
            else if(j == k){
                return EnumAxis.Z;
            }
        }
        return EnumAxis.Y;
    }
}
