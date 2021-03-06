package com.zundrel.simplyconveyors.common.blocks.advanced;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.zundrel.simplyconveyors.common.blocks.base.BlockInverseConveyor;
import com.zundrel.simplyconveyors.common.blocks.tiles.TileAdvancedConveyor;

public class BlockInverseAdvancedConveyor extends BlockInverseConveyor
{
	public BlockInverseAdvancedConveyor(String unlocalizedName, double speed, Material material, float hardness, SoundType type, CreativeTabs tab)
	{
		super(unlocalizedName, speed, material, hardness, type, tab);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileAdvancedConveyor();
	}
}
