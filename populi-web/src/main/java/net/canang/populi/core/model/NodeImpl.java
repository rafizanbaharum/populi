package net.canang.populi.core.model;

import com.vividsolutions.jts.geom.Point;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "NRIC_NO")
    private String nricNo;

    @Column(name = "ADDRESS")
    private String address;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point location;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "INCLINATION_TYPE")
    private InclinationType inclinationType;

    @JsonIgnore
    @OneToMany(targetEntity = NodeAttributeImpl.class, mappedBy = "node")
    private List<NodeAttribute> attributes;

    public NodeImpl() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNricNo() {
        return nricNo;
    }

    public void setNricNo(String nricNo) {
        this.nricNo = nricNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public InclinationType getInclinationType() {
        return inclinationType;
    }

    public void setInclinationType(InclinationType inclinationType) {
        this.inclinationType = inclinationType;
    }

    public List<NodeAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<NodeAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "NodeImpl{" +
                "id=" + id +
//                ", latitude=" + latitude +
//                ", longitude=" + longitude +
                '}';
    }
}
