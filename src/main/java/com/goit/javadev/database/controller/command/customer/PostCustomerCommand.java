package com.goit.javadev.database.controller.command.customer;

import com.goit.javadev.database.model.entity.customer.Customer;
import com.goit.javadev.database.model.entity_services.CustomerDaoService;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class PostCustomerCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();
        CustomerDaoService customerDaoService = new CustomerDaoService(connection);

        Customer customer = new Customer();

        String name = req.getParameter("name");
        String businessSphere = req.getParameter("businessSphere");

        customer.setName(name);
        customer.setBusinessSphere(businessSphere);

        customerDaoService.insertNewEntity(customer);

        resp.sendRedirect("/dao/customer");
    }
}
