package net.canang.populi.biz;

import net.canang.populi.core.model.District;
import net.canang.populi.core.model.DistrictPoint;
import net.canang.populi.core.model.Node;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface BizFinder {

    District findDistrictById(Long id);

    List<District> findDistricts();

    List<DistrictPoint> findDistrictPoints(District district);

    List<Node> findAround(Double radius, Double myLat, Double myLon);

    List<Node> findNodesByDistrict(District district);

    void addNode(District district, Node Node);

    void addDistrictPoint(District district, DistrictPoint point);

}
