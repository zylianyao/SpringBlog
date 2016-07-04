package com.coderknock.server.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * 拿客
 * www.coderknock.com
 * QQ群：213732117
 * 创建时间：2016年07月04日
 * 描述：
 */
@Controller
class HelloWorld {
    /**
     * 1、用@RequestMapping来映射请求的URL
     * 2、返回值会通过视图解析器解析为实际的物理视图，对于org.springframework.web.servlet.view.
     * InternalResourceViewResolver视图解析器，会做如下解析：
     * prefix【在springmvc.xml中配置的prefix值】+下面return返回的值+suffix【在springmvc.xml中配置的suffix值】
     * @return
     */
    @RequestMapping("/helloworld")
    fun hello(): String {
        return "hello"
    }


    /**
     * 1、用@RequestMapping来映射请求的URL
     * 2、返回值会通过视图解析器解析为实际的物理视图，对于org.springframework.web.servlet.view.
     * InternalResourceViewResolver视图解析器，会做如下解析：
     * prefix【在springmvc.xml中配置的prefix值】+下面return返回的值+suffix【在springmvc.xml中配置的suffix值】
     * @return
     */
    @RequestMapping("/")
    fun index(): String {
        return "index"
    }

    @RequestMapping("abc")
    fun abc(): String {
        return "abc"
    }
}