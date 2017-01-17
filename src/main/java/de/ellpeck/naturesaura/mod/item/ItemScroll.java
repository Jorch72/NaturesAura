package de.ellpeck.naturesaura.mod.item;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.scroll.IScrollPage;
import de.ellpeck.naturesaura.mod.util.CommonUtil;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemScroll extends ItemBase{

    public ItemScroll(){
        super("scroll");
    }

    public static void setPage(ItemStack stack, IScrollPage page){
        if(!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound compound = stack.getTagCompound();
        compound.setString("ScrollType", page.getRegisteredName());
    }

    public static IScrollPage getPage(ItemStack stack){
        if(!stack.hasTagCompound()){
            return null;
        }
        else{
            NBTTagCompound compound = stack.getTagCompound();
            String name = compound.getString("ScrollType");

            return NaturesAuraAPI.SCROLL_PAGE_REGISTRY.get(name);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced){
        IScrollPage page = getPage(stack);
        if(page == null){
            tooltip.add(CommonUtil.translate(ModUtil.MOD_ID+".noPage"));
        }
        else{
            tooltip.addAll(page.getTooltip());
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems){
        super.getSubItems(item, tab, subItems);

        for(IScrollPage page : NaturesAuraAPI.SCROLL_PAGE_REGISTRY.values()){
            ItemStack stack = new ItemStack(item);
            setPage(stack, page);
            subItems.add(stack);
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack){
        String base = super.getItemStackDisplayName(stack);
        IScrollPage page = getPage(stack);

        String loc;
        if(page == null){
            loc = ModUtil.MOD_ID+".empty";
        }
        else{
            loc = page.getUnlocalizedDisplayName();
        }

        return base+": "+CommonUtil.translate(loc);
    }
}
