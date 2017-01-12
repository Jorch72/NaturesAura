package de.ellpeck.naturesaura.mod.item.subtype;

import de.ellpeck.naturesaura.mod.item.ItemBase;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ItemSubtype extends ItemBase{

    private final String[] subtypes;

    public ItemSubtype(String baseName, String[] subtypes){
        super(baseName);
        this.subtypes = subtypes;

        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        String base = this.getUnlocalizedName();
        int meta = stack.getItemDamage();

        if(meta >= 0 && meta < this.subtypes.length){
            return base+"."+this.subtypes[meta];
        }
        else{
            return base;
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems){
        for(int i = 0; i < this.subtypes.length; i++){
            subItems.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public Map<ItemStack, ModelVariant> getModelLocations(){
        Map<ItemStack, ModelVariant> locations = new HashMap<ItemStack, ModelVariant>();

        for(int i = 0; i < this.subtypes.length; i++){
            ModelVariant variant = new ModelVariant(new ResourceLocation(ModUtil.MOD_ID, this.subtypes[i]), "inventory");
            locations.put(new ItemStack(this, 1, i), variant);
        }

        return locations;
    }
}
