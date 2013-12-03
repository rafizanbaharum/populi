package net.canang.populi.core.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

/**
 * @author rafizan.baharum
 * @since 11/29/13
 */
@Indexed
@Entity(name = "Company")
@Table(name = "COMPANY")
public class CompanyImpl implements Company {

    @Id
    @DocumentId
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_COMPANY")
    @SequenceGenerator(name = "SEQ_COMPANY", sequenceName = "SEQ_COMPANY", allocationSize = 1)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @JsonIgnore
    @ManyToOne(targetEntity = NodeImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private Node owner;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Node getOwner() {
        return owner;
    }

    public void setOwner(Node owner) {
        this.owner = owner;
    }
}

