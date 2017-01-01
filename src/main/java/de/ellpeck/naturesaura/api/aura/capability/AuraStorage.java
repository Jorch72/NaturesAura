package de.ellpeck.naturesaura.api.aura.capability;

import de.ellpeck.naturesaura.api.aura.AuraType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class AuraStorage implements IAuraInteractor{

    private final Map<AuraType, Integer> storedAura = new HashMap<AuraType, Integer>();

    private final AuraType[] possibleTypes;
    private final int maxTotalAmount;

    public AuraStorage(int maxTotalAmount, AuraType... possibleTypes){
        this.maxTotalAmount = maxTotalAmount;
        this.possibleTypes = possibleTypes;
    }

    @Override
    public int insertAura(AuraType type, int amount, boolean simulate){
        if(ArrayUtils.contains(this.possibleTypes, type)){
            int space = this.maxTotalAmount-this.getTotalStored();
            if(space > 0){
                int insert = Math.min(space, amount);

                if(!simulate){
                    this.storedAura.put(type, this.storedAura.get(type)+insert);
                }

                return insert;
            }
        }
        return 0;
    }

    @Override
    public int extractAura(AuraType type, int amount, boolean simulate){
        int stored = this.storedAura.get(type);
        if(stored > 0){
            int extract = Math.min(amount, stored);

            if(!simulate){
                this.storedAura.put(type, stored-extract);
            }

            return extract;
        }
        return 0;
    }

    @Override
    public Map<AuraType, Integer> getStoredAura(){
        return this.storedAura;
    }

    public int getTotalStored(){
        int amount = 0;
        for(int stored : this.storedAura.values()){
            amount += stored;
        }
        return amount;
    }
}
