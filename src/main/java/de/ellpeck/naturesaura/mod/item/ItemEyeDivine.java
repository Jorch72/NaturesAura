package de.ellpeck.naturesaura.mod.item;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.capability.IAuraInteractor;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class ItemEyeDivine extends ItemBase{

    public ItemEyeDivine(){
        super("eye_divine");

        this.setMaxStackSize(1);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected){
        if(!world.isRemote){
            if(world.getTotalWorldTime()%60 == 0){
                List<IAuraInteractor> supplies = NaturesAuraAPI.getAuraHandler().getSuppliersInArea(world, entity.getPosition(), 10);

                int totalStored = 0;
                int totalLimit = 0;
                for(IAuraInteractor supply : supplies){
                    totalStored += supply.getStoredAura();
                    totalLimit += supply.getAuraLimit();
                }

                if(!stack.hasTagCompound()){
                    stack.setTagCompound(new NBTTagCompound());
                }
                NBTTagCompound compound = stack.getTagCompound();
                compound.setInteger("AreaStored", totalStored);
                compound.setInteger("AreaLimit", totalLimit);
            }
        }
    }
}
