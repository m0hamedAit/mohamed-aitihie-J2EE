package testAnnotations.applications;

import testXML.dao.IDao;
import xmlConf.ClassPathXmlApplicationContext;

public class presentationAnnotation {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("src/main/resources/applicationContext.xml");
        IDao dao = (IDao) context.getBean("dao");

        System.out.println(dao.getData());

    }

}
