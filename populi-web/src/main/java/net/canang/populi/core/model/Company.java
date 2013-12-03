package net.canang.populi.core.model;

/**
 * @author rafizan.baharum
 * @since 11/29/13
 */
public interface Company {

    /**
     * @return
     */
    Long getId();

    void setId(Long id);

    /**
     * @return
     */
    String getCode();

    void setCode(String code);

    /**
     * @return
     */
    String getName();

    void setName(String name);

    /**
     * @return
     */
    String getDescription();

    void setDescription(String description);


    Node getOwner();

    void setOwner(Node node);
}
