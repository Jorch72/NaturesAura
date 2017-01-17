package de.ellpeck.naturesaura.mod.scroll;

import de.ellpeck.naturesaura.api.NaturesAuraAPI;

public final class ScrollRegistry{

    public static void init(){
        NaturesAuraAPI.registerScrollPage(new ScrollPage("testy"));
    }

}
