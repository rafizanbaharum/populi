package net.canang.populi.core.dao;


import com.vividsolutions.jts.geom.Point;
import net.canang.populi.core.model.Event;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface EventDao {

    Event findById(Long id);

    List<Event> find();

    List<Event> findAround(Point point);

    List<Event> findWithin(String filter);

    Integer count();

    void save(Event Event);

    void update(Event Event);

    void remove(Event Event);
}
