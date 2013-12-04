package net.canang.populi.core.model;

import com.vividsolutions.jts.geom.Polygon;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface Turf {

    /**
     * @return
     */
    Long getId();

    void setId(Long id);

    /**
     * @return
     */
    String getCode();

    void setCode(String code);

    /**
     * @return
     */
    String getDescription();

    void setDescription(String description);

    /**
     * @return
     */
    Polygon getBound();

    void setBound(Polygon bound);

    /**
     * @return
     */
    Node getOwner();

    void setOwner(Node node);

    /**
     * @return
     */
    List<Volunteer> getVolunteers();

    void setVolunteers(List<Volunteer> volunteers);


    // transient
    Integer getHeadCount();

    void setHeadCount(Integer headCount);

}
