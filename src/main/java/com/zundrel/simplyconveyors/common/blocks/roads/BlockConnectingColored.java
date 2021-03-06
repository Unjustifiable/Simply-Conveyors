package com.zundrel.simplyconveyors.common.blocks.roads;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.zundrel.simplyconveyors.SimplyConveyors;

public class BlockConnectingColored extends BlockConnecting {

	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);
	
    public BlockConnectingColored(String unlocalizedName, Material materialIn, float hardness, SoundType type, CreativeTabs tab) {
		super(unlocalizedName, materialIn, hardness, type, tab);
		
		this.setDefaultState(this.getDefaultState().withProperty(COLOR, EnumDyeColor.WHITE));
	}
    
    @Override
    public BlockRenderLayer getBlockLayer()
    {
    	return BlockRenderLayer.CUTOUT;
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
    }

    @Override
    public BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] { EAST, NORTH, SOUTH, WEST, COLOR, NONE });
    }
    
    @Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> subItems)
    {
    	for (EnumDyeColor enumdyecolor : EnumDyeColor.values())
        {
        	if (enumdyecolor != EnumDyeColor.BLACK) {
        		subItems.add(new ItemStack(this, 1, enumdyecolor.getMetadata()));
        	}
        }
    }
    
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
    	return new ItemStack(state.getBlock(), 1, state.getValue(COLOR).getMetadata());
    }
    
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn,
    		BlockPos pos) {
    	return MapColor.getBlockColor(((EnumDyeColor)state.getValue(COLOR)));
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
    }
    
    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos,
    		EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
    		EntityLivingBase placer, EnumHand hand) {
    	if (placer.getHeldItemMainhand() != ItemStack.EMPTY && placer.getHeldItemMainhand().getItem() == Item.getItemFromBlock(this)) {
    		return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(placer.getHeldItem(EnumHand.MAIN_HAND).getMetadata()));
    	} else if (placer.getHeldItemMainhand() != ItemStack.EMPTY && placer.getHeldItemOffhand().getItem() == Item.getItemFromBlock(this)) {
    		return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(placer.getHeldItem(EnumHand.OFF_HAND).getMetadata()));
    	}
    	return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta,
    			placer, hand);
    }
    
    @Override
    public void registerItemModel(ItemBlock ib) {
		for (int i2 = 0; i2 < 15; i2++) {
			SimplyConveyors.proxy.registerItemRenderer(ib, i2, this.getUnlocalizedName().substring(5));
		}
    }
}
