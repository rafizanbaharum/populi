package net.canang.populi.web.controller;

import net.canang.populi.biz.BizFinder;
import net.canang.populi.core.model.District;
import net.canang.populi.core.model.Event;
import net.canang.populi.core.model.Node;
import net.canang.populi.web.model.Converter;
import net.canang.populi.web.model.DistrictModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Controller
@RequestMapping("/district")
public class DistrictController {

    private Logger log = LoggerFactory.getLogger(DistrictController.class);

    @Autowired
    private Converter converter;

    @Autowired
    private BizFinder finder;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.put("districts", converter.convertDistricts(finder.findDistricts()));
        return "district_list";
    }

    @RequestMapping(value = "/navigate/{id}", method = RequestMethod.GET)
    public String navigate(@PathVariable Long id, ModelMap model) {
        District district = finder.findDistrictById(id);
        model.put("district", converter.convert(district));
        model.put("turfs", converter.convertTurfs(finder.findTurfsWithinDistrict(district)));
        return "district_navigate";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        District district = finder.findDistrictById(id);
        List<Node> nodes = finder.findNodesWithinDistrict(district);
        List<Event> events = finder.findEventsWithinDistrict(district);
        model.put("district", converter.convert(district));
        model.put("nodes", converter.convertNodes(nodes));
        model.put("events", converter.convertEvents(events));
        return "district_view";
    }


    @RequestMapping(value = "/draw", method = RequestMethod.GET)
    public String draw(ModelMap model) {
        return "district_draw";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String data(ModelMap model) {
        return "district_data";
    }

    @RequestMapping(value = "/findDistrict", method = RequestMethod.GET)
    public
    @ResponseBody
    DistrictModel findDistrictBound(@RequestParam Long id) {
        District district = finder.findDistrictById(id);
        return converter.convert(district);
    }

    @RequestMapping(value = "/findAllDistricts", method = RequestMethod.GET)
    public
    @ResponseBody
    List<DistrictModel> findAllDistricts() {
        List<District> districts = finder.findDistricts();
        return converter.convertDistricts(districts);
    }
}
