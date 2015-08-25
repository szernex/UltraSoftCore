package org.szernex.usc.proxy;

public interface IProxy
{
	ClientProxy getClientProxy();

	void registerEventHandlers();

	void initRenderingAndTextures();

	void registerEntities();
}
