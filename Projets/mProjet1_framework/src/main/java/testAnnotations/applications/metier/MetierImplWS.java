package testAnnotations.applications.metier;

import testXML.dao.IDao;

public class MetierImplWS implements IMetier {
    private IDao dao;

    @Override
    public int calcul(){
        System.out.println("Version web service");
        return dao.getData() * 10 ;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
