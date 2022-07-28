package web.command.project;

import com.goit.javadev.database.entity.project.Project;
import com.goit.javadev.database.entity_services.ProjectDaoService;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

public class PostProjectCommand implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();
        ProjectDaoService projectDaoService = new ProjectDaoService(connection);

        Project project = new Project();

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int customerId = Integer.parseInt(req.getParameter("customerId"));
        int companyId = Integer.parseInt(req.getParameter("companyId"));


        project.setName(name);
        project.setDescription(description);
        project.setDate(LocalDate.now());
        project.setCustomerId(customerId);
        project.setCompanyId(companyId);

        projectDaoService.insertNewEntity(project);

        resp.sendRedirect("/dao/project");
    }
}
