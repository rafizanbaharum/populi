package net.canang.populi.core.model;

import com.vividsolutions.jts.geom.Point;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 11/29/13
 */
public interface Event {

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
    String getName();

    void setName(String name);

    /**
     * @return
     */
    String getDescription();

    void setDescription(String description);

    /**
     * @return
     */
    Integer getHeadCount();

    void setHeadCount(Integer headCount);

    /**
     * @return
     */
    Point getLocation();

    void setLocation(Point point);

    /**
     * @return
     */
    List<Attendee> getAttendees();

    void setAttendees(List<Attendee> attendees);

}

