package de.ellpeck.naturesaura.api.aura.capability;

import de.ellpeck.naturesaura.api.aura.AuraType;
import net.minecraft.nbt.NBTTagCompound;

public class AuraStorage implements IAuraInteractor{

    private final AuraType type;
    private final int maxTotalAmount;
    private final int maxInsert;
    private final int maxExtract;

    private int currentAmount;

    public AuraStorage(int maxTotalAmount, int maxInsert, int maxExtract, AuraType type){
        this.maxTotalAmount = maxTotalAmount;
        this.type = type;
        this.maxInsert = maxInsert;
        this.maxExtract = maxExtract;
    }

    @Override
    public int insertAura(AuraType type, int amount, boolean simulate, boolean doInternal){
        if(doInternal || this.maxInsert > 0){
            if(type == this.type){
                int space = this.maxTotalAmount-this.currentAmount;
                if(space > 0){
                    int insert = Math.min(space, doInternal ? amount : Math.min(this.maxInsert, amount));

                    if(!simulate){
                        this.currentAmount += insert;
                    }

                    return insert;
                }
            }
        }
        return 0;
    }

    @Override
    public int extractAura(AuraType type, int amount, boolean simulate, boolean doInternal){
        if(doInternal || this.maxExtract > 0){
            if(this.type == type){
                if(this.currentAmount > 0){
                    int extract = Math.min(this.currentAmount, doInternal ? amount : Math.min(this.maxExtract, amount));

                    if(!simulate){
                        this.currentAmount -= extract;
                    }

                    return extract;
                }
            }
        }
        return 0;
    }

    @Override
    public void setStoredAura(int amount){
        this.currentAmount = amount;
    }

    @Override
    public int getStoredAura(){
        return this.currentAmount;
    }

    @Override
    public int getAuraLimit(){
        return this.maxTotalAmount;
    }

    @Override
    public int getMaxInsert(){
        return this.maxInsert;
    }

    @Override
    public int getMaxExtract(){
        return this.maxExtract;
    }

    @Override
    public AuraType getType(){
        return this.type;
    }

    public void writeToNBT(NBTTagCompound compound){
        compound.setInteger("Amount", this.currentAmount);
    }

    public void readFromNBT(NBTTagCompound compound){
        this.currentAmount = compound.getInteger("Amount");
    }
}
