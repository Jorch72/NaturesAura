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
        Map<BlockPos, IAuraInteractor> storage = this.getAllSuppliers(world);
        storage.put(pos, supplier);
    }

    @Override
    public IAuraInteractor getSupplier(World world, BlockPos pos){
        Map<BlockPos, IAuraInteractor> storage = this.getAllSuppliers(world);
        return storage.get(pos);
    }

    @Override
    public IAuraInteractor removeSupplier(World world, BlockPos pos){
        Map<BlockPos, IAuraInteractor> storage = this.getAllSuppliers(world);
        return storage.remove(pos);
    }

    @Override
    public List<BlockPos> getSupplierPositionsInArea(World world, BlockPos pos, int radius){
        Map<BlockPos, IAuraInteractor> storage = this.getAllSuppliers(world);
        int radiusSq = radius*radius;

        List<BlockPos> suppliers = new ArrayList<BlockPos>();
        for(BlockPos supplierPos : storage.keySet()){
            if(supplierPos.distanceSq(pos) <= radiusSq){
                suppliers.add(supplierPos);
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

    @Override
    public Map<BlockPos, IAuraInteractor> getAllSuppliers(World world){
        WorldStorage storage = this.storages.get(world);

        if(storage == null){
            storage = new WorldStorage(world);
            this.storages.put(world, storage);
        }

        return storage.suppliers;
    }

    private static class WorldStorage{

        public final Map<BlockPos, IAuraInteractor> suppliers = new HashMap<BlockPos, IAuraInteractor>();
        public final World world;

        public WorldStorage(World world){
            this.world = world;
        }

    }
}
