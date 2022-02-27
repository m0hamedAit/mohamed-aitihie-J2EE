package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
