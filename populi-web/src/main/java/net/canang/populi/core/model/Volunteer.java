package net.canang.populi.core.model;

/**
 * @author rafizan.baharum
 * @since 11/29/13
 */
public interface Volunteer {

    /**
     * @return
     */
    Node getNode();

    void setNode(Node node);

    /**
     * @return
     */
    Turf getTurf();

    void setTurf(Turf turf);
}
