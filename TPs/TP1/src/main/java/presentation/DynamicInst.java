package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class DynamicInst {
    public static void main(String[] args) throws Exception {
        /*
            Injection des dependances par Instantiation dynamique
         */
        Scanner scanner = new Scanner(new File("config.txt"));

        String daoName = scanner.nextLine();
        Class daoClass = Class.forName(daoName);
        IDao dao = (IDao) daoClass.newInstance();

        String metierName = scanner.nextLine();
        Class metierClass = Class.forName(metierName);
        IMetier metier = (IMetier) metierClass.newInstance();

        Method method = metierClass.getMethod("setDao", IDao.class);
        // metier.setDao(dao)
        method.invoke(metier, dao);

        System.out.println(metier.calcul());

    }
}
