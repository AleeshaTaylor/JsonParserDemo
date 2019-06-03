package exam.aleeshataylor.jsonparserdemo.entity;

public class School {
    private String name;
    private String province;

    public School() {
    }

    public School(String name, String province) {
        this.name = name;
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "School：name=" + name + "，province=" + province + "\n";
    }
}
