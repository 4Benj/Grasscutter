package emu.grasscutter.utils.dependencies;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.plugin.PluginManager;
import emu.grasscutter.utils.Utils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class DependencyLoader {

    public static final String DEPENDENCY_LOCATION = "./libraries/";

    public static void loadDependencies() {
        File dependencyDir = new File(Utils.toFilePath(DEPENDENCY_LOCATION));

        if(!dependencyDir.exists() && !dependencyDir.mkdir()) {
            Grasscutter.getLogger().error(String.format("FAILED TO CREATE '{}'! UNABLE TO LOAD NECESSARY DEPENDENCIES", DEPENDENCY_LOCATION));
            System.exit(0); // TODO: Better exit code?
            return;
        }

        File[] files = dependencyDir.listFiles();
        if (files == null || files.length <= 0) {
            Grasscutter.getLogger().info("Downloading all dependencies...");
            // The directory is empty, we need to download all dependencies. We shall come back to this once downloading is complete.
            DependencyDownloader.DownloadAllDependencies(DEPENDENCY_LOCATION); // TODO: Call loadDependencies again once this completes.

            return;
        }

        // TODO: Verify that all the required dependencies have been downloaded.

        for(var file : files) {
            Grasscutter.getLogger().info(file.getName());
        }

        List<File> libraries = Arrays.stream(files)
            .filter(file -> file.getName().endsWith(".jar"))
            .toList();

        URL[] libraryNames = new URL[libraries.size()];
        libraries.forEach(plugin -> {
            try {
                libraryNames[libraries.indexOf(plugin)] = plugin.toURI().toURL();
            } catch (MalformedURLException exception) {
                Grasscutter.getLogger().error("Unable to load library.", exception);
                return;
            }
        });

        // Initialize all libraries.
        for (var library : libraries) {
            try {
                URL url = library.toURI().toURL();
                try (URLClassLoader loader = new URLClassLoader(new URL[]{url})) {
                    // TODO: complete
                    // Find the /META-INF/MANIFEST.MF file for each library.
                    URL manifestFile = loader.findResource("META-INF/MANIFEST.MF");
                    // Open the manifest file.
                    Manifest manifest = new Manifest(manifestFile.openStream());

                    // Create a JAR file instance from the library's URL.
                    JarFile jarFile = new JarFile(library);
                    // Load all class files from the JAR file.
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        if (entry.isDirectory() || !entry.getName().endsWith(".class") || entry.getName().contains("module-info"))
                            continue;
                        String className = entry.getName().replace(".class", "").replace("/", ".");
                        Grasscutter.class.getClassLoader().loadClass(className);
                    }
                } catch (IOException e) {
                    // TODO: pretty print
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                // TODO: pretty print
                e.printStackTrace();
            }
        }
    }
}
