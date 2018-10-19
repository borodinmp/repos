package home.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please fill the text")
    @Length(max = 255, message = "Text is to long")
    private String nameOrg;

    @NotBlank(message = "Please fill the text")
    @Length(max = 255, message = "Text is to long")
    private String inn;

    @NotBlank(message = "Please fill the text")
    @Length(max = 255, message = "Text is to long")
    private String ogrnn;

    @NotBlank(message = "Please fill the text")
    @Length(max = 255, message = "Text is to long")
    private String addressOrg;

    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Message() {
    }

    public Message(String nameOrg, String inn, String ogrnn, String addressOrg, User user) {
        this.author = user;
        this.nameOrg = nameOrg;
        this.inn = inn;
        this.ogrnn = ogrnn;
        this.addressOrg = addressOrg;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}