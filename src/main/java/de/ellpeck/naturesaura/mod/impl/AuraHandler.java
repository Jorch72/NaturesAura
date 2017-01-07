package de.ellpeck.naturesaura.mod.impl;

import de.ellpeck.naturesaura.api.aura.capability.IAuraInteractor;
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
    public void addSupplier(World world, BlockPos pos, IAuraInteractor supplier){
        WorldStorage storage = this.getStorageForWorld(world);
        storage.suppliers.put(pos, supplier);
    }

    @Override
    public IAuraInteractor getSupplier(World world, BlockPos pos){
        WorldStorage storage = this.getStorageForWorld(world);
        return storage.suppliers.get(pos);
    }

    @Override
    public IAuraInteractor removeSupplier(World world, BlockPos pos){
        WorldStorage storage = this.getStorageForWorld(world);
        return storage.suppliers.remove(pos);
    }

    @Override
    public List<IAuraInteractor> getSuppliersInArea(World world, BlockPos pos, int radius){
        WorldStorage storage = this.getStorageForWorld(world);
        int radiusSq = radius*radius;

        List<IAuraInteractor> suppliers = new ArrayList<IAuraInteractor>();
        for(BlockPos supplierPos : storage.suppliers.keySet()){
            if(supplierPos.distanceSq(pos) <= radiusSq){
                suppliers.add(storage.suppliers.get(supplierPos));
            }
        }
        return suppliers;
    }

    @Override
    public void clear(){
        if(!this.storages.isEmpty()){
            this.storages.clear();
        }
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

        public final Map<BlockPos, IAuraInteractor> suppliers = new HashMap<BlockPos, IAuraInteractor>();
        public final World world;

        public WorldStorage(World world){
            this.world = world;
        }

    }
}
