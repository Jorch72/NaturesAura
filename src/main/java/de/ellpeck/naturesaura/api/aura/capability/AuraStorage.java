package de.ellpeck.naturesaura.api.aura.capability;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.AuraType;
import net.minecraft.nbt.NBTTagCompound;

public class AuraStorage implements IAuraStorage{

    protected final int maxTotalAmount;
    protected final int maxInsert;
    protected final int maxExtract;

    protected AuraType currentType;
    protected int currentAmount;

    public AuraStorage(int maxTotalAmount, int maxInsert, int maxExtract){
        this.maxTotalAmount = maxTotalAmount;
        this.maxInsert = maxInsert;
        this.maxExtract = maxExtract;
    }

    @Override
    public int insertAura(AuraType type, int amount, boolean simulate, boolean doInternal){
        if(doInternal || this.maxInsert > 0){
            if(this.fitsType(type)){
                int space = this.maxTotalAmount-this.currentAmount;
                if(space > 0){
                    int insert = Math.min(space, doInternal ? amount : Math.min(this.maxInsert, amount));

                    if(!simulate){
                        this.setStoredAura(type, this.currentAmount+insert);
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
            if(this.fitsType(type)){
                if(this.currentAmount > 0){
                    int extract = Math.min(this.currentAmount, doInternal ? amount : Math.min(this.maxExtract, amount));

                    if(!simulate){
                        this.setStoredAura(type, this.currentAmount-extract);
                    }

                    return extract;
                }
            }
        }
        return 0;
    }

    protected boolean fitsType(AuraType type){
        return this.currentType == null || type == this.currentType;
    }

    @Override
    public void setStoredAura(AuraType type, int amount){
        this.setStoredAura(amount);
        this.currentType = type;
    }

    @Override
    public void setStoredAura(int amount){
        this.currentAmount = amount;

        if(this.currentAmount <= 0){
            this.currentType = null;
        }
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
    public AuraType getCurrentType(){
        return this.currentType;
    }

    public boolean isFull(){
        return this.getStoredAura() >= this.getAuraLimit();
    }

    public void writeToNBT(NBTTagCompound compound){
        if(this.currentType != null && this.currentAmount > 0){
            compound.setString("Type", this.currentType.getRegisteredName());
            compound.setInteger("Amount", this.currentAmount);
        }
    }

    public void readFromNBT(NBTTagCompound compound){
        String typeName = compound.getString("Type");
        AuraType type = NaturesAuraAPI.AURA_REGISTRY.get(typeName);
        int amount = compound.getInteger("Amount");

        if(type != null && amount > 0){
            this.currentType = type;
            this.currentAmount = amount;
        }
        else{
            this.currentType = null;
            this.currentAmount = 0;
        }
    }
}
