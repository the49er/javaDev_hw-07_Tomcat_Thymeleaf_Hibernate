package com.goit.javadev.database.controller.command.customer;

import com.goit.javadev.database.model.customer.CustomerDaoHibernate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CreateCustomer implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("maxId", new CustomerDaoHibernate().getMaxId())
        );

        engine.process("customer/createCustomer", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
