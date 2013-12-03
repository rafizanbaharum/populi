package net.canang.populi.core.model;

import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;

/**
 * @author rafizan.baharum
 * @since 11/29/13
 */
@Entity(name = "Volunteer")
@Table(name = "VOLUNTEER")
public class VolunteerImpl implements Volunteer {

    @Id
    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_VOLUNTEER")
    @SequenceGenerator(name = "SEQ_VOLUNTEER", sequenceName = "SEQ_VOLUNTEER", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = NodeImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "NODE_ID")
    private Node node;

    @ManyToOne(targetEntity = TurfImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TURF_ID")
    private Turf turf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Turf getTurf() {
        return turf;
    }

    public void setTurf(Turf turf) {
        this.turf = turf;
    }

}
