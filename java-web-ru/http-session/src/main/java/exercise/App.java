package exercise;

import io.javalin.Javalin;

import java.util.Date;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", context -> {
            var page = context.queryParamAsClass("page", Integer.class).getOrDefault(1);
            var per  = context.queryParamAsClass("per",Integer.class).getOrDefault(5);
            var offset = (page - 1) * per;
            List<Map<String, String>> responseBody = USERS.subList(offset, offset + per);
            context.json(responseBody);
        });
        app.get("/", context -> context.json(USERS));
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
