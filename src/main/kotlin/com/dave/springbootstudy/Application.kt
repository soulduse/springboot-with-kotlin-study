package com.dave.springbootstudy

import org.apache.catalina.startup.Tomcat
import org.springframework.boot.runApplication
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Application {

}

fun main(args: Array<String>) {
	runApplication<SpringbootstudyApplication>(*args)
	val tomcat = Tomcat().apply {
		setPort(8080)
	}

	val context = tomcat.addContext("/", "/")

	val servlet = object: HttpServlet() {
		override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse) {
			val wrtier = resp.writer
			with(wrtier) {
				println("<html><head><title>")
				println("Hey, Tomcat")
				println("</title></head>")
				println("<body><h1>Hello Tomcat</h1></body>")
				println("</html>")
			}
		}
	}

	val servletName = "HelloServlet"
	tomcat.addServlet("/", servletName, servlet)
	context.addServletMappingDecoded("/hello", servletName)
	tomcat.start()
	tomcat.server.await()
}
