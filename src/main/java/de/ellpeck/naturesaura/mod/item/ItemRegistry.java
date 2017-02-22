package de.ellpeck.naturesaura.mod.item;

import de.ellpeck.naturesaura.mod.item.subtype.ItemMaterial;
import net.minecraft.item.Item;

public final class ItemRegistry{

    public static Item itemMaterial;
    public static Item itemScroll;

    public static void preInit(){
        itemMaterial = new ItemMaterial();
        itemScroll = new ItemScroll();
    }

}
