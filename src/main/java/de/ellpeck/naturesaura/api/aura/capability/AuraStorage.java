package de.ellpeck.naturesaura.api.aura.capability;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.AuraType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AuraStorage implements IAuraInteractor{

    private final Map<AuraType, Integer> storedAura = new HashMap<AuraType, Integer>();

    private final AuraType[] possibleTypes;
    private final int maxTotalAmount;
    private final int maxInsert;
    private final int maxExtract;

    public AuraStorage(int maxTotalAmount, int maxInsert, int maxExtract, AuraType... possibleTypes){
        this.maxTotalAmount = maxTotalAmount;
        this.possibleTypes = possibleTypes;
        this.maxInsert = maxInsert;
        this.maxExtract = maxExtract;
    }

    @Override
    public int insertAura(AuraType type, int amount, boolean simulate, boolean doInternal){
        if(doInternal || this.maxInsert > 0){
            if(ArrayUtils.contains(this.possibleTypes, type)){
                int space = this.maxTotalAmount-this.getTotalStoredAmount();
                if(space > 0){
                    int insert = Math.min(space, doInternal ? amount : Math.min(this.maxInsert, amount));

                    if(!simulate){
                        this.storedAura.put(type, this.storedAura.get(type)+insert);
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
            int stored = this.storedAura.get(type);
            if(stored > 0){
                int extract = Math.min(stored, doInternal ? amount : Math.min(this.maxExtract, amount));

                if(!simulate){
                    this.storedAura.put(type, stored-extract);
                }

                return extract;
            }
        }
        return 0;
    }

    @Override
    public int getStoredAura(AuraType type){
        return this.storedAura.get(type);
    }

    @Override
    public boolean setStoredAura(AuraType type, int amount){
        this.storedAura.put(type, amount);
        return true;
    }

    @Override
    public int getTotalStoredAmount(){
        int amount = 0;
        for(int stored : this.storedAura.values()){
            amount += stored;
        }
        return amount;
    }

    @Override
    public Collection<AuraType> getStoredTypes(){
        return this.storedAura.keySet();
    }

    public void writeToNBT(NBTTagCompound compound){
        NBTTagList list = new NBTTagList();

        for(AuraType type : this.storedAura.keySet()){
            NBTTagCompound tag = new NBTTagCompound();

            tag.setInteger("Amount", this.storedAura.get(type));
            tag.setString("Type", type.getName());

            list.appendTag(tag);
        }

        compound.setTag("Storage", list);
    }

    public void readFromNBT(NBTTagCompound compound){
        this.storedAura.clear();
        NBTTagList list = compound.getTagList("Storage", 10);

        for(int i = 0; i < list.tagCount(); i++){
            NBTTagCompound tag = list.getCompoundTagAt(i);

            AuraType type = NaturesAuraAPI.AURA_REGISTRY.get(tag.getString("Type"));
            if(type != null){
                this.storedAura.put(type, tag.getInteger("Amount"));
            }
        }
    }
}
