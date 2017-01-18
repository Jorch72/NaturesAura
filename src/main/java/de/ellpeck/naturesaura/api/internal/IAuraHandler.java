package de.ellpeck.naturesaura.api.internal;

import de.ellpeck.naturesaura.api.aura.capability.IAuraStorage;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public interface IAuraHandler{

    void addSupplier(World world, BlockPos pos, IAuraStorage supplier);

    IAuraStorage getSupplier(World world, BlockPos pos);

    IAuraStorage removeSupplier(World world, BlockPos pos);

    List<BlockPos> getSupplierPositionsInArea(World world, BlockPos pos, int radius);

    void clear();

    Map<BlockPos, IAuraStorage> getAllSuppliers(World world);
}
