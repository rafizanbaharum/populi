package net.canang.populi.core.model;

import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;

/**
 * @author rafizan.baharum
 * @since 11/29/13
 */
@Entity(name = "Attendee")
@Table(name = "ATTENDEE")
public class AttendeeImpl implements Attendee {

    @Id
    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_ATTENDEE")
    @SequenceGenerator(name = "SEQ_ATTENDEE", sequenceName = "SEQ_ATTENDEE", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = EventImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @ManyToOne(targetEntity = NodeImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "NODE_ID")
    private Node node;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
