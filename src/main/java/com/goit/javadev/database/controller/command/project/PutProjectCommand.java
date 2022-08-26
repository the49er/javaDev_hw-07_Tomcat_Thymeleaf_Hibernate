package com.goit.javadev.database.controller.command.project;

import com.goit.javadev.database.model.project.Project;
import com.goit.javadev.database.model.project.ProjectDaoHibernate;
import com.goit.javadev.database.model.project.ProjectDaoJDBC;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

public class PutProjectCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {

        Project project = new Project();

        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        int customerId = Integer.parseInt(req.getParameter("customerId"));
        int companyId = Integer.parseInt(req.getParameter("companyId"));

        project.setName(name);
        project.setDescription(description);
        project.setDate(date);
        project.setCustomerId(customerId);
        project.setCompanyId(companyId);

        new ProjectDaoHibernate().updateEntityFieldsById(project, id);

        resp.sendRedirect("/project");
    }
}
