package org.industry.common.base.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * controller
 */
@Controller
public class GatewayController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
