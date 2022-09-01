package com.goit.javadev.database.controller.command.developer;

import com.goit.javadev.database.model.company.CompanyDaoHibernate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CreateDeveloper implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("maxId", new CompanyDaoHibernate().getMaxId())
        );

        engine.process("developer/createDeveloper", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
