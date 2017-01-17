package de.ellpeck.naturesaura.mod.item;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.capability.IAuraStorage;
import de.ellpeck.naturesaura.api.aura.supplier.IAuraSupplier;
import de.ellpeck.naturesaura.mod.packet.PacketHandler;
import de.ellpeck.naturesaura.mod.packet.PacketSyncAreaAura;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemEyeDivine extends ItemBase{

    public static int areaAuraAmount;
    public static int areaAuraLimit;

    public ItemEyeDivine(){
        super("eye_divine");

        this.setMaxStackSize(1);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected){
        if(!world.isRemote && world.getTotalWorldTime()%10 == 0 && isSelected){
            if(entity instanceof EntityPlayer){
                List<IAuraSupplier> suppliers = NaturesAuraAPI.getAuraHandler(world).getSuppliersInArea(world.provider.getDimension(), entity.getPosition(), 20);
                int amount = 0;
                int limit = 0;

                if(!suppliers.isEmpty()){
                    for(IAuraSupplier supplier : suppliers){
                        IAuraStorage storage = supplier.getSupply();

                        amount += storage.getStoredAura();
                        limit += storage.getAuraLimit();
                    }
                }

                //TODO Find a way of syncing this only when the amount has actually changed
                PacketHandler.sendTo((EntityPlayer)entity, new PacketSyncAreaAura(amount, limit));
            }
        }
    }
}
