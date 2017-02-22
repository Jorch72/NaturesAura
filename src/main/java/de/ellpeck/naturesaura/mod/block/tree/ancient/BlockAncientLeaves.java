package de.ellpeck.naturesaura.mod.block.tree.ancient;

import de.ellpeck.naturesaura.mod.NaturesAura;
import de.ellpeck.naturesaura.mod.block.BlockRegistry;
import de.ellpeck.naturesaura.mod.block.tree.BlockLeavesBase;
import de.ellpeck.naturesaura.mod.tile.TileEntityAncientLeaves;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockAncientLeaves extends BlockLeavesBase implements ITileEntityProvider{

    public BlockAncientLeaves(){
        super("ancient_leaves");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return (meta & 2) != 0 ? new TileEntityAncientLeaves() : null;
    }

    @Override
    public boolean hasTileEntity(IBlockState state){
        return state.getValue(DECAYABLE);
    }

    @Override
    public void onInit(FMLInitializationEvent event){
        super.onInit(event);

        GameRegistry.registerTileEntity(TileEntityAncientLeaves.class, ModUtil.MOD_ID+":ancient_leaves");
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(BlockRegistry.blockAncientSapling);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand){
        super.randomDisplayTick(state, world, pos, rand);

        if(rand.nextFloat() >= 0.95F){
            IBlockState stateBelow = world.getBlockState(pos.down());
            if(stateBelow.getBlock().isAir(stateBelow, world, pos)){

                double x = pos.getX()+rand.nextDouble();
                double z = pos.getZ()+rand.nextDouble();
                float gravity = rand.nextFloat()*0.015F;

                NaturesAura.proxy.spawnMagicParticle(world, x, pos.getY(), z, 0, 0, 0, 0xB780FF, rand.nextFloat()*0.75F, rand.nextInt(100)+100, gravity, true);
            }
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
        super.updateTick(world, pos, state, rand);

        if(!world.isRemote){
            TileEntity tile = world.getTileEntity(pos);
            if(tile instanceof TileEntityAncientLeaves){
                TileEntityAncientLeaves leaves = (TileEntityAncientLeaves)tile;

                if(leaves.supply.getStoredAura() <= 0){
                    world.setBlockState(pos, BlockRegistry.blockDecayedLeaves.getDefaultState(), 2);
                }
            }
        }
    }
}
