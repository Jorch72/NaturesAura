package de.ellpeck.naturesaura.mod.item;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.scroll.IScrollPage;
import de.ellpeck.naturesaura.mod.util.CommonUtil;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemScroll extends ItemBase{

    public ItemScroll(){
        super("scroll");
        this.addPropertyOverride(new ResourceLocation(ModUtil.MOD_ID, "open"), new IItemPropertyGetter(){
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, World world, EntityLivingBase entity){
                return isOpened(stack) ? 1F : 0F;
            }
        });
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

    public static void setOpened(ItemStack stack, boolean opened){
        if(!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound compound = stack.getTagCompound();
        compound.setBoolean("Opened", opened);
    }

    public static boolean isOpened(ItemStack stack){
        return stack.hasTagCompound() && stack.getTagCompound().getBoolean("Opened");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
        ItemStack stack = player.getHeldItem(hand);

        if(!isOpened(stack)){
            if(!world.isRemote){
                setOpened(stack, true);
            }

            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
        }
        else{
            return super.onItemRightClick(world, player, hand);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced){
        if(isOpened(stack)){
            IScrollPage page = getPage(stack);
            if(page == null){
                tooltip.add(CommonUtil.translate(ModUtil.MOD_ID+".noPage"));
            }
            else{
                tooltip.addAll(page.getTooltip());
            }
        }
        else{
            tooltip.add(CommonUtil.translate(ModUtil.MOD_ID+".rightClickOpen"));
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems){
        for(IScrollPage page : NaturesAuraAPI.SCROLL_PAGE_REGISTRY.values()){
            ItemStack stack = new ItemStack(item);
            setPage(stack, page);
            subItems.add(stack);

            ItemStack copy = stack.copy();
            setOpened(copy, true);
            subItems.add(copy);
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack){
        String base = super.getItemStackDisplayName(stack);

        if(isOpened(stack)){
            IScrollPage page = getPage(stack);
            if(page == null){
                return base+": "+CommonUtil.translate(ModUtil.MOD_ID+".empty");
            }
            else{
                return base+": "+CommonUtil.translate(page.getUnlocalizedDisplayName());
            }
        }
        else{
            return base+" ("+CommonUtil.translate(ModUtil.MOD_ID+".closed")+")";
        }
    }
}
