package com.example.demo;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
public class DatabaseClass {
    @GetMapping("/")
    public String home() {
        return "Home page";
    }

    @RequestMapping(
            value = "/insertMatch",
            method = RequestMethod.POST)
    public String insertMatch(@RequestBody Map<String, Object> payload)
    {
        String week=(String)payload.get("week");
        String team1=(String)payload.get("team1");
        String time=(String)payload.get("time");
        String team2=(String)payload.get("team2");
        String matchResult=(String)payload.get("matchResult");
        String matchDate=(String)payload.get("matchDate");
        if(checkLength(week) && checkLength(team1) && checkLength(time)
                && checkLength(team2) && checkLength(matchResult) && checkLength(matchDate) )
        {
            Database db=new Database();
            return db.insert(week,team1,time,team2,matchResult,matchDate);
        }
        else{
            JSONObject object=new JSONObject();
            object.put("message","Please fill fields");
            return object.toString();
        }
    }
    public boolean checkLength(String value)
    {
        if(value.length()>=1)
            return true;
        else
            return false;
    }
    @RequestMapping(
            value = "/selectByName/{teamName}",
            method = RequestMethod.GET)
    public String select(@PathVariable String teamName)
    {

        if(checkLength(teamName))
        {
            Database db=new Database();
            return db.specificTeam(teamName);
        }
        else{
            JSONObject object=new JSONObject();
            object.put("message","Please fill fields");
            return object.toString();
        }
    }
    @RequestMapping(
            value = "/selectByWeek/{week}",
            method = RequestMethod.GET)
    public String selectByWeek(@PathVariable String week)
    {

        if(checkLength(week))
        {
            Database db=new Database();
            return db.specificWeek(week);
        }
        else{
            JSONObject object=new JSONObject();
            object.put("message","Please fill fields");
            return object.toString();
        }
    }
    @RequestMapping(
            value = "/allData",
            method = RequestMethod.GET)
    public String allData()
    {
        Database db=new Database();
        return db.allData();
    }
}
