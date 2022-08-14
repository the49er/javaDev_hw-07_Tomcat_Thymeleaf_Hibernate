package com.goit.javadev.database.controller.command.company;

import com.goit.javadev.database.model.entity.company.Company;
import com.goit.javadev.database.model.entity_services.CompanyDaoService;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class PostCompanyCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();
        CompanyDaoService companyDaoService = new CompanyDaoService(connection);

        Company company = new Company();

        String name = req.getParameter("name");
        String specialization = req.getParameter("specialization");

        company.setName(name);
        company.setSpecialization(specialization);

        companyDaoService.insertNewEntity(company);

        resp.sendRedirect("/dao/company");
    }
}