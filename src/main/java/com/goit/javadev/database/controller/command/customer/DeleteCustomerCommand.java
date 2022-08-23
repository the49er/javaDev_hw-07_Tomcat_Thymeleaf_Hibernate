package com.goit.javadev.database.controller.command.customer;

import com.goit.javadev.database.model.customer.CustomerDaoService;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class DeleteCustomerCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();

        String id = req.getParameter("id");
        new CustomerDaoService(connection).deleteById(Long.parseLong(id));
        resp.sendRedirect("/dao/customer");
    }
}
