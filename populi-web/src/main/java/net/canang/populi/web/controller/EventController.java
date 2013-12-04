package net.canang.populi.web.controller;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import net.canang.populi.biz.BizFinder;
import net.canang.populi.core.model.District;
import net.canang.populi.core.model.Event;
import net.canang.populi.core.model.EventImpl;
import net.canang.populi.core.model.Turf;
import net.canang.populi.web.Utils;
import net.canang.populi.web.model.Converter;
import net.canang.populi.web.model.EventModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Controller
@RequestMapping("/event")
public class EventController {

    public static final double RADIUS = 10.0D;
    private Logger log = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private Converter converter;

    @Autowired
    private BizFinder finder;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String heatmap(ModelMap model) {
        return "event_view";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String data(ModelMap model) {
        return "event_data";
    }

    @RequestMapping(value = "/draw", method = RequestMethod.GET)
    public String draw(ModelMap model) {
        return "event_draw";
    }

    @RequestMapping(value = "/findEventsWithinDistrict", method = RequestMethod.GET)
    public
    @ResponseBody
    List<EventModel> findEventsWithinDistrict(@RequestParam Long districtId) {
        District district = finder.findDistrictById(districtId);
        List<Event> events = finder.findEventsWithinDistrict(district);
        List<EventModel> models = new ArrayList<EventModel>();
        for (Event event : events) {
            if (null != event.getLocation()) {
                models.add(converter.convert(event, event.getLocation()));
            }
        }
        log.debug("result: " + events.size());
        return models;
    }

    @RequestMapping(value = "/findEventsWithinTurf", method = RequestMethod.GET)
    public
    @ResponseBody
    List<EventModel> findEventsWithinTurf(@RequestParam Long turfId) {
        Turf turf = finder.findTurfById(turfId);
        List<Event> events = finder.findEventsWithinTurf(turf);
        List<EventModel> models = new ArrayList<EventModel>();
        for (Event event : events) {
            Point location = event.getLocation();
            EventModel model = new EventModel(
                    event.getId(),
                    event.getCode(),
                    event.getName(),
                    event.getHeadCount(),
                    location.getCoordinate().x,
                    location.getCoordinate().y);
            models.add(model);
        }
        log.debug("result: " + events.size());
        return models;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String addEvent(@RequestParam String pointStr) {
        try {
            WKTReader reader = new WKTReader();
            Event event = new EventImpl();
            String code = Utils.randomizeCode();
            event.setCode(code);
            event.setName("Event " + code);
            event.setDescription("Event " + code);
            event.setHeadCount(Utils.randomizeCount());
            event.setLocation((Point) reader.read(pointStr));
            finder.addEvent(event);
        } catch (ParseException e) {
        }
        return "event added!";
    }
}
