package net.canang.populi.core.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.*;
import org.hibernate.search.bridge.builtin.EnumBridge;
import org.hibernate.search.spatial.Coordinates;
import org.hibernate.search.spatial.SpatialFieldBridge;
import org.hibernate.search.spatial.SpatialFieldBridgeByRange;
import org.hibernate.search.spatial.impl.Point;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author rafizan.baharum
 * @since 11/28/13
 */
@Indexed
@Entity(name = "Node")
@Table(name = "NODE")
public class NodeImpl implements Node, Serializable {

    @Id
    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_NODE")
    @SequenceGenerator(name = "SEQ_NODE", sequenceName = "SEQ_NODE", allocationSize = 1)
    private Long id;

    @Column(name = "LAT")
    @Latitude(of = Node.FIELD)
    @FieldBridge(impl = SpatialFieldBridge.class)
    private Double latitude;

    @Column(name = "LON")
    @Longitude(of = Node.FIELD)
    @FieldBridge(impl = SpatialFieldBridge.class)
    private Double longitude;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "INCLINATION_TYPE")
    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
    @FieldBridge(impl = EnumBridge.class)
    private InclinationType inclinationType;

    @JsonIgnore
    @ManyToOne(targetEntity = DistrictImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DISTRICT_ID")
    private District district;

    public NodeImpl() {
    }

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

    public InclinationType getInclinationType() {
        return inclinationType;
    }

    public void setInclinationType(InclinationType inclinationType) {
        this.inclinationType = inclinationType;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "NodeImpl{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Transient
    @Field
    @FieldBridge(impl = SpatialFieldBridgeByRange.class)
    public Coordinates getLocation() {
        if (latitude == null || longitude == null) {
            return null;
        }
        Coordinates point = Point.fromDegrees(latitude, longitude);
        return point;
    }
}
