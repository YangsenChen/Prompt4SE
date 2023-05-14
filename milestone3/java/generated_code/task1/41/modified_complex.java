public class JvmTypeProviderManager {
	private final XtextResourceSet resourceSet;
	private final File classDirectory;
	private final JvmTypeProviderInstallationStrategy installationStrategy;

	public JvmTypeProviderManager(XtextResourceSet resourceSet, File classDirectory,
			JvmTypeProviderInstallationStrategy installationStrategy) {
		this.resourceSet = resourceSet;
		this.classDirectory = classDirectory;
		this.installationStrategy = installationStrategy;
	}

	public void installJvmTypeProvider() {
		installationStrategy.installJvmTypeProvider(resourceSet, classDirectory);
	}
}

public interface JvmTypeProviderInstallationStrategy {
	void installJvmTypeProvider(XtextResourceSet resourceSet, File classDirectory);
}

public class ExternalJvmTypeProviderInstallationStrategy implements JvmTypeProviderInstallationStrategy {
	@Override
	public void installJvmTypeProvider(XtextResourceSet resourceSet, File classDirectory) {
		//Install JvmTypeProvider from an external source using the given ResourceSet and ClassDirectory
	}
}

public class InternalJvmTypeProviderInstallationStrategy implements JvmTypeProviderInstallationStrategy {
	@Override
	public void installJvmTypeProvider(XtextResourceSet resourceSet, File classDirectory) {
		//Install JvmTypeProvider from an internal source using the given ResourceSet and ClassDirectory
	}
}

public class Application {
	public static void main(String[] args) {
		XtextResourceSet resourceSet = new XtextResourceSet();
		File classDirectory = new File("tmpClassDirectory");
		JvmTypeProviderInstallationStrategy installationStrategy = new InternalJvmTypeProviderInstallationStrategy();

		JvmTypeProviderManager jvmTypeProviderManager = new JvmTypeProviderManager(resourceSet, classDirectory,
				installationStrategy);

		jvmTypeProviderManager.installJvmTypeProvider();
	}
}