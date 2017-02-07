package com.momnop.simplyconveyors;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import com.momnop.simplyconveyors.blocks.SimplyConveyorsBlocks;
import com.momnop.simplyconveyors.helpers.CodeHelper;
import com.momnop.simplyconveyors.info.ModInfo;

public class SimplyConveyorsRoadTab extends CreativeTabs
{

	List list;
	int meta = 0;
	
	public static SimplyConveyorsRoadTab INSTANCE = new SimplyConveyorsRoadTab();

	public SimplyConveyorsRoadTab()
	{
		super(ModInfo.MOD_ID + "_roads");
	}

	@Override
	public ItemStack getIconItemStack()
	{
		if (Minecraft.getMinecraft().world.getTotalWorldTime() % 20 == 0) {
			meta = CodeHelper.getRangedRandom(0, 14	);
		}
		return new ItemStack(SimplyConveyorsBlocks.road_broken, 1, meta);
	}

	@Override
	public ItemStack getTabIconItem()
	{
		return getIconItemStack();
	}

	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> list)
	{
		this.list = list;
		
		addBlock(SimplyConveyorsBlocks.asphault);
		addBlock(SimplyConveyorsBlocks.concrete);
		addBlock(SimplyConveyorsBlocks.mossy_concrete);
		addBlock(SimplyConveyorsBlocks.road_broken);
		addBlock(SimplyConveyorsBlocks.road_full);
	}

	private void addItem(Item item)
	{
		item.getSubItems(item, this, (NonNullList<ItemStack>) list);
	}

	private void addBlock(Block block)
	{
		ItemStack stack = new ItemStack(block);
		block.getSubBlocks(stack.getItem(), this, (NonNullList<ItemStack>) list);
	}

}