package de.ellpeck.naturesaura.mod.impl;

import de.ellpeck.naturesaura.api.aura.capability.PassiveAuraSupplier;
import de.ellpeck.naturesaura.api.internal.IAuraHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuraHandler implements IAuraHandler{

    private final Map<World, WorldStorage> storages = new HashMap<World, WorldStorage>();

    @Override
    public void addSupplier(World world, PassiveAuraSupplier supplier){
        WorldStorage storage = this.getStorageForWorld(world);
        storage.suppliers.put(supplier.pos, supplier);
    }

    @Override
    public PassiveAuraSupplier getSupplier(World world, BlockPos pos){
        WorldStorage storage = this.getStorageForWorld(world);
        return storage.suppliers.get(pos);
    }

    @Override
    public PassiveAuraSupplier removeSupplier(World world, BlockPos pos){
        WorldStorage storage = this.getStorageForWorld(world);
        return storage.suppliers.remove(pos);
    }

    public List<PassiveAuraSupplier> getSuppliersInArea(World world, BlockPos pos, int radius){
        WorldStorage storage = this.getStorageForWorld(world);
        int radiusSq = radius*radius;

        List<PassiveAuraSupplier> suppliers = new ArrayList<PassiveAuraSupplier>();
        for(PassiveAuraSupplier supplier : storage.suppliers.values()){
            if(supplier.pos.distanceSq(pos) <= radiusSq){
                suppliers.add(supplier);
            }
        }
        return suppliers;
    }

    private WorldStorage getStorageForWorld(World world){
        WorldStorage storage = this.storages.get(world);

        if(storage == null){
            storage = new WorldStorage(world);
            this.storages.put(world, storage);
        }

        return storage;
    }

    private static class WorldStorage{

        public final Map<BlockPos, PassiveAuraSupplier> suppliers = new HashMap<BlockPos, PassiveAuraSupplier>();
        public final World world;

        public WorldStorage(World world){
            this.world = world;
        }

    }
}
