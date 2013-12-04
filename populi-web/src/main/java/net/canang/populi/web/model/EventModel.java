package net.canang.populi.web.model;

/**
 * @author rafizan.baharum
 * @since 12/4/13
 */
public class EventModel {

    private Long id;
    private String code;
    private String name;
    private Integer headCount;
    private double x;
    private double y;

    public EventModel(Long id, String code, String name, Integer headCount, double x, double y) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.headCount = headCount;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeadCount() {
        return headCount;
    }

    public void setHeadCount(Integer headCount) {
        this.headCount = headCount;
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
}
