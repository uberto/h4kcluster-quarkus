package com.ubertob.h4kcluster

import org.http4k.servlet.HttpHandlerServlet
import javax.servlet.Servlet
import javax.servlet.annotation.WebServlet


@WebServlet("/")
open class RestServlet : Servlet by HttpHandlerServlet(
        //here should go the egress UI
//        com.ubertob.h4kcluster.countword.SumNumberHandler(SumNumbersHub())
        LocalAppsHandler()

)


