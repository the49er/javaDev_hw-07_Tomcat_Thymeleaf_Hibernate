package com.goit.javadev.database.controller.command.company;

import com.goit.javadev.database.model.company.CompanyDaoHibernate;
import com.goit.javadev.database.model.company.CompanyDaoJDBC;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

public class CreateCompany implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("maxId", new CompanyDaoHibernate().getMaxId())
        );

        engine.process("company/createCompany", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
