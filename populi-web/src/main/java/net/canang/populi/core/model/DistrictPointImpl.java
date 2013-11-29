package net.canang.populi.core.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Latitude;
import org.hibernate.search.spatial.SpatialFieldBridge;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author rafizan.baharum
 * @since 11/29/13
 */
@Entity(name = "DistrictPoint")
@Table(name = "DISTRICT_POINT")
public class DistrictPointImpl implements DistrictPoint, Serializable {

    @Id
    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_DISTRICT_POINT")
    @SequenceGenerator(name = "SEQ_DISTRICT_POINT", sequenceName = "SEQ_DISTRICT_POINT", allocationSize = 1)
    private Long id;

    @Column(name = "LAT")
    @Latitude(of = Node.FIELD)
    @FieldBridge(impl = SpatialFieldBridge.class)
    private Double latitude;

    @Column(name = "LON")
    @Latitude(of = Node.FIELD)
    @FieldBridge(impl = SpatialFieldBridge.class)
    private Double longitude;

    @JsonIgnore
    @ManyToOne(targetEntity = DistrictImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DISTRICT_ID")
    private District district;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
