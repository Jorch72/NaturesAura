package de.ellpeck.naturesaura.mod.block;

import de.ellpeck.naturesaura.mod.reg.IModItem;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockContainerBase extends BlockContainer implements IModItem{

    private final String baseName;

    private final Class<? extends TileEntity> tileClass;
    private final String tileRegName;

    public BlockContainerBase(Material material, String baseName, Class<? extends TileEntity> tileClass, String tileReg){
        super(material);

        this.baseName = baseName;
        this.tileClass = tileClass;
        this.tileRegName = tileReg;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        try{
            return this.tileClass.newInstance();
        }
        catch(Exception e){
            ModUtil.LOGGER.error("Initializing a TileEntity failed!", e);
            return null;
        }
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
        GameRegistry.registerTileEntity(this.tileClass, ModUtil.MOD_ID+":"+this.tileRegName);
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent event){

    }
}
