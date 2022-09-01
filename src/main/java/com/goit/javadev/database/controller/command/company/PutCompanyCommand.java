package com.goit.javadev.database.controller.command.company;

import com.goit.javadev.database.model.company.Company;
import com.goit.javadev.database.model.company.CompanyDaoHibernate;
import org.thymeleaf.TemplateEngine;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PutCompanyCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {

        Company company = new Company();

        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String specialization = req.getParameter("specialization");


        company.setName(name);
        company.setSpecialization(specialization);

        new CompanyDaoHibernate().updateEntityFieldsById(company, id);

        resp.sendRedirect("/company");
    }
}
