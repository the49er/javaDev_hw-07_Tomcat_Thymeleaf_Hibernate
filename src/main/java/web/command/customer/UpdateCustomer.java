package web.command.customer;

import com.goit.javadev.database.entity_services.CompanyDaoService;
import com.goit.javadev.database.entity_services.CustomerDaoService;
import com.goit.javadev.database.feature.storage.Storage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import web.command.Command;

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

        CustomerDaoService customerDaoService = new CustomerDaoService(connection);

        String id = req.getParameter("id");

        Context simpleContext = new Context(
                req.getLocale(),
                Map.of(
                        "id", id,
                        "customer",customerDaoService.getEntityById(Long.parseLong(id)),
                        "maxIdCustomer", customerDaoService.getMaxId()
                )
        );

        engine.process("updateCustomer", simpleContext, resp.getWriter());
        resp.getWriter().close();
    }
}
