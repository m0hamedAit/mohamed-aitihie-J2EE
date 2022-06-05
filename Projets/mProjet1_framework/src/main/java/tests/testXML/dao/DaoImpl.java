package tests.testXML.dao;

public class DaoImpl implements IDao{

    @Override
    public int getData(){
        System.out.println("Dao");
        return 2;
    }
}
