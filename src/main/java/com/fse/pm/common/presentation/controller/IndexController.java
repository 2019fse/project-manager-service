package com.fse.pm.common.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(path = {"/", "/adduser"})
    String index() { return "index.html"; }
}
