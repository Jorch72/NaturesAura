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
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BlockLeavesBase extends BlockLeaves implements IModItem, ICustomItemBlockProvider, IModelProvider{

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
        return this.getDefaultState().withProperty(CHECK_DECAY, meta == 1 || meta == 3).withProperty(DECAYABLE, meta == 0 || meta == 1);
    }

    @Override
    public int getMetaFromState(IBlockState state){
        boolean check = state.getValue(CHECK_DECAY);
        boolean decayable = state.getValue(DECAYABLE);

        return check ? (decayable ? 1 : 3) : (decayable ? 0 : 2);
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta){
        return null;
    }

    @Override
    public ItemBlock getItemBlock(){
        return new ItemBlock(this){
            @Override
            public int getMetadata(int damage){
                return 2;
            }
        };
    }

    @Override
    public Map<ItemStack, ModelVariant> getModelLocations(){
        return Collections.singletonMap(new ItemStack(this), new ModelVariant(new ResourceLocation(ModUtil.MOD_ID, this.getBaseName()), "inventory"));
    }
}
