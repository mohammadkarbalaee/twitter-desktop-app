package twitter.proj;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import java.io.*;
import java.sql.*;

public class App
{
    static Boolean isDatabaseEmpty;
    final static String url = "jdbc:mysql://localhost:3306/twitter?user=root";
    static Connection connection;
    static
    {
        try
        {
            connection = DriverManager.getConnection(url);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
    static Statement st;
    static
    {
        try
        {
            st = connection.createStatement();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Twitter twitter = twitterConstruct();
        twitter.userInterface();
        sendTwitterToDatabase(twitter);
    }

    private static Twitter twitterConstruct() throws SQLException
    {
        Twitter twitter;
        String query = "SELECT Json FROM app";
        ResultSet rs = st.executeQuery(query);
        String json = "";
        while (rs.next())
        {
            json = rs.getString("Json");
        }
        if (json.equals(""))
        {
            twitter = new Twitter();
            rs.close();
            isDatabaseEmpty = true;
        }
        else
        {
            Gson gson = new Gson();
            twitter = gson.fromJson(json, Twitter.class);
            rs.close();
            isDatabaseEmpty = false;
        }
        return twitter;
    }

    public static void sendTwitterToDatabase(Twitter twitter) throws IOException, SQLException
    {
        ObjectMapper objectMapper = objectMapperConstructor();
        String jsonInString = objectMapper.writeValueAsString(twitter);
        String query;
        if (isDatabaseEmpty)
        {
            query = "INSERT INTO app(Json) VALUES('" + jsonInString + "')";
        }
        else
        {
            query = "UPDATE app SET Json='" + jsonInString + "'";
        }
        st.executeUpdate(query);
    }

    public static ObjectMapper objectMapperConstructor()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT,true);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT,true);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
