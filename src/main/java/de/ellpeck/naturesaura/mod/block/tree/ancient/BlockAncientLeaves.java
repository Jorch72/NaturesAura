package de.ellpeck.naturesaura.mod.block.tree.ancient;

import de.ellpeck.naturesaura.mod.block.BlockRegistry;
import de.ellpeck.naturesaura.mod.block.tree.BlockLeavesBase;
import de.ellpeck.naturesaura.mod.tile.TileEntityAncientLeaves;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class BlockAncientLeaves extends BlockLeavesBase implements ITileEntityProvider{

    public BlockAncientLeaves(){
        super("ancient_leaves");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return (meta & 4) != 0 ? new TileEntityAncientLeaves() : null;
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
}
