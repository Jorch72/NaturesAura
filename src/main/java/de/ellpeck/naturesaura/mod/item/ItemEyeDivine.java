package de.ellpeck.naturesaura.mod.item;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.capability.IAuraInteractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.List;

public class ItemEyeDivine extends ItemBase{

    public ItemEyeDivine(){
        super("eye_divine");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
        if(!world.isRemote){
            List<IAuraInteractor> suppliers = NaturesAuraAPI.getAuraHandler().getSuppliersInArea(world, player.getPosition(), 10);

            int amount = 0;
            for(IAuraInteractor supplier : suppliers){
                amount += supplier.getTotalStoredAmount();
            }

            player.sendMessage(new TextComponentString("There are "+suppliers.size()+" suppliers giving "+amount+" Aura in total. "+world.isRemote));
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }
}
