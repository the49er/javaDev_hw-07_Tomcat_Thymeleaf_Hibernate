package com.goit.javadev.database.controller.command.customer;

import com.goit.javadev.database.model.customer.Customer;
import com.goit.javadev.database.model.customer.CustomerDaoHibernate;
import com.goit.javadev.database.model.customer.CustomerDaoService;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class PutCustomerCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {

        Customer customer = new Customer();

        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String businessSphere = req.getParameter("businessSphere");


        customer.setName(name);
        customer.setBusinessSphere(businessSphere);

        new CustomerDaoHibernate().updateEntityFieldsById(customer, id);

        resp.sendRedirect("/customer");
    }
}
