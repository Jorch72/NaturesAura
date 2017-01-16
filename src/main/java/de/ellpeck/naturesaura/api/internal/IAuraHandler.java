package de.ellpeck.naturesaura.api.internal;

import de.ellpeck.naturesaura.api.aura.capability.IAuraInteractor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public interface IAuraHandler{

    void addSupplier(World world, BlockPos pos, IAuraInteractor supplier);

    IAuraInteractor getSupplier(World world, BlockPos pos);

    IAuraInteractor removeSupplier(World world, BlockPos pos);

    List<IAuraInteractor> getSuppliersInArea(World world, BlockPos pos, int radius);

    void clear();

    Map<BlockPos, IAuraInteractor> getAllSuppliers(World world);
}
