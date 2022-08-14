package com.goit.javadev.database.controller.command.project;

import com.goit.javadev.database.model.entity_services.CompanyDaoService;
import com.goit.javadev.database.model.entity_services.CustomerDaoService;
import com.goit.javadev.database.model.entity_services.ProjectDaoService;
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
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();

        ProjectDaoService projectDaoService = new ProjectDaoService(connection);

        String id = req.getParameter("id");
        LocalDate date = LocalDate.parse(req.getParameter("date"));

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of(
                        "id", id,
                        "project",projectDaoService.getEntityById(Long.parseLong(id)),
                        "maxIdCompany", new CompanyDaoService(connection).getMaxId(),
                        "maxIdCustomer", new CustomerDaoService(connection).getMaxId(),
                        "maxIdProject", projectDaoService.getMaxId(),
                        "date", date
                )
        );

        engine.process("project/updateProject", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
