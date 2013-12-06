package net.canang.populi.biz;

import net.canang.populi.core.model.District;
import net.canang.populi.core.model.Event;
import net.canang.populi.core.model.Node;
import net.canang.populi.core.model.Turf;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface BizFinder {

    District findDistrictById(Long id);

    Turf findTurfById(Long id);

    List<District> findDistricts();

    List<Turf> findTurfs();

    List<Turf> findTurfsWithinDistrict(District district);

    List<Node> findNodesWithinDistrict(District district);

    List<Node> findNodesWithinTurf(Turf turf);

    List<Event> findEventsWithinDistrict(District district);

    List<Event> findEventsWithinTurf(Turf turf);

    District findDistrictOuterTurf(Turf turf);

    void addDistrict(District district);

    void addTurf(Turf turf);

    void addNode(Node Node);

    void addEvent(Event event);

}
