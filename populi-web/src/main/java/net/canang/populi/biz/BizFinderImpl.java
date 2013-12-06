package net.canang.populi.biz;

import net.canang.populi.core.dao.DistrictDao;
import net.canang.populi.core.dao.EventDao;
import net.canang.populi.core.dao.NodeDao;
import net.canang.populi.core.dao.TurfDao;
import net.canang.populi.core.model.District;
import net.canang.populi.core.model.Event;
import net.canang.populi.core.model.Node;
import net.canang.populi.core.model.Turf;
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
    private EventDao eventDao;

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private TurfDao turfDao;

    @Override
    public District findDistrictById(Long id) {
        return decorate(districtDao.findById(id));
    }

    @Override
    public Turf findTurfById(Long id) {
        return decorate(turfDao.findById(id));
    }

    @Override
    public List<District> findDistricts() {
        return decorateDistrict(districtDao.find());
    }

    @Override
    public List<Turf> findTurfs() {
        return turfDao.find();
    }

    @Override
    public List<Turf> findTurfsWithinDistrict(District district) {
        return decorateTurfs(districtDao.findTurfsWithin(district));
    }

    @Override
    public List<Node> findNodesWithinDistrict(District district) {
        return districtDao.findNodesWithin(district);
    }


    @Override
    public List<Node> findNodesWithinTurf(Turf turf) {
        return turfDao.findNodesWithin(turf);
    }

    @Override
    public List<Event> findEventsWithinDistrict(District district) {
        return districtDao.findEventsWithin(district);
    }

    @Override
    public List<Event> findEventsWithinTurf(Turf turf) {
        return turfDao.findEventsWithin(turf);
    }

    @Override
    public District findDistrictOuterTurf(Turf turf) {
        return decorate(turfDao.findDistrictOuter(turf));
    }

    @Override
    public void addDistrict(District district) {
        districtDao.save(district);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addTurf(Turf turf) {
        turfDao.save(turf);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addNode(Node node) {
        nodeDao.save(node);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addEvent(Event event) {
        eventDao.save(event);
        sessionFactory.getCurrentSession().flush();
    }

    private List<Turf> decorateTurfs(List<Turf> turfs) {
        for (Turf turf : turfs) {
            decorate(turf);
        }
        return turfs;
    }

    private Turf decorate(Turf turf) {
        turf.setHeadCount(turfDao.countNodesWithin(turf));
        return turf;
    }

    private List<District> decorateDistrict(List<District> districts) {
        for (District district : districts) {
            decorate(district);
        }
        return districts;
    }

    private District decorate(District district) {
        district.setHeadCount(districtDao.countNodesWithin(district));
        return district;
    }
}
