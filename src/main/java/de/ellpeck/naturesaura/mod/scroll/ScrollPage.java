package de.ellpeck.naturesaura.mod.scroll;

import de.ellpeck.naturesaura.api.scroll.IScrollPage;

import java.util.Collections;
import java.util.List;

public class ScrollPage implements IScrollPage{

    protected final String name;

    public ScrollPage(String name){
        this.name = name;
    }

    @Override
    public String getRegisteredName(){
        return this.name;
    }

    @Override
    public String getUnlocalizedDisplayName(){
        return "scroll."+this.name+".name";
    }

    @Override
    public List<String> getTooltip(){
        return Collections.emptyList();
    }
}
