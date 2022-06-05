package annotations;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.*;

public class AccessPackageReflexions {

    public Reflections getPackageReflexions(String packageName) throws InstantiationException, IllegalAccessException {

        Reflections reflexions = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .addUrls(ClasspathHelper.forJavaClassPath())
                .filterInputsBy(new FilterBuilder()
                        .include(FilterBuilder.prefix(packageName))));

        return reflexions;
    }

}
