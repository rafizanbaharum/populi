package net.canang.populi.core.model;

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
    District getDistrict();

    void setDistrict(District district);

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

}
