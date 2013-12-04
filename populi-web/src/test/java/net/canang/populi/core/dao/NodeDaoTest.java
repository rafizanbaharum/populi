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
import java.util.Random;


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
        Node.setInclinationType(InclinationType.BLUE);
        nodeDao.save(Node);

        Node = new NodeImpl();
        Node.setInclinationType(InclinationType.BLUE);
        nodeDao.save(Node);

        Node = new NodeImpl();
        nodeDao.save(Node);

        Node = new NodeImpl();
        nodeDao.save(Node);

        radius = 10000.0D;
        startLat = 1.5333D;
        startLon = 103.388D;

//        List<Node> Nodes = nodeDao.findAround(radius, startLat, startLon);
//        for (Node i : Nodes) {
//            log.debug("Node: " + i);
//        }
    }


    @Test
    public void find() {
        List<Node> nodes = nodeDao.find();
        log.debug("size: " + nodes.size());
        for (Node node : nodes) {
            log.debug("Node: " + node);
            log.debug("Node: " + node.getLocation().toString());
        }
    }


    /**
     * POLYGON((1.51396 103.63293,1.52563 103.6464,1.52992 103.66803,1.52649 103.67361,1.52168 103.67988,1.50349 103.65619,1.50109 103.6561,1.49937 103.64812,1.51388 103.63301,1.51396 103.63293))
     */
    @Test
    public void findWithin() {
        List<Node> nodes = nodeDao.findWithin("" +
                "POLYGON((" +
                "1.51396 103.63293," +
                "1.52563 103.6464," +
                "1.52992 103.66803," +
                "1.52649 103.67361," +
                "1.52168 103.67988," +
                "1.50349 103.65619," +
                "1.50109 103.6561," +
                "1.49937 103.64812," +
                "1.51388 103.63301," +
                "1.51396 103.63293" +
                "))");
        log.debug("size: " + nodes.size());
        for (Node node : nodes) {
            log.debug("Node: " + node);
            log.debug("Node: " + node.getLocation().toString());
        }
    }


    @Test
    public void updateNode() {
        String[] names = new String[]{
                "Ahmad", "Shah", "Yusof", "Salleh", "Noor", "Nasir",
                "Said", "Yasin", "Yunos", "Zin", "Isa", "Sharif", "Khalid",
                "Nizam", "Taib", "Yatim", "Yazid", "Zain", "Arif", "Fauzi", "Rashid",
                "Razali", "Esa", "Fadil", "Aris", "Saad", "Kamal",
                "Ismail", "Azmi", "Hashim", "Nazri", "Jamil", "Zaini", "Zamri",
                "Kasim", "Fuad", "Din", "Ariffin", "Najib", "Hassan", "Sani",
                "Ishak", "Nordin", "Farid", "Hatta", "Ghazali", "Jais", "Khairi",
                "Suhaimi", "Zaidi", "Zaki"};

        List<Node> nodes = nodeDao.find();
        for (Node node : nodes) {
            randomizePhone();
            node.setNricNo(randomizeNricNo());
            node.setPhone(randomizePhone());
            nodeDao.update(node);
        }

//        for (Node node : nodes) {
//            Random rand = new Random();
//            int first = rand.nextInt(names.length);
//            int last = rand.nextInt(names.length);
//            node.setName(names[first] + " " + names[last]);
//            nodeDao.update(node);
//        }
    }


    @Test
    public void findAround() {
//        List<Node> Nodes = nodeDao.findAround(100.0D, 1.5333D, 103.388D);
//        for (Node node : Nodes) {
//            log.debug("Node: " + node);
//        }
    }

    @Test
    public void saveAndFind() {
        Node Node = new NodeImpl();
        nodeDao.save(Node);
//        List<Node> Nodes = nodeDao.findAround(1D, 1.5555D, 103.3333D);
//        for (Node i : Nodes) {
//            log.debug("Node: " + i);
//        }
    }

    @Test
    public void findAndUpdate() {
        List<Node> Nodes = nodeDao.find();
        for (Node node : Nodes) {
            nodeDao.update(node);
        }
    }


    public String randomizeNricNo() {
        String[] nos = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer nric = new StringBuffer();

        Random rand = new Random();
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        return nric.toString();
    }

    public String randomizePhone() {
        String[] nos = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer phone = new StringBuffer();

        Random rand = new Random();
        phone.append("01");
        phone.append(rand.nextInt(nos.length));
        phone.append(" ");
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        return phone.toString();
    }
}
