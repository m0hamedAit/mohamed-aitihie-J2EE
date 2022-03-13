package annotations;

import java.util.ArrayList;

public class AnnotationConfigApplicationContext {
    private ArrayList<String> packages;

    public AnnotationConfigApplicationContext(String... packages) {
        for(String packag:packages){
            this.packages.add(packag);
            //TODO finding all classes in each package
            //TODO Instantiate all classes that have @Component annotation if there is no @Scope(value="prototype")
            //TODO Instantiate classes that are @Autowired
                //TODO an error maybe raised, if there is 2 classes that can be instanciated for the same spot, so search @Qualifier(name) or raise error
        }
    }

    public <T> T getBean(Class<T> requiredType){
        // TODO return requiredType instantiation

        return null;
    }
}
