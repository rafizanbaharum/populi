package net.canang.populi.core.dao;

import net.canang.populi.core.model.District;
import net.canang.populi.core.model.DistrictImpl;
import net.canang.populi.core.model.DistrictPoint;
import net.canang.populi.core.model.Node;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Transactional
@Repository("districtDao")
public class DistrictDaoImpl implements DistrictDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public District findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (District) session.get(DistrictImpl.class, id);
    }

    @Override
    public List<District> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from District i");
        return (List<District>) query.list();
    }

    @Override
    public List<DistrictPoint> findPoints(District district) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from DistrictPoint i where i.district = :district");
        query.setEntity("district", district);
        return (List<DistrictPoint>) query.list();
    }

    @Override
    public List<Node> findNodes(District district) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i where i.district = :district");
        query.setEntity("district", district);
        return (List<Node>) query.list();
    }

    @Override
    public void save(District issue) {
        Session session = sessionFactory.getCurrentSession();
        session.save(issue);
    }

    @Override
    public void update(District issue) {
        // TODO:

    }

    @Override
    public void remove(District issue) {
        // TODO:
    }

    @Override
    public void addPoint(District district, DistrictPoint districtPoint) {
        districtPoint.setDistrict(district);
        sessionFactory.getCurrentSession().save(districtPoint);
    }

    @Override
    public void removePoint(District district, DistrictPoint districtPoint) {
        sessionFactory.getCurrentSession().delete(districtPoint);
    }
}
