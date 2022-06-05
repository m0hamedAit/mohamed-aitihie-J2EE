package xmlConf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="bean")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bean implements Serializable {
    @XmlAttribute
    private String id;
    @XmlAttribute(name="class")
    private String cclass;
    private Property property;

    public String getId() {
        return id;
    }

    public String getCclass() {
        return cclass;
    }

}
