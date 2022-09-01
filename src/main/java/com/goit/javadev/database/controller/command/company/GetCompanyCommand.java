package com.goit.javadev.database.controller.command.company;

import com.goit.javadev.database.model.company.CompanyDaoHibernate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class GetCompanyCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {

        resp.setContentType("text/html");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("companies", new CompanyDaoHibernate().getAllEntities())
        );

        engine.process("company/company", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
