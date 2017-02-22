package de.ellpeck.naturesaura.api.scroll;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public interface IScrollPage{

    String getRegisteredName();

    String getUnlocalizedDisplayName();

    @SideOnly(Side.CLIENT)
    List<String> getTooltip();
}
