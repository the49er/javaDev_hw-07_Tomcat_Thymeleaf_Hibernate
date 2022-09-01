package com.goit.javadev.database.controller.command.developer;

import com.goit.javadev.database.controller.command.Command;
import com.goit.javadev.database.model.developer.DeveloperDaoHibernate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class GetDevelopersCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {

        resp.setContentType("text/html");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("developers", new DeveloperDaoHibernate().getAllEntities())
        );

        engine.process("developer/developer.html", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
