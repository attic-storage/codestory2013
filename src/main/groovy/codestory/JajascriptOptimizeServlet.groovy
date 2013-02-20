package codestory

import codestory.jajascript.Optimizer
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JajascriptOptimizeServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        def payload = req.reader.text
        GroovySystem.println "Jajascript payload : " + payload

        def orders = new JsonSlurper().parseText(payload)
        def optimizedPlanning = Optimizer.optimize(orders)

        def jsonb = new JsonBuilder()
        jsonb.setContent(optimizedPlanning)
        GroovySystem.println jsonb.toString()
        resp.outputStream.println jsonb.toString()

        resp.status = 200
        resp.contentType = "application/json"
    }
}
