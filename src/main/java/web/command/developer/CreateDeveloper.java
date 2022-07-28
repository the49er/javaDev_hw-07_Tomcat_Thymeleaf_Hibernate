package web.command.developer;

import com.goit.javadev.database.entity_services.CompanyDaoService;
import com.goit.javadev.database.entity_services.ProjectDaoService;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collections;
import java.util.Map;

public class CreateDeveloper implements Command {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateEngine engine) throws IOException {
        resp.setContentType("text/html");
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of("maxId", new CompanyDaoService(connection).getMaxId())
        );

        engine.process("createDeveloper", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
