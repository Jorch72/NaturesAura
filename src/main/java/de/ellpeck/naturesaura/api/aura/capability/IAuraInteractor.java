package de.ellpeck.naturesaura.api.aura.capability;

import de.ellpeck.naturesaura.api.aura.AuraType;

import java.util.Collection;

public interface IAuraInteractor{

    /**
     * Tries to insert the given amount of the given type of Aura into this extractor
     *
     * @param type       The type to insert
     * @param amount     The amount to insert
     * @param simulate   If this action should be simulated
     * @param doInternal Should only be true when no checks of max insert and extract should be done
     * @return The amount that was inserted
     */
    int insertAura(AuraType type, int amount, boolean simulate, boolean doInternal);

    /**
     * Tries to extract the given amount of the given type of Aura out of this interactor
     *
     * @param type       The type to extract
     * @param amount     The amount to extract
     * @param simulate   If this action should be simulated
     * @param doInternal Should only be true when no checks of max insert and extract should be done
     * @return The amount that was extracted
     */
    int extractAura(AuraType type, int amount, boolean simulate, boolean doInternal);

    int getStoredAura(AuraType type);

    boolean setStoredAura(AuraType type, int amount);

    int getTotalStoredAmount();

    Collection<AuraType> getStoredTypes();
}
