package com.example.demo;

import org.json.JSONObject;
import java.sql.*;


public class Database {
    public static String url = "jdbc:sqlite:src/main/resources/Database/dist.db";

    public String insert(String week,String team1,String time,String team2,String matchResult,String matchDate)
    {
        JSONObject object=new JSONObject();
        String message = null;
        String sql = "SELECT * from results where team1=? and team2=? and matchDate=?";
        try {
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,team1);
            preparedStatement.setString(2,team2);
            preparedStatement.setString(3,matchDate);
            preparedStatement.execute();
            ResultSet rs=preparedStatement.executeQuery();
            if(!rs.next())
            {
                String query = "insert into results (week, team1,time,team2,matchResult,matchDate)" +
                        " values ('"+week+"','"+team1+"','"+time+"','"+team2+"','"+matchResult+"', '"+matchDate+"')";
                try {
                    Statement st=connection.createStatement();
                    int resultInserted=st.executeUpdate(query);
                    if(resultInserted==1)
                    {
                        message = "Successfully inserted";
                    }
                    else{
                        message = "Failed to insert";
                    }
                    object.put("message",message);
                    st.close();
                    connection.close();
                    return object.toString();

                } catch (SQLException e) {
                    object.put("message",e.getMessage());
                    return object.toString();
                }

            }
            else{
                message = "Failed to insert because this match exists!";
                object.put("message",message);
                return object.toString();
            }

        }
        catch(SQLException e)
        {
            object.put("message",e.getMessage());
            return object.toString();
        }

    }
    public String specificTeam(String teamName) {
        String sql = "SELECT * from results where team1='" + teamName + "'" + "or team2='" + teamName + "'";
        StringBuilder allData = new StringBuilder("[");
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                JSONObject object=new JSONObject();
                object.put("id",Integer.toString(rs.getInt("id")));
                object.put("week",rs.getString("week"));
                object.put("team1",rs.getString("team1"));
                object.put("time",rs.getString("time"));
                object.put("team2",rs.getString("team2"));
                object.put("matchResult",rs.getString("matchResult"));
                object.put("matchDate",rs.getString("matchDate"));
                allData.append(object).append(",");
            }
            allData = new StringBuilder(allData.substring(0, allData.length() - 1));
            allData.append("]");
            statement.close();
            connection.close();
            return allData.toString();

        } catch (SQLException e) {
            JSONObject object=new JSONObject();
            object.put("message",e.getMessage());
            allData.append(object).append("]");
            return allData.toString();
        }
    }
    public String specificWeek(String week) {
        String sql = "SELECT * from results where week=?";
        StringBuilder allData = new StringBuilder("[");
        try {
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,week);
            preparedStatement.execute();
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next())
            {
                JSONObject object=new JSONObject();
                object.put("id",Integer.toString(rs.getInt("id")));
                object.put("week",rs.getString("week"));
                object.put("team1",rs.getString("team1"));
                object.put("time",rs.getString("time"));
                object.put("team2",rs.getString("team2"));
                object.put("matchResult",rs.getString("matchResult"));
                object.put("matchDate",rs.getString("matchDate"));
                allData.append(object).append(",");
            }
            allData = new StringBuilder(allData.substring(0, allData.length() - 1));
            allData.append("]");
            preparedStatement.close();
            connection.close();
            return allData.toString();

        } catch (SQLException e) {
            JSONObject object=new JSONObject();
            object.put("message",e.getMessage());
            allData.append(object).append("]");
            return allData.toString();
        }
    }
    public String allData() {
        String sql = "SELECT * from results";
        StringBuilder allData = new StringBuilder("[");
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                JSONObject object=new JSONObject();
                object.put("id",Integer.toString(rs.getInt("id")));
                object.put("week",rs.getString("week"));
                object.put("team1",rs.getString("team1"));
                object.put("time",rs.getString("time"));
                object.put("team2",rs.getString("team2"));
                object.put("matchResult",rs.getString("matchResult"));
                object.put("matchDate",rs.getString("matchDate"));
                allData.append(object).append(",");
            }
            allData = new StringBuilder(allData.substring(0, allData.length() - 1));
            allData.append("]");
            statement.close();
            connection.close();
            return allData.toString();

        } catch (SQLException e) {
            JSONObject object=new JSONObject();
            object.put("message",e.getMessage());
            allData.append(object).append("]");
            return allData.toString();
        }
    }


}
