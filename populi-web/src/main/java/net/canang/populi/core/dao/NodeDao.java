package net.canang.populi.core.dao;


import net.canang.populi.core.model.Node;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface NodeDao {

    Node findById(Long id);

    List<Node> find();

    List<Node> findAround(Double radius, Double latitude, Double longitude);

    Integer count();

    void save(Node Node);

    void update(Node Node);

    void remove(Node Node);
}
