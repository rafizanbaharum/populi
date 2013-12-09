package net.canang.populi.core.graph.node;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

import java.util.HashSet;
import java.util.Set;

/**
 * @author rafizan.baharum
 * @since 12/9/13
 */
@NodeEntity
public class NodeData {

    @GraphId
    Long id;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "search")
    private String name;

    @RelatedTo(type = "FRIENDS_OF", direction = Direction.BOTH, elementClass = NodeData.class)
    private Set<NodeData> friends = new HashSet<NodeData>();

    @RelatedTo(type = "LIKES_TO", direction = Direction.OUTGOING, elementClass = HobbyData.class)
    private Set<HobbyData> hobbies = new HashSet<HobbyData>();

    @RelatedTo(type = "WORKS_AS", direction = Direction.OUTGOING, elementClass = OccupationData.class)
    private Set<OccupationData> occupations = new HashSet<OccupationData>();

    @RelatedTo(type = "INCLINES_TO", direction = Direction.OUTGOING, elementClass = InclinationData.class)
    private Set<InclinationData> inclinations = new HashSet<InclinationData>();

    public NodeData() {
    }

    public NodeData(String name) {
        this.name = name;
    }

    public void knows(NodeData friend) {
        friends.add(friend);
    }

    public void worksAs(OccupationData occupation) {
        occupations.add(occupation);
    }

    public void likes(HobbyData hobby) {
        hobbies.add(hobby);
    }

    public void inclines(InclinationData inclination) {
        inclinations.add(inclination);
    }
}
