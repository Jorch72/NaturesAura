package de.ellpeck.naturesaura.mod.block.tree;

import de.ellpeck.naturesaura.mod.reg.ICustomItemBlockProvider;
import de.ellpeck.naturesaura.mod.reg.IModItem;
import de.ellpeck.naturesaura.mod.reg.IModelProvider;
import de.ellpeck.naturesaura.mod.reg.ModRegistry;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BlockLeavesBase extends BlockLeaves implements IModItem, IModelProvider{

    private final String baseName;

    public BlockLeavesBase(String baseName){
        this.baseName = baseName;
        this.leavesFancy = true;

        ModRegistry.addItemOrBlock(this);
    }

    @Override
    public String getBaseName(){
        return this.baseName;
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
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        boolean check = (meta & 1) != 0;
        boolean decay = (meta & 2) != 0;

        return this.getDefaultState().withProperty(CHECK_DECAY, check).withProperty(DECAYABLE, decay);
    }

    @Override
    public int getMetaFromState(IBlockState state){
        boolean check = state.getValue(CHECK_DECAY);
        boolean decay = state.getValue(DECAYABLE);

        return (check ? 1 : 0) | (decay ? 1 : 0) << 1;
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta){
        return null;
    }

    @Override
    public Map<ItemStack, ModelVariant> getModelLocations(){
        return Collections.singletonMap(new ItemStack(this), new ModelVariant(new ResourceLocation(ModUtil.MOD_ID, this.getBaseName()), "inventory"));
    }

    @Override
    public void beginLeavesDecay(IBlockState state, World world, BlockPos pos){
        if(!state.getValue(CHECK_DECAY) && state.getValue(DECAYABLE)){
            world.setBlockState(pos, state.withProperty(CHECK_DECAY, true), 4);
        }
    }
}
