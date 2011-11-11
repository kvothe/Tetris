package jku.se.tetris.model;

public final class GraphicsProviderRegistry {
	private static GraphicsAdaptor graphicsAdaptor;

	public static GraphicsAdaptor getProvider() {
		return graphicsAdaptor;
	}

	public static void setProvider(GraphicsAdaptor graphicsAdaptor) {
		GraphicsProviderRegistry.graphicsAdaptor = graphicsAdaptor;
	}
}
