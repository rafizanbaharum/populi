package net.canang.populi.core.dao;

import net.canang.populi.core.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Transactional
@Repository("turfDao")
public class TurfDaoImpl implements TurfDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Turf findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Turf) session.get(TurfImpl.class, id);
    }

    @Override
    public List<Turf> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Turf i");
        return (List<Turf>) query.list();
    }

    @Override
    public District findDistrictOuter(Turf turf) {
        if (null == turf.getBound()) return null;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from District i where within('" + turf.getBound().toString() + "', i.bound) = true"); // TODO: set param
        return (District) query.uniqueResult();
    }


    @Override
    public List<Node> findNodesWithin(Turf turf) {
        if (null == turf.getBound()) return Collections.EMPTY_LIST;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i where within(i.location, '" + turf.getBound().toString() + "') = true"); // TODO: set param
        return (List<Node>) query.list();
    }

    @Override
    public List<Node> findNodesNotWithin(Turf turf) {
        if (null == turf.getBound()) return Collections.EMPTY_LIST;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i where within(i.location, '" + turf.getBound().toString() + "') = false"); // TODO: set param
        return (List<Node>) query.list();
    }

    @Override
    public List<Turf> findTurfsWithin(District district) {
        if (null == district.getBound()) return Collections.EMPTY_LIST;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Turf i where contains(i.bound, '" + district.getBound().toString() + "') = true"); // TODO: set param
        return (List<Turf>) query.list();
    }

    @Override
    public List<Event> findEventsWithin(Turf turf) {
        if (null == turf.getBound()) return Collections.EMPTY_LIST;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Event i where contains(i.location, '" + turf.getBound().toString() + "') = true"); // TODO: set param
        return (List<Event>) query.list();
    }

    @Override
    public Integer countNodesWithin(Turf turf) {
        if (null == turf.getBound()) return 0;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(i) from Node i where within(i.location, '" + turf.getBound().toString() + "') = true"); // TODO: set param
        return ((Long) query.uniqueResult()).intValue();
    }


    @Override
    public void save(Turf turf) {
        Session session = sessionFactory.getCurrentSession();
        session.save(turf);
    }

    @Override
    public void update(Turf turf) {
        Session session = sessionFactory.getCurrentSession();
        session.update(turf);
    }

    @Override
    public void remove(Turf issue) {
        // TODO:
    }
}
