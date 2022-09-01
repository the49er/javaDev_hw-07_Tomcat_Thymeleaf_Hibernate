package com.goit.javadev.database.controller.command.project;

import com.goit.javadev.database.controller.command.Command;
import com.goit.javadev.database.model.project.ProjectDaoHibernate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class GetProjectsCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {

        resp.setContentType("text/html");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("projects", new ProjectDaoHibernate().getAllEntities())
        );

        engine.process("project/project", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
