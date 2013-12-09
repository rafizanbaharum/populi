package net.canang.populi.core.dao;

import net.canang.populi.core.graph.dao.HobbyDataRepository;
import net.canang.populi.core.graph.dao.InclinationDataRepository;
import net.canang.populi.core.graph.dao.NodeDataRepository;
import net.canang.populi.core.graph.dao.OccupationDataRepository;
import net.canang.populi.core.graph.node.HobbyData;
import net.canang.populi.core.graph.node.InclinationData;
import net.canang.populi.core.graph.node.NodeData;
import net.canang.populi.core.graph.node.OccupationData;
import net.canang.populi.core.model.Node;
import net.canang.populi.core.model.NodeAttribute;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
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
public class NodeDataDaoTest {

    private Logger log = LoggerFactory.getLogger(NodeDataDaoTest.class);

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private NodeDataRepository nodeDataRepository;

    @Autowired
    private HobbyDataRepository hobbyDataRepository;

    @Autowired
    private OccupationDataRepository occupationDataRepository;

    @Autowired
    private InclinationDataRepository inclinationDataRepository;

    @Autowired
    private GraphDatabaseService graphDatabase;


    @Test
    public void createHobby() {
        Transaction tx = graphDatabase.beginTx();
        try {
            for (String hobby : HOBBIES) {
                HobbyData data = new HobbyData(hobby);
                hobbyDataRepository.save(data);
            }
            for (String hobby : OCCUPATIONS) {
                OccupationData data = new OccupationData(hobby);
                occupationDataRepository.save(data);
            }
            for (String inclination : INCLINATIONS) {
                InclinationData data = new InclinationData(inclination);
                inclinationDataRepository.save(data);
            }
            tx.success();
        } finally {
            tx.finish();
        }
    }


    public static String[] HOBBIES = new String[]{
            "Backpacking",
            "BASE jumping",
            "Basketball",
            "Beekeeping",
            "Bird watching",
            "Board sports",
            "Bonsai",
            "Bungee jumping",
            "Camping",
            "Canoeing",
            "Cosplay",
            "Cycling",
            "Driving",
            "Foraging",
            "Gardening",
            "Geocaching",
            "Ghost Hunting",
            "Graffiti",
            "Hiking",
            "Hooping",
            "Hunting",
            "Inline Skating",
            "Jogging",
            "Kayaking",
            "Kiteboarding",
            "Kiteflying",
            "LARPing",
            "Machining",
            "Metal detecting",
            "Motor sports",
            "Mountain biking",
            "Mushroom Hunting or Mycology",
            "Nordic skating",
            "Parkour",
            "Photography",
            "Rock climbing",
            "Roller skating",
            "Rugby",
            "Running",
            "Sailing",
            "Sand castle building",
            "Sculling or Rowing",
            "Skating",
            "Skiing",
            "Skydiving",
            "Surfing",
            "Swimming",
            "Tai Chi",
            "Urban exploration",
            "Vehicle restoration",
            "Water sports"
    };

    public static String[] OCCUPATIONS = new String[]{
            "Actuary",
            "Acupuncturist",
            "Advertising and Public Relations",
            "Advertising Manager",
            "Advertising Specialist",
            "Aeronautical Engineer",
            "Aeroplane Pilot",
            "Agricultural Consultant",
            "Agricultural Engineer",
            "Agricultural Scientist",
            "Agricultural Technician",
            "Air Traffic Controller",
            "Air Transport Professionals NEC",
            "Ambulance Officer",
            "Amusement Centre Manager",
            "Anaesthetist",
            "Analyst Programmer",
            "Anatomist or Physiologist",
            "Animal Attendants and Trainers nec",
            "Antique Dealer",
            "Apiarist",
            "Apparel Cutter",
            "Arborist",
            "Archaeologist",
            "Architect",
            "Architectural Draftsperson",
            "Archivist",
            "Art Administrator or Manager",
            "Art Director (Film, Television or Stage)",
            "Art Teacher (Private Tuition)",
            "Artistic Director",
            "Auctioneer",
            "Audiologist",
            "Authors",
            "Automotive Electrician"
    };


    public static String[] INCLINATIONS = new String[]{
            "BLUE",
            "GREEN",
            "RED"
    };


    @Test
    public void nodeData() {
        List<Node> nodes = nodeDao.find();
        Transaction tx = graphDatabase.beginTx();
        try {
            for (Node node : nodes) {
                NodeData data = new NodeData(node.getName());
                nodeDataRepository.save(data);

                List<NodeAttribute> attributes = nodeDao.findAttributes(node);
                for (NodeAttribute attribute : attributes) {
                    switch (attribute.getKey()) {
                        case INCLINATION:
                            InclinationData inclination = inclinationDataRepository.findByPropertyValue("name", attribute.getValue());
                            data.inclines(inclination);
                            break;
                        case EARNING:
                            break;
                        case HOBBY:
                            HobbyData hobby = hobbyDataRepository.findByPropertyValue("name", attribute.getValue());
                            data.likes(hobby);
                            break;
                        case ISSUE:
                            break;
                        case LIKE:
                            break;
                        case ISA:
                            break;
                        case WORKS_AS:
                            OccupationData occupation = occupationDataRepository.findByPropertyValue("name", attribute.getValue());
                            data.worksAs(occupation);
                            break;
                        case PARENT_TO:
                            break;
                        case MOTHERED:
                            break;
                        case KNOW:
                            break;
                    }
                }
                nodeDataRepository.save(data);
            }
            tx.success();
        } finally {
            tx.finish();
        }
    }

    @Test
    public void nodeDataUpdate() {
        List<Node> nodes = nodeDao.find();
        Transaction tx = graphDatabase.beginTx();
        try {
            for (Node node : nodes) {
                NodeData data = nodeDataRepository.findByPropertyValue("name", node.getName());

                List<NodeAttribute> attributes = nodeDao.findAttributes(node);
                for (NodeAttribute attribute : attributes) {
                    switch (attribute.getKey()) {
                        case INCLINATION:
                            InclinationData inclination = inclinationDataRepository.findByPropertyValue("name", attribute.getValue());
                            data.inclines(inclination);
                            break;
                        case EARNING:
                            break;
                        case HOBBY:
                            break;
                        case ISSUE:
                            break;
                        case LIKE:
                            break;
                        case ISA:
                            break;
                        case WORKS_AS:
                            break;
                        case PARENT_TO:
                            break;
                        case MOTHERED:
                            break;
                        case KNOW:
                            break;
                    }
                }
                nodeDataRepository.save(data);
            }
            tx.success();
        } finally {
            tx.finish();
        }
    }

}
