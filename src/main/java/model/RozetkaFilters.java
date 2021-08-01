package model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rozetkaFilters")

public class RozetkaFilters{

    private List<RozetkaFilter> rozetkaFilters;

    public RozetkaFilters(List<RozetkaFilter> rozetkaFilters) { this.rozetkaFilters = rozetkaFilters; }

    public List<RozetkaFilter> getRozetkaFilters() {
        return rozetkaFilters;
    }

    public void setRozetkaFilters(List<RozetkaFilter> rozetkaFilters) {
        this.rozetkaFilters = rozetkaFilters;
    }

}