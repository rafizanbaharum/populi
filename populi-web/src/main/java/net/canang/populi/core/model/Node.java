package net.canang.populi.core.model;

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
     *
     * @return
     */
    District getDistrict();

    void setDistrict(District district);

}
