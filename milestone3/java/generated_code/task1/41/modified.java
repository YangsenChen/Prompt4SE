public class JvmTypeProviderBuilder {
	private XtextResourceSet resourceSet;
	private File tmpClassDirectory;
	private boolean internal;

	public JvmTypeProviderBuilder resourceSet(XtextResourceSet resourceSet) {
		this.resourceSet = resourceSet;
		return this;
	}

	public JvmTypeProviderBuilder tmpClassDirectory(File tmpClassDirectory) {
		this.tmpClassDirectory = tmpClassDirectory;
		return this;
	}

	public JvmTypeProviderBuilder internal(boolean internal) {
		this.internal = internal;
		return this;
	}

	@Deprecated
	public void installJvmTypeProvider() {
		internalInstallJvmTypeProvider(resourceSet, tmpClassDirectory, internal);
	}

	protected void internalInstallJvmTypeProvider(XtextResourceSet resourceSet, File tmpClassDirectory, boolean internal) {
		// install JvmTypeProvider with the specified parameters
	}
}