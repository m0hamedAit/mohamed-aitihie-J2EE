package testAnnotations;

import annotations.AnnotationConfigApplicationContext;
import testAnnotations.metier.IMetier;
import testXML.dao.IDao;
import xmlConf.ClassPathXmlApplicationContext;

public class presentationAnnotation {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("testAnnotations.dao", "testAnnotations.metier");

        IMetier metier = context.getBean(IMetier.class);

        System.out.println(metier.calcul());
    }

}
