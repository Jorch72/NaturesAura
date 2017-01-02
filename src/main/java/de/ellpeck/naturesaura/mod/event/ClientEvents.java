package de.ellpeck.naturesaura.mod.event;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.mod.item.ItemEyeDivine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientEvents{

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event){
        if(event.phase == Phase.END){
            Minecraft mc = Minecraft.getMinecraft();

            if(mc.world == null){
                NaturesAuraAPI.getAuraHandler().clear();
            }
        }
    }

    @SubscribeEvent
    public void onOverlayRender(RenderGameOverlayEvent.Post event){
        if(event.getType() == RenderGameOverlayEvent.ElementType.ALL){
            Minecraft mc = Minecraft.getMinecraft();
            ScaledResolution res = event.getResolution();
            FontRenderer font = mc.fontRendererObj;
            EntityPlayer player = mc.player;

            for(int i = 0; i < player.inventory.getSizeInventory(); i++){
                ItemStack stack = player.inventory.getStackInSlot(i);
                if(!stack.isEmpty()){
                    if(stack.getItem() instanceof ItemEyeDivine){
                        if(stack.hasTagCompound()){
                            NBTTagCompound compound = stack.getTagCompound();

                            int totalStored = compound.getInteger("AreaStored");
                            int totalLimit = compound.getInteger("AreaLimit");

                            String strg = "Aura in area: "+totalStored+"/"+totalLimit;
                            font.drawString(strg, res.getScaledWidth()/2-font.getStringWidth(strg)/2, res.getScaledHeight()-80, 0xFFFFFF, true);
                        }

                        break;
                    }
                }
            }
        }
    }

}
