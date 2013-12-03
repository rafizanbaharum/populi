package net.canang.populi.core.dao;


import com.vividsolutions.jts.geom.Point;
import net.canang.populi.core.model.Node;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface NodeDao {

    Node findById(Long id);

    List<Node> find();

    List<Node> findAround(Point point);

    List<Node> findWithin(String filter);

    Integer count();

    void save(Node Node);

    void update(Node Node);

    void remove(Node Node);
}
