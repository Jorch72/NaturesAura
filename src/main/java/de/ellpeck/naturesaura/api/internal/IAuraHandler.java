package de.ellpeck.naturesaura.api.internal;

import de.ellpeck.naturesaura.api.aura.supplier.IAuraSupplier;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Map;

public interface IAuraHandler{

    void addSupplier(int dimension, IAuraSupplier supplier);

    IAuraSupplier getSupplier(int dimension, BlockPos pos);

    IAuraSupplier removeSupplier(int dimension, BlockPos pos);

    List<IAuraSupplier> getSuppliersInArea(int dimension, BlockPos pos, int radius);

    Map<BlockPos, IAuraSupplier> getAllSuppliers(int dimension);
}
