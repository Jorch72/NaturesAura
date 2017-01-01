package de.ellpeck.naturesaura.api.aura.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;

public final class AuraCapabilities{

    @CapabilityInject(IAuraInteractor.class)
    public static Capability<IAuraInteractor> capabilityAura;

    public static class CapabilityAura<T extends IAuraInteractor> implements IStorage<IAuraInteractor>{

        @Override
        public NBTBase writeNBT(Capability<IAuraInteractor> capability, IAuraInteractor instance, EnumFacing side){
            return null;
        }

        @Override
        public void readNBT(Capability<IAuraInteractor> capability, IAuraInteractor instance, EnumFacing side, NBTBase nbt){

        }
    }
}
