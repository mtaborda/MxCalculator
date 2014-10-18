package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import models.Session;
import models.SessionEntry;
import services.SessionService;

import javax.ws.rs.*;

/**
 * Created by maximiliano on 13/10/14.
 */
@Path("/sessions")
public class SessionController {
    private SessionService sessionService = new SessionService();

    @Path("/all")
    @GET
    @Produces("application/json")
    public String retrieveAll() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        JsonArray array = new JsonArray();
        for (Session session : sessionService.retrieveAll()) {
            array.add(gson.toJsonTree(session));
        }
        return array.toString();
    }

    @Path("{id}")
    @GET
    @Produces("application/json")
    public String retrieve(@PathParam("id") int id) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        JsonArray array = new JsonArray();
        Session session = sessionService.retrieve(id);
        if (session == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("expression", "WRONG!");
            jsonObject.addProperty("result", "There's no session with id=" + id);
            array.add(jsonObject);
        } else {
            for (SessionEntry entry : session.getEntries()) {
                array.add(gson.toJsonTree(entry.calculation()));
            }
        }
        return array.toString();
    }
}
