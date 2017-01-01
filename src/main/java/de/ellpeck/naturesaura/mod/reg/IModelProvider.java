package de.ellpeck.naturesaura.mod.reg;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public interface IModelProvider{

    Map<ItemStack, IModelVariant> getModelLocations();

    class IModelVariant{

        public final ResourceLocation location;
        public final String variant;

        public IModelVariant(ResourceLocation location, String variant){
            this.location = location;
            this.variant = variant;
        }
    }
}
