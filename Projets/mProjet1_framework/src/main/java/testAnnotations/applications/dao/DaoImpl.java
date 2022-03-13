package testAnnotations.applications.dao;

import annotations.Component;

@Component
public class DaoImpl implements IDao {

    @Override
    public int getData(){
        return 2;
    }
}
