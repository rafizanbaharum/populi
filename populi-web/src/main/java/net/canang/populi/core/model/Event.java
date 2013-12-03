package net.canang.populi.core.model;

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
    District getDistrict();

    void setDistrict(District district);

    /**
     *
     * @return
     */
    List<Attendee> getAttendees();

    void setAttendees(List<Attendee> attendees);

}

