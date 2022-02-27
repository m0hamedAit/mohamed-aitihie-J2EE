package presentation;

import dao.DaoImpl;
import dao.IDao;
import metier.MetierImpl;

public class StatiqueInst {
    public static void main(String[] args){
        /*
            Injection des dependances par Instanciation statique
         */
        IDao dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);
        System.out.println(metier.calcul());

    }
}
