package de.ellpeck.naturesaura.mod.scroll;

import de.ellpeck.naturesaura.api.scroll.IScrollPage;
import de.ellpeck.naturesaura.mod.util.CommonUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ScrollPage implements IScrollPage{

    protected final String name;

    public ScrollPage(String name){
        this.name = name;
    }

    @Override
    public String getRegisteredName(){
        return this.name;
    }

    @Override
    public String getUnlocalizedDisplayName(){
        return "scroll."+this.name+".name";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public List<String> getTooltip(){
        String tooltip = CommonUtil.translate("scroll."+this.name+".info");
        return Minecraft.getMinecraft().fontRendererObj.listFormattedStringToWidth(tooltip, 200);
    }
}
