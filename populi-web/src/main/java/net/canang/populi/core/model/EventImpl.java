package net.canang.populi.core.model;

import com.vividsolutions.jts.geom.Point;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 11/29/13
 */
@Indexed
@Entity(name = "Event")
@Table(name = "EVENT")
public class EventImpl implements Event {

    @Id
    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_EVENT")
    @SequenceGenerator(name = "SEQ_EVENT", sequenceName = "SEQ_EVENT", allocationSize = 1)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "HEAD_COUNT")
    private Integer headCount;

    @Column(name = "DESCRIPTION")
    private String description;

    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point location;

    @JsonIgnore
    @OneToMany(targetEntity = AttendeeImpl.class, mappedBy = "event")
    private List<Attendee> attendees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeadCount() {
        return headCount;
    }

    public void setHeadCount(Integer headCount) {
        this.headCount = headCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }
}
