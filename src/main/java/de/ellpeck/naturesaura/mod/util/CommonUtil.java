package de.ellpeck.naturesaura.mod.util;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.supplier.IAuraSupplier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public final class CommonUtil{

    public static String translate(String key){
        return I18n.translateToLocal(key);
    }

    public static IAuraSupplier makeAuraSupplier(String name, BlockPos pos){
        try{
            return NaturesAuraAPI.SUPPLIER_REGISTRY.get(name).getConstructor(BlockPos.class).newInstance(pos);
        }
        catch(Exception e){
            ModUtil.LOGGER.error("Initializing an Aura Supplier that was stored to disk failed! Has it been registered properly?", e);
            return null;
        }
    }
}
