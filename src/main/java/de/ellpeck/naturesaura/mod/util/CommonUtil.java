package de.ellpeck.naturesaura.mod.util;

import net.minecraft.util.text.translation.I18n;

public final class CommonUtil{

    public static String translate(String key){
        return I18n.translateToLocal(key);
    }

}