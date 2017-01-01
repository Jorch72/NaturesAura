package de.ellpeck.naturesaura.api.internal;

import de.ellpeck.naturesaura.api.aura.capability.PassiveAuraSupplier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IAuraHandler{

    void addSupplier(World world, PassiveAuraSupplier supplier);

    PassiveAuraSupplier getSupplier(World world, BlockPos pos);

    PassiveAuraSupplier removeSupplier(World world, BlockPos pos);
}
