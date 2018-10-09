package com.sinova.timecapsule.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author hukui
 * @Date 2018/08/21 下午10:32
 */
public class LocalErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/index";
    }

    @RequestMapping("/error")
    public String handleError(){
        return "/index";
    }
}
