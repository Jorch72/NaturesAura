package de.ellpeck.naturesaura.mod.event;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.mod.particle.ParticleHandler;
import de.ellpeck.naturesaura.mod.particle.ParticleMagic;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

@SideOnly(Side.CLIENT)
public class ClientEvents{

    @SubscribeEvent
    public void onDebugRender(RenderGameOverlayEvent.Text event){
        Minecraft mc = Minecraft.getMinecraft();

        if(mc.gameSettings.showDebugInfo){
            List<String> left = event.getLeft();
            String prefix = TextFormatting.GREEN+"["+ModUtil.NAME+"]"+TextFormatting.RESET+" ";

            left.add("");
            left.add(prefix+"PartScrn: "+ParticleHandler.particlesOnScreenLast);
            left.add(prefix+"AuraHandlerClCount: "+NaturesAuraAPI.getAuraHandler().getAllSuppliers(mc.world).size());
        }
    }

    @SubscribeEvent
    public void onRenderLast(RenderWorldLastEvent event){
        ParticleHandler.particlesOnScreenLast = ParticleHandler.particlesOnScreen;
        ParticleHandler.particlesOnScreen = 0;
    }

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
        if(event.getType() == ElementType.ALL){
            Minecraft mc = Minecraft.getMinecraft();
            ScaledResolution res = event.getResolution();
            FontRenderer font = mc.fontRendererObj;
            EntityPlayer player = mc.player;
            IItemHandler playerInv = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        }
    }

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent event){
        TextureMap map = event.getMap();
        map.registerSprite(ParticleMagic.RES_LOC);
    }
}
