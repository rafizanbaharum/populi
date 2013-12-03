package net.canang.populi.core.model;

/**
 * @author rafizan.baharum
 * @since 11/29/13
 */
public interface Attendee {

    /**
     * @return
     */
    Long getId();

    void setId(Long id);

    /**
     * @return
     */
    Event getEvent();

    void setEvent(Event event);

    /**
     * @return
     */
    Node getNode();

    void setNode(Node node);
}
