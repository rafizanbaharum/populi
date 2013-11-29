package net.canang.populi.core.dao;

import net.canang.populi.core.model.Node;
import net.canang.populi.core.model.NodeImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;
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
    public List<Node> findAround(Double radius, Double latitude, Double longitude) {
        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        QueryBuilder builder = fullTextSession
                .getSearchFactory()
                .buildQueryBuilder().forEntity(NodeImpl.class).get();

//        org.apache.lucene.search.Query termQuery = builder.keyword().b
//                .onField(Node.STATUS)
//                .matching(status)
//                .createQuery();

        org.apache.lucene.search.Query luceneQuery = builder.spatial()
                .onCoordinates(Node.FIELD)
                .within(radius, Unit.KM)
                .ofLatitude(latitude)
                .andLongitude(longitude)
                .createQuery();

        org.apache.lucene.search.Query combine = luceneQuery;
        //.combine(new org.apache.lucene.search.Query[]{termQuery});
        log.debug("query: " + combine.toString());
        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(combine, NodeImpl.class);
        return fullTextQuery.list();
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
}
