package de.ellpeck.naturesaura.mod.block.tree;

import de.ellpeck.naturesaura.mod.reg.IModelProvider;
import de.ellpeck.naturesaura.mod.tile.TileEntityAncientLeaves;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Collections;
import java.util.Map;

public class BlockAncientLeaves extends BlockLeavesBase implements IModelProvider, ITileEntityProvider{

    public BlockAncientLeaves(){
        super("ancient_leaves");
    }

    @Override
    public Map<ItemStack, ModelVariant> getModelLocations(){
        return Collections.singletonMap(new ItemStack(this), new ModelVariant(new ResourceLocation(ModUtil.MOD_ID, this.getBaseName()), "inventory"));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return new TileEntityAncientLeaves();
    }

    @Override
    public void onInit(FMLInitializationEvent event){
        super.onInit(event);

        GameRegistry.registerTileEntity(TileEntityAncientLeaves.class, ModUtil.MOD_ID+":ancient_leaves");
    }
}
