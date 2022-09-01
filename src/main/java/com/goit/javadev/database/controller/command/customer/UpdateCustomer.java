package com.goit.javadev.database.controller.command.customer;

import com.goit.javadev.database.model.customer.CustomerDaoHibernate;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

public class UpdateCustomer implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html");
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();

        CustomerDaoHibernate customerDaoHibernate = new CustomerDaoHibernate();

        String id = req.getParameter("id");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of(
                        "id", id,
                        "customer",customerDaoHibernate.getEntityById(Long.parseLong(id)),
                        "maxIdCustomer", customerDaoHibernate.getMaxId()
                )
        );

        engine.process("customer/updateCustomer", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
