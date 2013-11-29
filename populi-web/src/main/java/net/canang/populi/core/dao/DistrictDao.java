package net.canang.populi.core.dao;

import net.canang.populi.core.model.District;
import net.canang.populi.core.model.DistrictPoint;
import net.canang.populi.core.model.Node;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface DistrictDao {

    District findById(Long id);

    List<District> find();

    List<DistrictPoint> findPoints(District district);

    List<Node> findNodes(District district);

    void save(District district);

    void update(District district);

    void remove(District district);

    void addPoint(District district, DistrictPoint districtPoint);

    void removePoint(District district, DistrictPoint districtPoint);

}
