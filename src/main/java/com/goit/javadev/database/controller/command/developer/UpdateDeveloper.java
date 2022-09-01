package com.goit.javadev.database.controller.command.developer;

import com.goit.javadev.database.controller.command.Command;
import com.goit.javadev.database.model.company.CompanyDaoHibernate;
import com.goit.javadev.database.model.developer.DeveloperDaoHibernate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UpdateDeveloper implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html");

        DeveloperDaoHibernate developerDaoHibernate = new DeveloperDaoHibernate();

        String id = req.getParameter("id");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of(
                        "id", id,
                        "developer",developerDaoHibernate.getEntityById(Long.parseLong(id)),
                        "maxIdCompany", new CompanyDaoHibernate().getMaxId(),
                        "maxIdDeveloper", developerDaoHibernate.getMaxId()
                )
        );

        engine.process("developer/updateDeveloper", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
