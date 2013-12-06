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
public interface TurfDao {

    Turf findById(Long id);

    List<Turf> find();

    District findDistrictOuter(Turf turf);

    List<Node> findNodesWithin(Turf turf);

    List<Node> findNodesNotWithin(Turf turf);

    List<Event> findEventsWithin(Turf turf);

    List<Turf> findTurfsWithin(District district);

    Integer countNodesWithin(Turf turf);

    void save(Turf turf);

    void update(Turf turf);

    void remove(Turf turf);

}
