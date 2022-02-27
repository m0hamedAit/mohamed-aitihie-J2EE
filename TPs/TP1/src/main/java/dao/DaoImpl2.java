package dao;

import org.springframework.stereotype.Component;

@Component("dao2")
public class DaoImpl2 implements IDao{

    @Override
    public int getData(){
        return 4;
    }
}