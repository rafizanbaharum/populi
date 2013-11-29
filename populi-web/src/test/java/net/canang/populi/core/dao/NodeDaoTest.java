package net.canang.populi.core.dao;

import net.canang.populi.core.model.InclinationType;
import net.canang.populi.core.model.Node;
import net.canang.populi.core.model.NodeImpl;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PopuliConfiguration.class})
public class NodeDaoTest {

    private Logger log = LoggerFactory.getLogger(NodeDaoTest.class);

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private SessionFactory sessionFactory;
    private double radius;
    private double startLat;
    private double startLon;

    @Test
    public void createNode() {
        Node Node = new NodeImpl();
        Node.setLatitude(1.5333D);
        Node.setLongitude(103.6667D);
        Node.setInclinationType(InclinationType.BLUE);
        nodeDao.save(Node);

        Node = new NodeImpl();
        Node.setLatitude(1.5343D);
        Node.setLongitude(103.888D);
        Node.setInclinationType(InclinationType.BLUE);
        nodeDao.save(Node);

        Node = new NodeImpl();
        Node.setLatitude(1.5243D);
        Node.setLongitude(103.388D);
        nodeDao.save(Node);

        Node = new NodeImpl();
        Node.setLatitude(2.5243D);
        Node.setLongitude(102.388D);
        nodeDao.save(Node);

        radius = 10000.0D;
        startLat = 1.5333D;
        startLon = 103.388D;

        List<Node> Nodes = nodeDao.findAround(radius, startLat, startLon);
        for (Node i : Nodes) {
            log.debug("Node: " + i);
        }
    }


    @Test
    public void find() {
        List<Node> Nodes = nodeDao.find();
        for (Node Node : Nodes) {
            log.debug("Node: " + Node);
        }
    }

    @Test
    public void findAround() {
        List<Node> Nodes = nodeDao.findAround(100.0D, 1.5333D, 103.388D);
        for (Node node : Nodes) {
            log.debug("Node: " + node);
        }
    }

    @Test
    public void saveAndFind() {
        Node Node = new NodeImpl();
        Node.setLatitude(1.5555D);
        Node.setLongitude(103.3333D);
        nodeDao.save(Node);
        List<Node> Nodes = nodeDao.findAround(1D, 1.5555D, 103.3333D);
        for (Node i : Nodes) {
            log.debug("Node: " + i);
        }
    }

    @Test
    public void findAndUpdate() {
        List<Node> Nodes = nodeDao.find();
        for (Node node : Nodes) {
            nodeDao.update(node);
        }
    }
}
