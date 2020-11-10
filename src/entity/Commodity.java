package entity;

public class Commodity {
    private Integer id;
    private String name;
    private Double price;
    private int stock;
    private String type;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Commodity(Integer id, String name, Double price, int stock, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    public Commodity(Integer id, String name, Double price, String type, int amount){
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.type = type;
    }

}
