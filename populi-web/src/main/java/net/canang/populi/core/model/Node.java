package net.canang.populi.core.model;


import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/28/13
 */
public interface Node {

    public static String FIELD = "location";
    public static String STATUS = "status";

    /**
     * @return
     */
    Long getId();

    void setId(Long id);

    /**
     * @return
     */
    String getName();

    void setName(String name);


    /**
     * @return
     */
    String getNricNo();

    void setNricNo(String nricNo);

    /**
     * @return
     */
    String getAddress();

    void setAddress(String address);

    /**
     * @return
     */
    Point getLocation();

    void setLocation(Point point);

    /**
     * @return
     */
    Double getLatitude();

    void setLatitude(Double latitude);

    /**
     * @return
     */
    Double getLongitude();

    void setLongitude(Double longitude);

    /**
     * @return
     */
    InclinationType getInclinationType();

    void setInclinationType(InclinationType inclinationType);

    /**
     * @return
     */
    District getDistrict();

    void setDistrict(District district);

    /**
     * @return
     */
    List<NodeAttribute> getAttributes();

    void setAttributes(List<NodeAttribute> nodes);

}
