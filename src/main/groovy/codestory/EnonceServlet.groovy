package codestory

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class EnonceServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        def bodyContent = req.reader.text

        GroovySystem.println bodyContent

        resp.status = 201
        resp.setHeader("Location", "http://code-story.net")
    }
}
