package com.goit.javadev.database.controller.command.company;

import com.goit.javadev.database.model.company.CompanyDaoHibernate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UpdateCompany implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html");

        CompanyDaoHibernate companyDaoHibernate = new CompanyDaoHibernate();
        String id = req.getParameter("id");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of(
                        "id", id,
                        "company",companyDaoHibernate.getEntityById(Long.parseLong(id)),
                        "maxIdCompany", companyDaoHibernate.getMaxId()
                )
        );

        engine.process("company/updateCompany", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
