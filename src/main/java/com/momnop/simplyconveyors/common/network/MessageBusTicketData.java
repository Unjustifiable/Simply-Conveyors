package com.momnop.simplyconveyors.common.network;

import io.netty.buffer.ByteBuf;
import mcjty.lib.tools.ItemStackTools;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.momnop.simplyconveyors.common.items.ItemBusTicket;

public class MessageBusTicketData implements IMessage
{
	private String name;

	public MessageBusTicketData()
	{
	}

	public MessageBusTicketData(String name)
	{
		this.name = name;
	}

	public void fromBytes(ByteBuf buf)
	{
		this.name = ByteBufUtils.readUTF8String(buf);
	}

	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, name);
	}

	public static class Handler extends AbstractClientMessageHandler<MessageBusTicketData>
	{
		public IMessage handleClientMessage(EntityPlayer player, MessageBusTicketData message, MessageContext ctx)
		{
			if((player != null) && (message != null) && (ctx != null))
			{
				if(player.getHeldItemMainhand() != ItemStackTools.getEmptyStack() && player.getHeldItemMainhand().getItem() instanceof ItemBusTicket)
				{
					player.getHeldItemMainhand().setStackDisplayName(message.name);
				}

				if(player.getHeldItemOffhand() != ItemStackTools.getEmptyStack() && player.getHeldItemOffhand().getItem() instanceof ItemBusTicket)
				{
					player.getHeldItemOffhand().setStackDisplayName(message.name);
				}
			}
			return null;
		}
	}
}
