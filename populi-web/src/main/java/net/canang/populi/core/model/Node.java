package net.canang.populi.core.model;


import com.vividsolutions.jts.geom.Point;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/28/13
 */
public interface Node {

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
    String getPhone();

    void setPhone(String phone);

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
    InclinationType getInclinationType();

    void setInclinationType(InclinationType inclinationType);

    /**
     * @return
     */
    List<NodeAttribute> getAttributes();

    void setAttributes(List<NodeAttribute> nodes);

}
