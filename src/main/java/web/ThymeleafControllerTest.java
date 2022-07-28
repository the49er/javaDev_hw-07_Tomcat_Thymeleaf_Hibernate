package web;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/thymeleaf")
public class ThymeleafControllerTest extends HttpServlet {
    private TemplateEngine engine;
    //private CommandService commandService;

    @Override
    public void init() {
        //new DatabaseInitService().initDb();

        engine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);
        engine.addTemplateResolver(resolver);

        //commandService = new CommandService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Context simpleContext = new Context();
        simpleContext.setVariable("name", "Vlad");
        simpleContext.setVariable("date", LocalDateTime.now().toString());
        engine.process("test", simpleContext, resp.getWriter());
        resp.getWriter().close();

        //commandService.process(req, resp, engine);
    }
}
