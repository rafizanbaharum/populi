package net.canang.populi.core.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/28/13
 */
@Indexed
@Entity(name = "Turf")
@Table(name = "TURF")
public class TurfImpl implements Turf, Serializable {

    @Id
    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_TURF")
    @SequenceGenerator(name = "SEQ_TURF", sequenceName = "SEQ_TURF", allocationSize = 1)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(targetEntity = DistrictImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DISTRICT_ID")
    private District district;

    @ManyToOne(targetEntity = NodeImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "NODE_ID")
    private Node owner;

    @JsonIgnore
    @OneToMany(targetEntity = VolunteerImpl.class, mappedBy = "turf")
    private List<Volunteer> volunteers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Node getOwner() {
        return owner;
    }

    public void setOwner(Node owner) {
        this.owner = owner;
    }

    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }
}
