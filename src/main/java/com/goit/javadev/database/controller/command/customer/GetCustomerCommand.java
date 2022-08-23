package com.goit.javadev.database.controller.command.customer;

import com.goit.javadev.database.model.customer.CustomerDaoService;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

public class GetCustomerCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();

        resp.setContentType("text/html");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("customers", new CustomerDaoService(connection).getAllEntities())
        );

        engine.process("customer/customer", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
