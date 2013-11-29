package net.canang.populi.web.controller;

import net.canang.populi.biz.BizFinder;
import net.canang.populi.core.model.District;
import net.canang.populi.core.model.InclinationType;
import net.canang.populi.core.model.Node;
import net.canang.populi.core.model.NodeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private BizFinder finder;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String heatmap(ModelMap model) {
        return "node_view";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String data(ModelMap model) {
        return "node_data";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Node> findNodes(@RequestParam Long districtId) {
        District district = finder.findDistrictById(districtId);
        List<Node> nodes = finder.findNodesByDistrict(district);
        log.debug("result: " + nodes.size());
        return nodes;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String addNode(@RequestParam Long districtId, @RequestParam String lat, @RequestParam String lng) {
        log.debug("lat: " + lat + " lng: " + lng);
        District district = finder.findDistrictById(districtId);
        Node node = new NodeImpl();
        node.setInclinationType(InclinationType.BLUE);
        node.setLatitude(Double.parseDouble(lat));
        node.setLongitude(Double.parseDouble(lng));
        node.setDistrict(district);
        finder.saveNode(node);
        return "success";
    }
}
