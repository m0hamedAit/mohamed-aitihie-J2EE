package annotations;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MyAnnotationConfigApplicationContext {
    HashMap<Class, Object> classInstances = new HashMap<>();
    List<String> packages = new ArrayList<>();

    public MyAnnotationConfigApplicationContext(String... packages) {
        this.packages.addAll(Arrays.asList(packages));
    }

    public void getClasses() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        ArrayList<Class> classes=new ArrayList<Class>();
        AccessPackageReflexions accessPackageReflexions = new AccessPackageReflexions();
        Set<Class<?>> subTypesOf=null;
        String method=null;

        for(String packageName : packages) {
            Reflections reflexions = new Reflections(new ConfigurationBuilder()
                    .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                    .addUrls(ClasspathHelper.forJavaClassPath())
                    .filterInputsBy(new FilterBuilder()
                            .include(FilterBuilder.prefix(packageName))));

            subTypesOf = reflexions.getSubTypesOf(Object.class);
            for( Class c :subTypesOf) {
                if(c.toString().contains("class")) {
                    Object o = c.newInstance();
                    classInstances.put(c.getInterfaces()[0], o);
                    classes.add(c);
                }
            }


        }
        //injection
        for(Class cl : classes) {
            if(cl.getAnnotations().length>0) {
                if( cl.getAnnotations()[0].toString().contains("Component") && cl.getDeclaredFields().length>0 ) {
                    Field[] fields =cl.getDeclaredFields();
                    for(Field f : fields) {
                        if(f.getAnnotations()[0].toString().contains("Autowired"))
                        {
                            try {
                                method = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1, f.getName().length());
                                Method methode = cl.getMethod(method, f.getType());
                                methode.invoke(classInstances.get(cl.getInterfaces()[0]), classInstances.get(f.getType()));

                            }catch(Exception e){

                            }
                        }
                    }
                }
            }
        }

    }
    public HashMap<Class, Object> getInstances(){
        return classInstances;
    }

}
