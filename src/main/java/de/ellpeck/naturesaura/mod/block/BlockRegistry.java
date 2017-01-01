package de.ellpeck.naturesaura.mod.block;

import de.ellpeck.naturesaura.mod.block.tree.BlockAncientLeaves;
import de.ellpeck.naturesaura.mod.block.tree.BlockAncientLog;
import net.minecraft.block.Block;

public final class BlockRegistry{

    public static Block blockAncientLeaves;
    public static Block blockAncientLog;

    public static void preInit(){
        blockAncientLeaves = new BlockAncientLeaves();
        blockAncientLog = new BlockAncientLog();
    }

}
