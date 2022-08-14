package com.goit.javadev.database.controller.command.project;

import com.goit.javadev.database.controller.command.Command;
import com.goit.javadev.database.model.entity_services.CompanyDaoService;
import com.goit.javadev.database.model.entity_services.CustomerDaoService;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Map;

public class CreateProject implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html");
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of(
                        "maxIdCustomer", new CustomerDaoService(connection).getMaxId(),
                        "maxIdCompany", new CompanyDaoService(connection).getMaxId(),
                        "date", LocalDate.now(),
                        "dateString", LocalDate.now().toString()
                )

        );

        engine.process("project/createProject", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
