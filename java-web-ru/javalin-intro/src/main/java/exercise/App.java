package exercise;

// BEGIN

// END

import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {

        // BEGIN
       var javalin = Javalin.create(config -> {
           config.plugins.enableDevLogging();
       });
       javalin.get("/welcome", ctx -> ctx.result("Welcome to Hexlet!"));
       javalin.get("/", ctx -> ctx.result("Home"));
       return  javalin;
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
