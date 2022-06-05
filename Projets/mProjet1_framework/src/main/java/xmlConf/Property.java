package xmlConf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement(name="property")
@XmlAccessorType(XmlAccessType.FIELD)
public class Property implements Serializable {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String ref;
    @XmlAttribute
    private String value;

}
