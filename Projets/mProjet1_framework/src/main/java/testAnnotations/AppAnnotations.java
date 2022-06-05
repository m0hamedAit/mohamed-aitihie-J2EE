package testAnnotations;

import annotations.MyAnnotationConfigApplicationContext;
import testAnnotations.metier.IMetier;

import java.lang.reflect.InvocationTargetException;


public class AppAnnotations {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        MyAnnotationConfigApplicationContext context = new MyAnnotationConfigApplicationContext("testAnnotations.dao", "testAnnotations.metier");
        context.getClasses();  // create instances of classes annotated with @Component and @Autowired

        IMetier metier = (IMetier) context.getInstances().get(IMetier.class);

        System.out.println(metier.calcul());
    }

}
