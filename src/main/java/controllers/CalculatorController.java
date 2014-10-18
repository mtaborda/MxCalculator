package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import models.Calculation;
import models.Session;
import services.CalculatorService;
import services.SessionService;

import javax.ws.rs.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * Created by maximiliano on 15/10/14.
 */
@Path("/calculator")
public class CalculatorController {
    private CalculatorService calculatorService = new CalculatorService();
    private SessionService sessionService = new SessionService();


    @Path("/calculate/{expression}")
    @GET
    @Produces("application/json")
    public String calculate(@PathParam("expression") String expression) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Calculation calc = calculatorService.calculate(expression);
        return gson.toJson(calc);
    }

    @Path("/save/{calcs}")
    @GET
    @Produces ("application/json")
    public String save(@PathParam("calcs") String calcs) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Type calcListType = new TypeToken<Collection<Calculation>>() {}.getType();
        List<Calculation> calculations = gson.fromJson(calcs, calcListType);

        if (calculations.size()!=0) {
            calculatorService.save(calculations);

            JsonArray array = new JsonArray();
            for (Session session : sessionService.retrieveAll()) {
                array.add(gson.toJsonTree(session));
            }
            return array.toString();
        }

        return null;
    }
}
