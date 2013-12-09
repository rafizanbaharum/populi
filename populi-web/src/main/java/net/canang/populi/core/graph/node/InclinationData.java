package net.canang.populi.core.graph.node;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

/**
 * @author rafizan.baharum
 * @since 12/9/13
 */
@NodeEntity
public class InclinationData {

    @GraphId
    Long id;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "search")
    public String name;

    public InclinationData() {
    }

    public InclinationData(String name) {
        this.name = name;
    }

}
