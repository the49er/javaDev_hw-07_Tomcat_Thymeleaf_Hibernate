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

        commands.put("GET /dao/index", new IndexCommand());

        commands.put("GET /dao/developer", new GetDevelopersCommand());
        commands.put("GET /dao/createDeveloper", new CreateDeveloper());
        commands.put("POST /dao/developer/create", new PostDevelopersCommand());
        commands.put("POST /dao/developer/update", new UpdateDeveloper());
        commands.put("POST /dao/developer/put", new PutDevelopersCommand());
        commands.put("POST /dao/developer/delete", new DeleteDevelopersCommand());

        commands.put("GET /dao/project", new GetProjectsCommand());
        commands.put("GET /dao/createProject", new CreateProject());
        commands.put("POST /dao/project/create", new PostProjectCommand());
        commands.put("POST /dao/project/update", new UpdateProject());
        commands.put("POST /dao/project/put", new PutProjectCommand());
        commands.put("POST /dao/project/delete", new DeleteProjectCommand());

        commands.put("GET /dao/company", new GetCompanyCommand());
        commands.put("GET /dao/createCompany", new CreateCompany());
        commands.put("POST /dao/company/create", new PostCompanyCommand());
        commands.put("POST /dao/company/update", new UpdateCompany());
        commands.put("POST /dao/company/put", new PutCompanyCommand());
        commands.put("POST /dao/company/delete", new DeleteCompanyCommand());

        commands.put("GET /dao/customer", new GetCustomerCommand());
        commands.put("GET /dao/createCustomer", new CreateCustomer());
        commands.put("POST /dao/customer/create", new PostCustomerCommand());
        commands.put("POST /dao/customer/update", new UpdateCustomer());
        commands.put("POST /dao/customer/put", new PutCustomerCommand());
        commands.put("POST /dao/customer/delete", new DeleteCustomerCommand());
    }

    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        String requestUri = req.getRequestURI();
        String commandKey = req.getMethod() + " " + requestUri;

        commands.get(commandKey).process(req, resp, engine);
    }

}
