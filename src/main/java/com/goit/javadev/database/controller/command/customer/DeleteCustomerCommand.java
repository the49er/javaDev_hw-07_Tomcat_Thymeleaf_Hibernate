package com.goit.javadev.database.controller.command.customer;

import com.goit.javadev.database.model.customer.CustomerDaoHibernate;
import org.thymeleaf.TemplateEngine;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCustomerCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {

        String id = req.getParameter("id");
        new CustomerDaoHibernate().deleteById(Long.parseLong(id));
        resp.sendRedirect("/customer");
    }
}
