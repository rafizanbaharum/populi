package net.canang.populi.core.model;

/**
 * @author rafizan.baharum
 * @since 11/29/13
 */
public interface DistrictPoint {

    /**
     * @return
     */
    Long getId();

    void setId(Long id);

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
     *
     * @return
     */
    District getDistrict();

    void setDistrict(District district);
}
