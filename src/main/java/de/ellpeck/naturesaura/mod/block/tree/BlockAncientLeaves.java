package de.ellpeck.naturesaura.mod.block.tree;

import de.ellpeck.naturesaura.mod.reg.IModItem;
import de.ellpeck.naturesaura.mod.reg.IModelProvider;
import de.ellpeck.naturesaura.mod.reg.ModRegistry;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BlockAncientLeaves extends BlockLeaves implements IModItem, IModelProvider{

    public BlockAncientLeaves(){
        ModRegistry.addItemOrBlock(this);
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta){
        return null;
    }

    @Override
    public String getBaseName(){
        return "ancient_leaves";
    }

    @Override
    public boolean shouldAddCreative(){
        return true;
    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event){

    }

    @Override
    public void onInit(FMLInitializationEvent event){

    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event){

    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune){
        return Collections.singletonList(new ItemStack(this, 1, 0));
    }

    @Override
    public Map<ItemStack, ModelVariant> getModelLocations(){
        return Collections.singletonMap(new ItemStack(this), new ModelVariant(new ResourceLocation(ModUtil.MOD_ID, this.getBaseName()), "inventory"));
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(CHECK_DECAY, meta == 3 || meta == 2).withProperty(DECAYABLE, meta == 3 || meta == 1);
    }

    @Override
    public int getMetaFromState(IBlockState state){
        boolean check = state.getValue(CHECK_DECAY);
        boolean decayable = state.getValue(DECAYABLE);

        return check ? (decayable ? 3 : 2) : (decayable ? 1 : 0);
    }

    @Override
    public BlockRenderLayer getBlockLayer(){
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }
}