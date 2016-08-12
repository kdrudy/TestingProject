package com.theironyard.kyru.testing;


import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.staticFiles;


/**
 * Created by kdrudy on 8/4/16.
 */
public class DissociatedPressSpark {

    public static void main(String[] args) {
        Spark.port(getHerokuAssignedPort());

        staticFiles.location("/templates");

        Spark.init();
        Spark.get("/", ((req, res) ->
        {
            Testing t = new Testing();
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            String newQuote = t.dissociatedPressFormatted(3, 1, 15, "timecube.txt");
            System.out.println(newQuote);

            Map map = new HashMap();
            map.put("quote", newQuote);
            map.put("currentFile", "timecube.txt");
            map.put("currentFileDescription", "The text of the last version of Gene Ray's Time Cube website.");

            return new ModelAndView(map, "dissociatedpress.html");
        }), new MustacheTemplateEngine());

        Spark.post(
                "/",
                ((request, response) -> {
                    response.redirect("/");
                    return "";
                })
        );

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
