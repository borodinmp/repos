package home.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nameOrg;
    private String inn;
    private String ogrnn;
    private String addressOrg;

    public Message() {
    }

    public Message(String nameOrg, String inn, String ogrnn, String addressOrg) {
        this.nameOrg = nameOrg;
        this.inn = inn;
        this.ogrnn = ogrnn;
        this.addressOrg = addressOrg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOrg() {
        return nameOrg;
    }

    public void setNameOrg(String nameOrg) {
        this.nameOrg = nameOrg;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getOgrnn() {
        return ogrnn;
    }

    public void setOgrnn(String ogrnn) {
        this.ogrnn = ogrnn;
    }

    public String getAddressOrg() {
        return addressOrg;
    }

    public void setAddressOrg(String addressOrg) {
        this.addressOrg = addressOrg;
    }
}