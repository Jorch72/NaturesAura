package de.ellpeck.naturesaura.mod.tile;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.capability.FiniteAuraStorage;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityAncientLeaves extends TileEntityBase{

    public final FiniteAuraStorage supply = new FiniteAuraStorage(NaturesAuraAPI.AURA_LIFE, 10, 1);

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        this.supply.writeToNBT(compound);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        this.supply.readFromNBT(compound);
        super.readFromNBT(compound);
    }

    @Override
    public void invalidate(){
        super.invalidate();
        NaturesAuraAPI.getAuraHandler().removeSupplier(this.world, this.pos);
    }

    @Override
    public void onChunkUnload(){
        super.onChunkUnload();
        NaturesAuraAPI.getAuraHandler().removeSupplier(this.world, this.pos);
    }

    @Override
    public void onLoad(){
        super.onLoad();
        NaturesAuraAPI.getAuraHandler().addSupplier(this.world, this.pos, this.supply);
    }

    @Override
    public void validate(){
        super.validate();
        NaturesAuraAPI.getAuraHandler().addSupplier(this.world, this.pos, this.supply);
    }
}
