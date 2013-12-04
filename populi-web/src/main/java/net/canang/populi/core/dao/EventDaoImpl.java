package net.canang.populi.core.dao;

import com.vividsolutions.jts.geom.Point;
import net.canang.populi.core.model.Event;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Transactional
@Repository("eventDao")
public class EventDaoImpl implements EventDao {

    private Logger log = LoggerFactory.getLogger(EventDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Event findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Event) session.get(Event.class, id);
    }

    @Override
    public List<Event> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Event i");
        return (List<Event>) query.list();
    }

    @Override
    public List<Event> findAround(Point point) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Event i");
        return (List<Event>) query.list();
    }

    @Override
    public List<Event> findWithin(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Event i where within(i.location, '" + filter + "') = true"); // TODO: set param
        return (List<Event>) query.list();
    }

    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(i) from Event i");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public void save(Event Event) {
        Session session = sessionFactory.getCurrentSession();
        session.save(Event);
    }

    @Override
    public void update(Event Event) {
        Session session = sessionFactory.getCurrentSession();
        session.update(Event);
    }

    @Override
    public void remove(Event Event) {
        // TODO:
    }
}
