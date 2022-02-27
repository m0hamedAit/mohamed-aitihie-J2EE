package presentation;

import metier.IMetier;
import metier.MetierImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationsInst {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("dao", "metier", "presentaion");

        IMetier metier = context.getBean(IMetier.class);

        System.out.println(metier.calcul());
    }

}
