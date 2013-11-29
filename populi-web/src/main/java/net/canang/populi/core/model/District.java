package net.canang.populi.core.model;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface District {

    Long getId();

    void setId(Long id);

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    State getState();

    void setState(State state);

}
