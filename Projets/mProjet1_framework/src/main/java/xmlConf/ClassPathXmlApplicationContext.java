package xmlConf;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class ClassPathXmlApplicationContext {
    private String file;

    public ClassPathXmlApplicationContext(String file) {
        this.file = file;
    }

    public Object getBean(String name) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Beans.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Beans b = (Beans) unmarshaller.unmarshal(new File(this.file));
        List<Bean> beans = b.getBeans();


        for(Bean bean:beans){
            if(bean.getId().equals(name)){
                Class cl = Class.forName(bean.getCclass());
                return cl.newInstance();
            }
        }

        return null;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
