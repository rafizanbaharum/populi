package net.canang.populi.web.controller;

import net.canang.populi.biz.BizFinder;
import net.canang.populi.core.model.*;
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
 *
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Controller
@RequestMapping("/district")
public class DistrictController {

    public static final double RADIUS = 10.0D;
    private Logger log = LoggerFactory.getLogger(DistrictController.class);

    @Autowired
    private BizFinder finder;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(ModelMap model) {
        return "district_view";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String data(ModelMap model) {
        return "district_data";
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public
    @ResponseBody
    List<DistrictPoint> findDistrictPoints(@RequestParam Long id) {
        District district = finder.findDistrictById(id);
        List<DistrictPoint> points = finder.findDistrictPoints(district);
        log.debug("result: " + points.size());
        return points;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String addNode(@RequestParam Long id, @RequestParam String lat, @RequestParam String lng) {
        log.debug("id: " + id);
        log.debug("lat: " + lng);
        District district = finder.findDistrictById(id);
        DistrictPoint point = new DistrictPointImpl();
        point.setLatitude(Double.parseDouble(lat));
        point.setLongitude(Double.parseDouble(lng));
        finder.addDistrictPoint(district, point);
        return "success";
    }
}
