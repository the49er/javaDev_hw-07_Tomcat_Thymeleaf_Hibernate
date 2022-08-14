package com.goit.javadev.database.controller.command.company;

import com.goit.javadev.database.model.entity_services.CompanyDaoService;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

public class UpdateCompany implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html");
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();

        CompanyDaoService companyDaoService = new CompanyDaoService(connection);

        String id = req.getParameter("id");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of(
                        "id", id,
                        "company",companyDaoService.getEntityById(Long.parseLong(id)),
                        "maxIdCompany", companyDaoService.getMaxId()
                )
        );

        engine.process("company/updateCompany", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
