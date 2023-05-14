import java.io.File;

public class Main {

    public static void main(String[] args) {
        XtextResourceSet resourceSet = new XtextResourceSet();
        File tmpClassDirectory = new File("temp/");

        installJvmTypeProvider(resourceSet, tmpClassDirectory);
    }

    protected static void installJvmTypeProvider(XtextResourceSet resourceSet, File tmpClassDirectory) {
        internalInstallJvmTypeProvider(resourceSet, tmpClassDirectory, false);
    }


  
}