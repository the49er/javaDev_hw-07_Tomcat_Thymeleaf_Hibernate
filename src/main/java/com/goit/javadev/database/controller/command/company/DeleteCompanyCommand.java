package com.goit.javadev.database.controller.command.company;

import com.goit.javadev.database.model.company.CompanyDaoHibernate;

import org.thymeleaf.TemplateEngine;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCompanyCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {

        String id = req.getParameter("id");
        new CompanyDaoHibernate().deleteById(Long.parseLong(id));
        resp.sendRedirect("/company");
    }
}
