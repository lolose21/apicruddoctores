package models;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HospitalCod {

    private List<String> hospitales;

    public List<String> getHospitales() {
        return hospitales;
    }

    public void setHospitales(List<String> hospitales) {
        this.hospitales = hospitales;
    }

}
