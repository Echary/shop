package entity;

public class Address {

    private Integer id;
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Address(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
