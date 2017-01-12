package de.ellpeck.naturesaura.mod.block.tree;

import de.ellpeck.naturesaura.mod.item.ItemRegistry;
import de.ellpeck.naturesaura.mod.reg.IColorProvidingBlock;
import de.ellpeck.naturesaura.mod.reg.IColorProvidingItem;
import de.ellpeck.naturesaura.mod.util.ClientUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockGoldenLeaves extends BlockLeavesBase implements IColorProvidingBlock, IColorProvidingItem{

    private static final int COLOR = 0xFFD800;

    private static final int STAGE_FULL = 3;
    private static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, STAGE_FULL);

    public BlockGoldenLeaves(){
        super("golden_leaves");
    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event){
        super.onPreInit(event);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        boolean check = (meta & 4) != 0;
        boolean decay = (meta & 8) != 0;
        int stage = meta & STAGE_FULL;

        return this.getDefaultState().withProperty(CHECK_DECAY, check).withProperty(DECAYABLE, decay).withProperty(STAGE, stage);
    }

    @Override
    public int getMetaFromState(IBlockState state){
        boolean check = state.getValue(CHECK_DECAY);
        boolean decay = state.getValue(DECAYABLE);

        return (check ? 1 : 0) << 3 | (decay ? 1 : 0) << 2 | state.getValue(STAGE);
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE, STAGE);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand){
        super.updateTick(world, pos, state, rand);

        if(!world.isRemote){
            int stage = state.getValue(STAGE);
            if(stage < STAGE_FULL){
                if(world.rand.nextDouble() >= 0.5){
                    world.setBlockState(pos, state.withProperty(STAGE, stage+1));
                }
            }

            EnumFacing facing = EnumFacing.random(rand);
            BlockPos otherPos = pos.offset(facing);
            IBlockState otherState = world.getBlockState(otherPos);

            this.convertLeaves(world, otherPos, otherState);
        }
    }

    @SubscribeEvent
    public void onRightClick(RightClickBlock event){
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = player.getHeldItem(event.getHand());
        if(!stack.isEmpty() && stack.getItem() == Items.GOLD_NUGGET){
            BlockPos pos = event.getPos();
            IBlockState state = player.world.getBlockState(pos);

            if(this.convertLeaves(player.world, pos, state)){
                if(!player.world.isRemote && !player.isCreative()){
                    stack.shrink(1);
                }

                player.swingArm(event.getHand());
            }
        }
    }

    private boolean convertLeaves(World world, BlockPos pos, IBlockState state){
        Block block = state.getBlock();
        if(block instanceof BlockOldLeaf){
            EnumType type = state.getValue(BlockOldLeaf.VARIANT);
            if(type == EnumType.OAK){
                if(!world.isRemote){
                    IBlockState newState = this.getDefaultState().withProperty(CHECK_DECAY, state.getValue(CHECK_DECAY)).withProperty(DECAYABLE, state.getValue(DECAYABLE));
                    world.setBlockState(pos, newState, 2);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return state.getValue(STAGE) < STAGE_FULL ? Items.AIR : ItemRegistry.itemMaterial;
    }

    @Override
    protected int getSaplingDropChance(IBlockState state){
        return 3;
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos){
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockColor getBlockColor(){
        return new IBlockColor(){
            @Override
            public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex){
                if(world != null && pos != null){
                    int foliageColor = BiomeColorHelper.getFoliageColorAtPos(world, pos);
                    float ratio = (float)state.getValue(STAGE)/(float)STAGE_FULL;

                    return ClientUtil.blendColors(foliageColor, COLOR, 1-ratio);
                }
                else{
                    return COLOR;
                }
            }
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getItemColor(){
        return new IItemColor(){
            @Override
            public int getColorFromItemstack(ItemStack stack, int tintIndex){
                return COLOR;
            }
        };
    }
}
