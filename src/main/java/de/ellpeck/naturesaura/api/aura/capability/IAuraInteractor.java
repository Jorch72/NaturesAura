package de.ellpeck.naturesaura.api.aura.capability;

import de.ellpeck.naturesaura.api.aura.AuraType;

import java.util.List;
import java.util.Map;

public interface IAuraInteractor{

    /**
     * Tries to insert the given amount of the given type of Aura into this extractor
     * @param type The type to insert
     * @param amount The amount to insert
     * @param simulate If this action should be simulated
     * @return The amount that was inserted
     */
    int insertAura(AuraType type, int amount, boolean simulate);

    /**
     * Tries to extract the given amount of the given type of Aura out of this interactor
     *
     * @param type The type to extract
     * @param amount The amount to extract
     * @param simulate If this action should be simulated
     * @return The amount that was extracted
     */
    int extractAura(AuraType type, int amount, boolean simulate);

    int getStoredAura(AuraType type);
}
