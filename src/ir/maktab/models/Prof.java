package ir.maktab.models;

/**
 * Created by nader on 11/18/2017.
 */
public class Prof {
    private Integer id;
    private String name;
    private String address;

    public Prof(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Prof(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Prof(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setDept(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Prof{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
