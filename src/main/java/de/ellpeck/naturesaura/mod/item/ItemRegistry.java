package de.ellpeck.naturesaura.mod.item;

import net.minecraft.item.Item;

public final class ItemRegistry{

    public static Item itemEyeDivine;

    public static void preInit(){
        itemEyeDivine = new ItemEyeDivine();
    }

}
