package exercise;

import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {

        // BEGIN
       var app = Javalin.create(javalinConfig -> javalinConfig.bundledPlugins.enableDevLogging());
       var phones = Data.getPhones();
       var domains = Data.getDomains();
       app.get("/phones", ctx -> ctx.json(phones));
       app.get("/domains", context -> context.json(domains));
       app.get("/", context -> context.redirect("phones"));

       return app;
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
