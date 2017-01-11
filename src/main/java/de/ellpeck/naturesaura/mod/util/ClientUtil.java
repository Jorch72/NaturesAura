package de.ellpeck.naturesaura.mod.util;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class ClientUtil{

    //Credit for this goes to way2muchnoise because I don't know anything about colors
    public static int blendColors(int c1, int c2, float ratio){
        int a = (int)((c1 >> 24 & 0xFF)*ratio+(c2 >> 24 & 0xFF)*(1-ratio));
        int r = (int)((c1 >> 16 & 0xFF)*ratio+(c2 >> 16 & 0xFF)*(1-ratio));
        int g = (int)((c1 >> 8 & 0xFF)*ratio+(c2 >> 8 & 0xFF)*(1-ratio));
        int b = (int)((c1 & 0xFF)*ratio+(c2 & 0xFF)*(1-ratio));

        return ((a & 255) << 24) | ((r & 255) << 16) | ((g & 255) << 8) | (b & 255);
    }

}
