package de.ellpeck.naturesaura.api.aura.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;

public final class AuraCapabilities{

    @CapabilityInject(IAuraStorage.class)
    public static Capability<IAuraStorage> capabilityAura;

    public static class CapabilityAura<T extends IAuraStorage> implements IStorage<IAuraStorage>{

        @Override
        public NBTBase writeNBT(Capability<IAuraStorage> capability, IAuraStorage instance, EnumFacing side){
            return null;
        }

        @Override
        public void readNBT(Capability<IAuraStorage> capability, IAuraStorage instance, EnumFacing side, NBTBase nbt){

        }
    }
}
