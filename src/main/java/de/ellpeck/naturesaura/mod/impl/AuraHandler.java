package de.ellpeck.naturesaura.mod.impl;

import de.ellpeck.naturesaura.api.aura.supplier.IAuraSupplier;
import de.ellpeck.naturesaura.api.internal.IAuraHandler;
import de.ellpeck.naturesaura.mod.util.CommonUtil;
import de.ellpeck.naturesaura.mod.util.WorldData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuraHandler implements IAuraHandler{

    private final WorldData data;
    private final Map<Integer, DimStorage> storages = new ConcurrentHashMap<Integer, DimStorage>();

    public AuraHandler(WorldData data){
        this.data = data;
    }

    @Override
    public void addSupplier(int dimension, IAuraSupplier supplier){
        Map<BlockPos, IAuraSupplier> storage = this.getAllSuppliers(dimension);

        IAuraSupplier previous = storage.put(supplier.getPos(), supplier);
        if(previous != supplier){
            this.data.markDirty();
        }
    }

    @Override
    public IAuraSupplier getSupplier(int dimension, BlockPos pos){
        Map<BlockPos, IAuraSupplier> storage = this.getAllSuppliers(dimension);
        return storage.get(pos);
    }

    @Override
    public IAuraSupplier removeSupplier(int dimension, BlockPos pos){
        Map<BlockPos, IAuraSupplier> storage = this.getAllSuppliers(dimension);

        IAuraSupplier removed = storage.remove(pos);
        if(removed != null){
            this.data.markDirty();
        }

        return removed;
    }

    @Override
    public List<IAuraSupplier> getSuppliersInArea(int dimension, BlockPos pos, int radius){
        Map<BlockPos, IAuraSupplier> storage = this.getAllSuppliers(dimension);
        int radiusSq = radius*radius;

        List<IAuraSupplier> suppliers = new ArrayList<IAuraSupplier>();
        for(BlockPos supplierPos : storage.keySet()){
            if(supplierPos.distanceSq(pos) <= radiusSq){
                suppliers.add(storage.get(supplierPos));
            }
        }
        return suppliers;
    }

    @Override
    public Map<BlockPos, IAuraSupplier> getAllSuppliers(int dimension){
        DimStorage storage = this.storages.get(dimension);

        if(storage == null){
            storage = new DimStorage(dimension);
            this.storages.put(dimension, storage);
        }

        return storage.suppliers;
    }

    public void writeToNBT(NBTTagCompound compound){
        NBTTagList dimList = new NBTTagList();

        for(int dim : this.storages.keySet()){
            NBTTagCompound dimTag = new NBTTagCompound();

            dimTag.setInteger("Dim", dim);
            this.writeDimToNBT(dimTag, this.getAllSuppliers(dim));

            dimList.appendTag(dimTag);
        }

        compound.setTag("AuraData", dimList);
    }

    public void readFromNBT(NBTTagCompound compound){
        NBTTagList dimList = compound.getTagList("AuraData", 10);

        for(int i = 0; i < dimList.tagCount(); i++){
            NBTTagCompound dimTag = dimList.getCompoundTagAt(i);

            int dim = dimTag.getInteger("Dim");
            this.readDimFromNBT(dimTag, dim);
        }
    }

    //TODO Save this in "categories", a list for every type of supplier to save space and not have "ancient_leaves" 200 times
    private void writeDimToNBT(NBTTagCompound compound, Map<BlockPos, IAuraSupplier> storage){
        NBTTagList storageList = new NBTTagList();

        for(IAuraSupplier supplier : storage.values()){
            NBTTagCompound suppTag = new NBTTagCompound();
            suppTag.setString("Name", supplier.getRegisteredName());
            suppTag.setLong("Pos", supplier.getPos().toLong());
            supplier.writeToNBT(suppTag);
            storageList.appendTag(suppTag);
        }

        compound.setTag("Data", storageList);
    }

    private void readDimFromNBT(NBTTagCompound compound, int dim){
        NBTTagList storageList = compound.getTagList("Data", 10);

        for(int i = 0; i < storageList.tagCount(); i++){
            NBTTagCompound suppTag = storageList.getCompoundTagAt(i);
            String name = suppTag.getString("Name");
            BlockPos pos = BlockPos.fromLong(suppTag.getLong("Pos"));

            IAuraSupplier supplier = CommonUtil.makeAuraSupplier(name, pos);
            if(supplier != null){
                supplier.readFromNBT(suppTag);
                this.addSupplier(dim, supplier);
            }
        }
    }

    private static class DimStorage{

        public final Map<BlockPos, IAuraSupplier> suppliers = new ConcurrentHashMap<BlockPos, IAuraSupplier>();
        public final int dimension;

        public DimStorage(int dimension){
            this.dimension = dimension;
        }

    }
}
