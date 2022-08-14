//package web.command;
//
//import link.Link;
//import link.LinkService;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//import serviceprovider.ServiceProvider;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collections;
//
//public class ListCommand implements Command {
//    @Override
//    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
//        resp.setContentType("text/html");
//
//        LinkService linkService = ServiceProvider.get(LinkService.class);
//
//        Link link = new Link();
//        link.setShortLink("f4g4F");
//        link.setLink("https://google.com");
//        linkService.save(link);
//
//        Context simpleContext = new Context(
//                req.getLocale(),
//                Collections.singletonMap("links", linkService.listAll())
//        );
//
//        engine.process("list", simpleContext, resp.getWriter());
//        resp.getWriter().close();
//    }
//}
