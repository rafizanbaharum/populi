package net.canang.populi.core.dao;

import com.vividsolutions.jts.geom.Point;
import net.canang.populi.core.model.Node;
import net.canang.populi.core.model.NodeAttribute;
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
@Repository("NodeDao")
public class NodeDaoImpl implements NodeDao {

    private Logger log = LoggerFactory.getLogger(NodeDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Node findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Node) session.get(Node.class, id);
    }

    @Override
    public List<Node> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i");
        return (List<Node>) query.list();
    }

    @Override
    public List<Node> findAround(Point point) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i");
        return (List<Node>) query.list();
    }

    @Override
    public List<Node> findWithin(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Node i where within(i.location, '" + filter + "') = true"); // TODO: set param
        return (List<Node>) query.list();
    }

    @Override
    public List<NodeAttribute> findAttributes(Node node) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from NodeAttribute i where i.node = :node");
        query.setEntity("node", node);
        return (List<NodeAttribute>) query.list();
    }


    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(i) from Node i");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public void save(Node Node) {
        Session session = sessionFactory.getCurrentSession();
        session.save(Node);
    }

    @Override
    public void update(Node Node) {
        Session session = sessionFactory.getCurrentSession();
        session.update(Node);
    }

    @Override
    public void remove(Node Node) {
        // TODO:

    }

    @Override
    public void addAttribute(Node node, NodeAttribute attribute) {
        attribute.setNode(node);
        sessionFactory.getCurrentSession().save(attribute);
    }

    @Override
    public void removeAttribute(Node node, NodeAttribute attribute) {
        // TODO:

    }
}
