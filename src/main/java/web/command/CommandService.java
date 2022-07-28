package web.command;

import org.thymeleaf.TemplateEngine;
import web.command.developer.CreateDeveloper;
import web.command.developer.DeleteDevelopersCommand;
import web.command.developer.GetDevelopersCommand;
import web.command.developer.PostDevelopersCommand;
import web.command.developer.PutDevelopersCommand;
import web.command.developer.UpdateDeveloper;
import web.command.project.GetProjectsCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandService {
    private Map<String, Command> commands;

    public CommandService() {
        commands = new HashMap<>();

        commands.put("GET /dao/developer", new GetDevelopersCommand());
        commands.put("POST /dao/developer/update", new UpdateDeveloper());
        commands.put("POST /dao/developer/put", new PutDevelopersCommand());
        commands.put("POST /dao/developer/create", new PostDevelopersCommand());
        commands.put("POST /dao/developer/delete", new DeleteDevelopersCommand());
        commands.put("GET /dao/index", new IndexCommand());
        commands.put("GET /dao/createDeveloper", new CreateDeveloper());
        commands.put("GET /dao/project", new GetProjectsCommand());
    }

    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        String requestUri = req.getRequestURI();
        String commandKey = req.getMethod() + " " + requestUri;

        commands.get(commandKey).process(req, resp, engine);
    }

}
