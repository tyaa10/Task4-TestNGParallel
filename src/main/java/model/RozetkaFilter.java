package model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rozetkaFilter")

public class RozetkaFilter {

    private int id;
    private String productGroup;

    private String brand;
    private int sum;


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getProductGroup() { return productGroup; }
    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public int getSum() { return sum; }
    public void setSum(int sum) { this.sum = sum; }

    public String getBrand() { return brand;}
    public void setBrand(String brand) { this.brand = brand; }

}