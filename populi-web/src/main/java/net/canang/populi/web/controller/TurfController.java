package net.canang.populi.web.controller;

import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import net.canang.populi.biz.BizFinder;
import net.canang.populi.core.model.District;
import net.canang.populi.core.model.Node;
import net.canang.populi.core.model.Turf;
import net.canang.populi.core.model.TurfImpl;
import net.canang.populi.web.Utils;
import net.canang.populi.web.model.Converter;
import net.canang.populi.web.model.TurfModel;
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
@RequestMapping("/turf")
public class TurfController {

    private Logger log = LoggerFactory.getLogger(TurfController.class);

    @Autowired
    private Converter converter;

    @Autowired
    private BizFinder finder;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.put("turfs", finder.findTurfs());
        return "turf_list";
    }

    @RequestMapping(value = "/navigate", method = RequestMethod.GET)
    public String navigate(ModelMap model) {
        return "turf_navigate";
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
    public String report(@PathVariable Long id, ModelMap model) {
        Turf turf = finder.findTurfById(id);
        List<Node> nodes = finder.findNodesWithinTurf(turf);
        model.put("turf", converter.convert(turf));
        model.put("nodes", converter.convertNodes(nodes));
        return "turf_report";
    }

    @RequestMapping(value = "/trend/{id}", method = RequestMethod.GET)
    public String trend(@PathVariable Long id, ModelMap model) {
        Turf turf = finder.findTurfById(id);
        List<Node> nodes = finder.findNodesWithinTurf(turf);
        model.put("turf", converter.convert(turf));
        model.put("nodes", converter.convertNodes(nodes));
        return "turf_trend";
    }


    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, ModelMap model) {
        Turf turf = finder.findTurfById(id);
        List<Node> nodes = finder.findNodesWithinTurf(turf);
        model.put("turf", converter.convert(turf));
        model.put("nodes", converter.convertNodes(nodes));
        return "turf_view";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String view(@RequestParam String polyStr, ModelMap model) {
        try {
            WKTReader reader = new WKTReader();
            Turf turf = new TurfImpl();
            String code = Utils.randomizeCode();
            turf.setCode(code);
            turf.setDescription("Turf " + code);
            turf.setBound((Polygon) reader.read(polyStr));
            finder.addTurf(turf);
        } catch (ParseException e) {
        }
        return "turf added!";
    }


    @RequestMapping(value = "/draw", method = RequestMethod.GET)
    public String draw(ModelMap model) {
        return "turf_draw";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String data(ModelMap model) {
        return "turf_data";
    }

    @RequestMapping(value = "/findTurf", method = RequestMethod.GET)
    public
    @ResponseBody
    TurfModel findTurfBound(@RequestParam Long id) {
        Turf turf = finder.findTurfById(id);
        return converter.convert(turf);
    }

    @RequestMapping(value = "/findTurfCenteroid", method = RequestMethod.GET)
    public
    @ResponseBody
    TurfModel findTurfCenteroid(@RequestParam Long id) {
        Turf turf = finder.findTurfById(id);
        return converter.convert(turf);
    }

    @RequestMapping(value = "/findAllTurfs", method = RequestMethod.GET)
    public
    @ResponseBody
    List<TurfModel> findAllTurfs(@RequestParam Long districtId) {
        District district = finder.findDistrictById(districtId);
        List<Turf> turfs = finder.findTurfsWithinDistrict(district);
        return converter.convertTurfs(turfs);
    }

}