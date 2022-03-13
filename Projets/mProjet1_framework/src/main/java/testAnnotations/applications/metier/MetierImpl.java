package testAnnotations.applications.metier;

import annotations.Autowired;
import testXML.dao.IDao;

public class MetierImpl implements IMetier {
    @Autowired
    private IDao dao;

    public MetierImpl() {
        System.out.println("MetierImpl created...");
    }

    @Override
    public int calcul(){
        System.out.println("Version dataBase");
        return dao.getData() * 4 ;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
