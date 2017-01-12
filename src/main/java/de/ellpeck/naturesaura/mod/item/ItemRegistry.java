package de.ellpeck.naturesaura.mod.item;

import de.ellpeck.naturesaura.mod.item.subtype.ItemMaterial;
import net.minecraft.item.Item;

public final class ItemRegistry{

    public static Item itemEyeDivine;
    public static Item itemMaterial;

    public static void preInit(){
        itemEyeDivine = new ItemEyeDivine();
        itemMaterial = new ItemMaterial();
    }

}
