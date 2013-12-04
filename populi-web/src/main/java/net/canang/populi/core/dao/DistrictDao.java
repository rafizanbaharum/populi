package net.canang.populi.core.dao;

import net.canang.populi.core.model.District;
import net.canang.populi.core.model.Event;
import net.canang.populi.core.model.Node;
import net.canang.populi.core.model.Turf;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface DistrictDao {

    District findById(Long id);

    List<District> find();

    List<Node> findNodesWithin(District district);

    List<Node> findNodesNotWithin(District district);

    List<Turf> findTurfsWithin(District district);

    List<Event> findEventsWithin(District district);

    Integer countNodesWithin(District district);

    void save(District district);

    void update(District district);

    void remove(District district);
}
