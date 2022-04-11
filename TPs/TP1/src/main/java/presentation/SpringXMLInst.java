package presentation;

import metier.IMetier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXMLInst {
    public static void main(String[] args){
        /*
            Injection des dependances en utilisant Spring (XML version)
         */
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IMetier metier = (IMetier) context.getBean("metier");

        System.out.println(metier.calcul());

        context.close();
    }
}
