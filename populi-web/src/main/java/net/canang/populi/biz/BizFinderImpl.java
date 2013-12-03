package net.canang.populi.biz;

import net.canang.populi.core.dao.DistrictDao;
import net.canang.populi.core.dao.NodeDao;
import net.canang.populi.core.model.District;
import net.canang.populi.core.model.DistrictPoint;
import net.canang.populi.core.model.Node;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Component("bizFinder")
public class BizFinderImpl implements BizFinder {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private DistrictDao districtDao;

    @Override
    public List<Node> findAround(Double radius, Double myLat, Double myLon) {
//        return nodeDao.findAround(Point point, Point location);
        return null;
    }

    @Override
    public List<Node> findNodesByDistrict(District district) {
        return districtDao.findNodes(district);
    }

    @Override
    public District findDistrictById(Long id) {
        return districtDao.findById(id);
    }

    @Override
    public List<District> findDistricts() {
        return districtDao.find();
    }


    @Override
    public List<DistrictPoint> findDistrictPoints(District district) {
        return districtDao.findPoints(district);

    }

    @Override
    public void addNode(District district, Node node) {
        node.setDistrict(district);
        nodeDao.save(node);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addDistrictPoint(District district, DistrictPoint point) {
        districtDao.addPoint(district, point);
    }

}
