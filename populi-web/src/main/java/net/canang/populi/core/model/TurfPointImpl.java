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
@Entity(name = "TurfPoint")
@Table(name = "TURF_POINT")
public class TurfPointImpl implements TurfPoint, Serializable {

    @Id
    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_TURF_POINT")
    @SequenceGenerator(name = "SEQ_TURF_POINT", sequenceName = "SEQ_TURF_POINT", allocationSize = 1)
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
    @ManyToOne(targetEntity = TurfImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TURF_ID")
    private Turf turf;


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

    public Turf getTurf() {
        return turf;
    }

    public void setTurf(Turf turf) {
        this.turf = turf;
    }
}
