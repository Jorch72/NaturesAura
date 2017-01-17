package de.ellpeck.naturesaura.mod.event;

import de.ellpeck.naturesaura.mod.item.ItemEyeDivine;
import de.ellpeck.naturesaura.mod.particle.ParticleHandler;
import de.ellpeck.naturesaura.mod.particle.ParticleMagic;
import de.ellpeck.naturesaura.mod.util.ModUtil;
import de.ellpeck.naturesaura.mod.util.WorldData;
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

            if(!mc.isIntegratedServerRunning() && mc.world == null){
                WorldData.clear();
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

            ItemStack stack = player.getHeldItemMainhand();
            if(!stack.isEmpty() && stack.getItem() instanceof ItemEyeDivine){
                String strg = "Aura in area: "+ItemEyeDivine.areaAuraAmount+"/"+ItemEyeDivine.areaAuraLimit;
                font.drawString(strg, res.getScaledWidth()/2-font.getStringWidth(strg)/2, res.getScaledHeight()-80, 0xFFFFFF, true);
            }
        }
    }

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent event){
        TextureMap map = event.getMap();
        map.registerSprite(ParticleMagic.RES_LOC);
    }
}
