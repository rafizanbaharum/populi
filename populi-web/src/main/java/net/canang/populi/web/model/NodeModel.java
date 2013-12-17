package net.canang.populi.web.model;

/**
 * @author rafizan.baharum
 * @since 12/4/13
 */
public class NodeModel {

    private Long id;
    private String name;
    private String nricNo;
    private int inclinationType;
    private String inclinationTypeString;
    private String phone;
    private double x;
    private double y;

    public NodeModel(Long id, String name, String nricNo, String phone, int inclinationType, double x, double y) {
        this.id = id;
        this.name = name;
        this.nricNo = nricNo;
        this.phone = phone;
        this.inclinationType = inclinationType;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getInclinationType() {
        return inclinationType;
    }

    public void setInclinationType(int inclinationType) {
        this.inclinationType = inclinationType;
    }

    public String getNricNo() {
        return nricNo;
    }

    public void setNricNo(String nricNo) {
        this.nricNo = nricNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInclinationTypeString() {
        return inclinationTypeString;
    }

    public void setInclinationTypeString(String inclinationTypeString) {
        this.inclinationTypeString = inclinationTypeString;
    }
}
