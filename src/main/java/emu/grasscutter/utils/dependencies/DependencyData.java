package emu.grasscutter.utils.dependencies;

public class DependencyData {

    public Dependency[] dependencies;

    public final class Dependency {
        public String repo;
        public String group;
        public String name;
        public String version;
    }
}
