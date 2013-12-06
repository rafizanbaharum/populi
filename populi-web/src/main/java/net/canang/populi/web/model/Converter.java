package net.canang.populi.web.model;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import net.canang.populi.core.model.District;
import net.canang.populi.core.model.Event;
import net.canang.populi.core.model.Node;
import net.canang.populi.core.model.Turf;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 12/4/13
 */

@Component("converter")
public class Converter {

    public NodeModel convert(Node node, Point location) {
        return new NodeModel(
                node.getId(),
                node.getName(),
                node.getNricNo(),
                node.getPhone(),
                node.getInclinationType().ordinal(),
                location.getCoordinate().x,
                location.getCoordinate().y);
    }

    public EventModel convert(Event event, Point location) {
        EventModel model = new EventModel(
                event.getId(),
                event.getCode(),
                event.getName(),
                event.getHeadCount(),
                location.getCoordinate().x,
                location.getCoordinate().y);
        return model;
    }


    public TurfModel convert(Turf turf) {
        TurfModel turfModel = new TurfModel(turf.getId(), turf.getCode(), turf.getDescription());
        turfModel.setHeadCount(turf.getHeadCount());
        Polygon bound = turf.getBound();
        if (null != bound) {
            turfModel.setCenter(convert(bound.getCentroid().getCoordinate()));
            Coordinate[] coordinates = bound.getCoordinates();
            for (Coordinate coordinate : coordinates) {
                turfModel.addBound(new CoordinateModel(coordinate.x, coordinate.y));
            }
        }
        return turfModel;
    }

    public DistrictModel convert(District district) {
        DistrictModel districtModel = new DistrictModel(district.getId(), district.getDescription());
        districtModel.setHeadCount(district.getHeadCount());
        Polygon bound = district.getBound();
        if (null != bound) {
            districtModel.setCenter(convert(bound.getCentroid().getCoordinate()));
            Coordinate[] coordinates = bound.getCoordinates();
            for (Coordinate coordinate : coordinates) {
                districtModel.addBound(new CoordinateModel(coordinate.x, coordinate.y));
            }
        }
        return districtModel;
    }

    public List<DistrictModel> convertDistricts(List<District> districts) {
        List<DistrictModel> models = new ArrayList<DistrictModel>();
        for (District district : districts) {
            if (null != district.getBound()) {
                DistrictModel model = convert(district);
                models.add(model);
            }
        }
        return models;
    }

    public List<EventModel> convert(List<Event> events) {
        List<EventModel> models = new ArrayList<EventModel>();
        for (Event event : events) {
            if (null != event.getLocation()) {
                models.add(convert(event, event.getLocation()));
            }
        }
        return models;
    }

    public List<TurfModel> convertTurfs(List<Turf> turfs) {
        List<TurfModel> models = new ArrayList<TurfModel>();
        for (Turf turf : turfs) {
            if (null != turf.getBound()) {
                TurfModel model = convert(turf);
                models.add(model);
            }
        }
        return models;
    }

    public List<NodeModel> convertNodes(List<Node> nodes) {
        List<NodeModel> nodeModels = new ArrayList<NodeModel>();
        for (Node node : nodes) {
            Point location = node.getLocation();
            if (null != location) {
                nodeModels.add(convert(node, location));
            }
        }
        return nodeModels;
    }

    public List<EventModel> convertEvents(List<Event> events) {
        List<EventModel> eventModels = new ArrayList<EventModel>();
        for (Event event : events) {
            Point location = event.getLocation();
            if (null != location) {
                eventModels.add(convert(event, location));
            }
        }
        return eventModels;
    }

    public CoordinateModel convert(Coordinate coordinate) {
        return new CoordinateModel(coordinate.x, coordinate.y);

    }
}
