package com.goit.javadev.database.controller.command.developer;

import com.goit.javadev.database.model.developer.DeveloperDaoHibernate;
import com.goit.javadev.database.model.developer.DeveloperDaoJDBC;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import com.goit.javadev.database.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class DeleteDevelopersCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {

        String id = req.getParameter("id");
        new DeveloperDaoHibernate().deleteById(Long.parseLong(id));
        resp.sendRedirect("/dao/developer");
    }
}
