package com.goit.javadev.database.controller.command.developer;

import com.goit.javadev.database.controller.command.Command;
import com.goit.javadev.database.model.developer.DeveloperDaoHibernate;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDevelopersCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {

        String id = req.getParameter("id");
        new DeveloperDaoHibernate().deleteById(Long.parseLong(id));
        resp.sendRedirect("/developer");
    }
}
