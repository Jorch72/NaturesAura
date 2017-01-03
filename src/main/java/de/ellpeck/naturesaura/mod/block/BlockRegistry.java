package de.ellpeck.naturesaura.mod.block;

import de.ellpeck.naturesaura.mod.block.tree.ancient.BlockAncientBark;
import de.ellpeck.naturesaura.mod.block.tree.ancient.BlockAncientLeaves;
import de.ellpeck.naturesaura.mod.block.tree.ancient.BlockAncientLog;
import de.ellpeck.naturesaura.mod.block.tree.ancient.BlockAncientSapling;
import net.minecraft.block.Block;

public final class BlockRegistry{

    public static Block blockAncientLeaves;
    public static Block blockAncientLog;
    public static Block blockAncientBark;
    public static Block blockAncientSapling;

    public static void preInit(){
        blockAncientLeaves = new BlockAncientLeaves();
        blockAncientLog = new BlockAncientLog();
        blockAncientBark = new BlockAncientBark();
        blockAncientSapling = new BlockAncientSapling();
    }

}
