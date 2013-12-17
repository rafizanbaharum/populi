package net.canang.populi.web.controller;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import net.canang.populi.biz.BizFinder;
import net.canang.populi.core.model.*;
import net.canang.populi.web.Utils;
import net.canang.populi.web.model.Converter;
import net.canang.populi.web.model.NodeModel;
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
 * /nearme_json/Nodes?lat=1.5333&lon=103.388
 *
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Controller
@RequestMapping("/node")
public class NodeController {

    public static final double RADIUS = 10.0D;
    private Logger log = LoggerFactory.getLogger(NodeController.class);

    @Autowired
    private Converter converter;

    @Autowired
    private BizFinder finder;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String heatmap(ModelMap model) {
        return "node_view";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String data(ModelMap model) {
        return "node_data";
    }

    @RequestMapping(value = "/draw", method = RequestMethod.GET)
    public String draw(ModelMap model) {
        return "node_draw";
    }


    @RequestMapping(value = "/findNodesWithinDistrict", method = RequestMethod.GET)
    public
    @ResponseBody
    List<NodeModel> findNodesWithinDistrict(@RequestParam Long districtId) {
        District district = finder.findDistrictById(districtId);
        List<Node> nodes = finder.findNodesWithinDistrict(district);
        List<NodeModel> models = new ArrayList<NodeModel>();
        for (Node node : nodes) {
            models.add(converter.convert(node, node.getLocation()));
        }
        log.debug("result: " + nodes.size());
        return models;
    }

    @RequestMapping(value = "/findNodesWithinTurf", method = RequestMethod.GET)
    public
    @ResponseBody
    List<NodeModel> findNodesWithinTurf(@RequestParam Long turfId) {
        Turf turf = finder.findTurfById(turfId);
        List<Node> nodes = finder.findNodesWithinTurf(turf);
        List<NodeModel> models = new ArrayList<NodeModel>();
        for (Node node : nodes) {
            if (null != node.getLocation()) {
                models.add(converter.convert(node, node.getLocation()));
            }
        }
        log.debug("result: " + nodes.size());
        return models;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String addNode(@RequestParam String pointStr) {
        try {
            WKTReader reader = new WKTReader();
            Node node = new NodeImpl();
            node.setName(Utils.randomizeName());
            node.setNricNo(Utils.randomizeNricNo());
            node.setPhone(Utils.randomizePhone());
            node.setInclinationType(InclinationType.BLUE);
            node.setLocation((Point) reader.read(pointStr));
            finder.addNode(node);
        } catch (ParseException e) {
        }
        return "success";
    }
}
