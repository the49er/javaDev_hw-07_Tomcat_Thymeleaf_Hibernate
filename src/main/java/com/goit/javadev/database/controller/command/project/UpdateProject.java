package com.goit.javadev.database.controller.command.project;

import com.goit.javadev.database.model.company.CompanyDaoHibernate;
import com.goit.javadev.database.model.company.CompanyDaoJDBC;
import com.goit.javadev.database.model.customer.CustomerDaoHibernate;
import com.goit.javadev.database.model.customer.CustomerDaoService;
import com.goit.javadev.database.model.project.ProjectDaoHibernate;
import com.goit.javadev.database.model.project.ProjectDaoJDBC;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Map;

public class UpdateProject implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html");

        ProjectDaoHibernate projectDaoHibernate = new ProjectDaoHibernate();

        String id = req.getParameter("id");
        LocalDate date = LocalDate.parse(req.getParameter("date"));

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of(
                        "id", id,
                        "project", projectDaoHibernate.getEntityById(Long.parseLong(id)),
                        "maxIdCompany", new CompanyDaoHibernate().getMaxId(),
                        "maxIdCustomer", new CustomerDaoHibernate().getMaxId(),
                        "maxIdProject", projectDaoHibernate.getMaxId(),
                        "date", date
                )
        );

        engine.process("project/updateProject", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
