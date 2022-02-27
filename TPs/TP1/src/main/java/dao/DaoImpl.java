package dao;

import org.springframework.stereotype.Component;

@Component
public class DaoImpl implements IDao{

    @Override
    public int getData(){
        return 2;
    }
}
