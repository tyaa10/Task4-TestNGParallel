package model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rozetkaFilters")
public class RozetkaFilters implements Serializable {

    @XmlElement(name = "rozetkaFilter")
    private List<RozetkaFilter> rozetkaFilters;

    public RozetkaFilters(){};

    public RozetkaFilters(List<RozetkaFilter> rozetkaFilters) { this.rozetkaFilters = rozetkaFilters; }

    public List<RozetkaFilter> getRozetkaFilters() {
        return rozetkaFilters;
    }

    public void setRozetkaFilters(List<RozetkaFilter> rozetkaFilters) {
        this.rozetkaFilters = rozetkaFilters;
    }
}