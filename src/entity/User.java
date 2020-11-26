package entity;

public class User {
    private String id;
    private String name;
    private String password;
    private int age;
    private int sex;
    private String car;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public User(String id, String name, String password, int age, int sex, String car) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.car = car;
    }
}
