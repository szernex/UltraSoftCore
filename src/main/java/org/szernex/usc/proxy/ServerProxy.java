package org.szernex.usc.proxy;

public class ServerProxy extends CommonProxy
{
	@Override
	public ClientProxy getClientProxy()
	{
		return null;
	}

	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
	}

	@Override
	public void initRenderingAndTextures()
	{

	}
}
