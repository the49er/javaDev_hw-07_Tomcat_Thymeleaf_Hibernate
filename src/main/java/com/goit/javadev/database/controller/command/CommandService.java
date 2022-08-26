package com.goit.javadev.database.controller.command;

import com.goit.javadev.database.controller.command.company.CreateCompany;
import com.goit.javadev.database.controller.command.company.DeleteCompanyCommand;
import com.goit.javadev.database.controller.command.company.GetCompanyCommand;
import com.goit.javadev.database.controller.command.company.PostCompanyCommand;
import com.goit.javadev.database.controller.command.company.PutCompanyCommand;
import com.goit.javadev.database.controller.command.company.UpdateCompany;
import com.goit.javadev.database.controller.command.customer.CreateCustomer;
import com.goit.javadev.database.controller.command.customer.DeleteCustomerCommand;
import com.goit.javadev.database.controller.command.customer.GetCustomerCommand;
import com.goit.javadev.database.controller.command.customer.PostCustomerCommand;
import com.goit.javadev.database.controller.command.customer.PutCustomerCommand;
import com.goit.javadev.database.controller.command.customer.UpdateCustomer;
import com.goit.javadev.database.controller.command.developer.CreateDeveloper;
import com.goit.javadev.database.controller.command.developer.DeleteDevelopersCommand;
import com.goit.javadev.database.controller.command.developer.GetDevelopersCommand;
import com.goit.javadev.database.controller.command.developer.PostDevelopersCommand;
import com.goit.javadev.database.controller.command.developer.PutDevelopersCommand;
import com.goit.javadev.database.controller.command.developer.UpdateDeveloper;
import com.goit.javadev.database.controller.command.project.CreateProject;
import com.goit.javadev.database.controller.command.project.DeleteProjectCommand;
import com.goit.javadev.database.controller.command.project.PostProjectCommand;
import com.goit.javadev.database.controller.command.project.PutProjectCommand;
import com.goit.javadev.database.controller.command.project.UpdateProject;
import org.thymeleaf.TemplateEngine;
import com.goit.javadev.database.controller.command.project.GetProjectsCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandService {
    private Map<String, Command> commands;

    public CommandService() {
        commands = new HashMap<>();

        commands.put("GET /", new IndexCommand());

        commands.put("GET /developer", new GetDevelopersCommand());
        commands.put("GET /createDeveloper", new CreateDeveloper());
        commands.put("POST /developer/create", new PostDevelopersCommand());
        commands.put("POST /developer/update", new UpdateDeveloper());
        commands.put("POST /developer/put", new PutDevelopersCommand());
        commands.put("POST /developer/delete", new DeleteDevelopersCommand());

        commands.put("GET /project", new GetProjectsCommand());
        commands.put("GET /createProject", new CreateProject());
        commands.put("POST /project/create", new PostProjectCommand());
        commands.put("POST /project/update", new UpdateProject());
        commands.put("POST /project/put", new PutProjectCommand());
        commands.put("POST /project/delete", new DeleteProjectCommand());

        commands.put("GET /company", new GetCompanyCommand());
        commands.put("GET /createCompany", new CreateCompany());
        commands.put("POST /company/create", new PostCompanyCommand());
        commands.put("POST /company/update", new UpdateCompany());
        commands.put("POST /company/put", new PutCompanyCommand());
        commands.put("POST /company/delete", new DeleteCompanyCommand());

        commands.put("GET /customer", new GetCustomerCommand());
        commands.put("GET /createCustomer", new CreateCustomer());
        commands.put("POST /customer/create", new PostCustomerCommand());
        commands.put("POST /customer/update", new UpdateCustomer());
        commands.put("POST /customer/put", new PutCustomerCommand());
        commands.put("POST /customer/delete", new DeleteCustomerCommand());
    }

    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        String requestUri = req.getRequestURI();
        String commandKey = req.getMethod() + " " + requestUri;

        commands.get(commandKey).process(req, resp, engine);
    }

}
