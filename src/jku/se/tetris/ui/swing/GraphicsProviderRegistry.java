package jku.se.tetris.ui.swing;

public final class GraphicsProviderRegistry {
	private static GraphicsAdaptor graphicsAdaptor;

	public static boolean hasProvider() {
		return graphicsAdaptor != null;
	}

	public static GraphicsAdaptor getProvider() {
		return graphicsAdaptor;
	}

	public static void setProvider(GraphicsAdaptor graphicsAdaptor) {
		GraphicsProviderRegistry.graphicsAdaptor = graphicsAdaptor;
	}
}
