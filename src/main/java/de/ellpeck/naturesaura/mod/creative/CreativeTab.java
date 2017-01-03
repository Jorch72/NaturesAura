package de.ellpeck.naturesaura.mod.creative;

import de.ellpeck.naturesaura.mod.block.BlockRegistry;
import de.ellpeck.naturesaura.mod.item.ItemRegistry;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs{

    public static final CreativeTab INSTANCE = new CreativeTab();

    public CreativeTab(){
        super(ModUtil.MOD_ID);
    }

    @Override
    public ItemStack getTabIconItem(){
        return new ItemStack(BlockRegistry.blockAncientLeaves);
    }
}
