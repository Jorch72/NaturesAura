package de.ellpeck.naturesaura.mod.block.tree.ancient;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.supplier.IAuraSupplier;
import de.ellpeck.naturesaura.mod.NaturesAura;
import de.ellpeck.naturesaura.mod.block.BlockRegistry;
import de.ellpeck.naturesaura.mod.block.tree.BlockLeavesBase;
import de.ellpeck.naturesaura.mod.impl.aura.SupplierAncientLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockAncientLeaves extends BlockLeavesBase{

    public BlockAncientLeaves(){
        super("ancient_leaves");
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
        super.updateTick(world, pos, state, rand);

        if(!world.isRemote){
            IAuraSupplier supplier = NaturesAuraAPI.getAuraHandler(world).getSupplier(world.provider.getDimension(), pos);
            if(supplier != null){
                int amount = supplier.getSupply().getStoredAura();
                if(amount <= 0){
                    world.setBlockState(pos, BlockRegistry.blockDecayedLeaves.getDefaultState(), 2);
                }
            }
        }
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state){
        super.onBlockAdded(world, pos, state);

        if(!world.isRemote){
            NaturesAuraAPI.getAuraHandler(world).addSupplier(world.provider.getDimension(), new SupplierAncientLeaves(pos));
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state){
        super.breakBlock(world, pos, state);

        if(!world.isRemote){
            NaturesAuraAPI.getAuraHandler(world).removeSupplier(world.provider.getDimension(), pos);
        }
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
}
