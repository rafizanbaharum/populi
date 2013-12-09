package net.canang.populi.core.dao;


import com.vividsolutions.jts.geom.Point;
import net.canang.populi.core.model.Node;
import net.canang.populi.core.model.NodeAttribute;

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

    List<NodeAttribute> findAttributes(Node node);

    Integer count();

    void save(Node Node);

    void update(Node Node);

    void remove(Node Node);

    void addAttribute(Node node, NodeAttribute attribute);

    void removeAttribute(Node node, NodeAttribute attribute);
}
