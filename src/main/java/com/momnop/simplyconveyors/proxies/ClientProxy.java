package com.momnop.simplyconveyors.proxies;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import com.momnop.simplyconveyors.blocks.roads.BlockConnectingColored;
import com.momnop.simplyconveyors.client.RenderRegistry;
import com.momnop.simplyconveyors.helpers.CodeHelper;
import com.momnop.simplyconveyors.items.ItemEntityFilter;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerModels()
	{
		RenderRegistry.registerRenderers();
	}

	@Override
	public void registerWoolColored(Block block)
	{
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor()
		{
			@Override
			public int colorMultiplier(IBlockState iBlockState, IBlockAccess iBlockAccess, BlockPos blockPos, int i)
			{
				if(iBlockState != null)
				{
					return EnumDyeColor.byMetadata(iBlockState.getValue(BlockConnectingColored.COLOR).getMetadata()).getMapColor().colorValue;
				}
				return -1;
			}
		}, block);

		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor()
		{
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex)
			{
				if(stack != ItemStack.EMPTY)
				{
					return EnumDyeColor.byMetadata(stack.getMetadata()).getMapColor().colorValue;
				}
				return -1;
			}
		}, block);
	}
	
	@Override
	public void registerFoliageColored(Block block) {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor()
		{
			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int i)
			{
				if (worldIn != null && state != null && pos != null) {
					return worldIn.getBiome(pos).getFoliageColorAtPos(pos);
				}
				return -1;
			}
		}, block);

		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor()
		{
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex)
			{
				if (stack != ItemStack.EMPTY) {
					return Minecraft.getMinecraft().world.getBiome(Minecraft.getMinecraft().player.getPosition()).getFoliageColorAtPos(Minecraft.getMinecraft().player.getPosition());
				}
				return -1;
			}
		}, block);
	}

	@Override
	public void registerTierColor(Item item)
	{
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor()
		{
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex)
			{
				if(stack != ItemStack.EMPTY)
				{
					return CodeHelper.getTierColor(stack.getMetadata());
				}
				return -1;
			}
		}, item);
	}

	@Override
	public void registerFilterColor(Item item)
	{
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor()
		{
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex)
			{
				if(stack.hasTagCompound() && stack != ItemStack.EMPTY && stack.getItem() instanceof ItemEntityFilter)
				{
					// NBTTagCompound compound = stack.getTagCompound();
					// String filter = compound.getString("filter");
					// Class<Entity> entityClass = null;
					// try
					// {
					// entityClass = (Class<Entity>) Class.forName(filter);
					// }
					// catch (ClassNotFoundException e)
					// {
					// e.printStackTrace();
					// }
					//
					// if(entityClass != null)
					// {
					// if
					// (EntityList.ENTITY_EGGS.containsKey(EntityRegistry.getEntry(entityClass).getRegistryName()))
					// {
					// return
					// EntityList.ENTITY_EGGS.get(EntityRegistry.getEntry(entityClass).getRegistryName()).primaryColor;
					// }
					// }
				}
				return -1;
			}
		}, item);
	}
}
