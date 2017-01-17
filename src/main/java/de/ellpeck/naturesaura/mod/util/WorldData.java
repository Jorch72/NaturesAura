package de.ellpeck.naturesaura.mod.util;

import de.ellpeck.naturesaura.mod.impl.AuraHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class WorldData extends WorldSavedData{

    public static final String DATA_TAG = ModUtil.MOD_ID+"data";
    private static WorldData data;

    public final AuraHandler auraHandler;

    public WorldData(String name){
        super(name);
        this.auraHandler = new AuraHandler(this);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        this.auraHandler.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        this.auraHandler.writeToNBT(compound);
        return compound;
    }

    public static WorldData get(World world){
        return get(world, false);
    }

    public static WorldData get(World world, boolean forceLoad){
        if(forceLoad || data == null){
            if(!world.isRemote){
                WorldSavedData savedData = world.loadData(WorldData.class, DATA_TAG);

                if(!(savedData instanceof WorldData)){
                    ModUtil.LOGGER.info("No WorldData found, creating...");

                    WorldData newData = new WorldData(DATA_TAG);
                    world.setData(DATA_TAG, newData);
                    data = newData;
                }
                else{
                    data = (WorldData)savedData;
                    ModUtil.LOGGER.info("Successfully loaded WorldData!");
                }
            }
        }

        return data;
    }

    public static void clear(){
        if(data != null){
            data = null;

            ModUtil.LOGGER.info("Clearing WorldData from an unloaded world!");
        }
    }
}
