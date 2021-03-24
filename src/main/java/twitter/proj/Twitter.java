package twitter.proj;

import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Twitter
{
    private static String[] getTweetData() throws FileNotFoundException
    {
        File file = new File("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\tweetValue.txt");
        Scanner reader = new Scanner(file);
        StringBuilder allTweets = new StringBuilder("");
        while (reader.hasNextLine())
        {
            allTweets.append(reader.nextLine());
        }
        return StringUtils.substringsBetween(allTweets.toString(),"<tweet>","</tweet>");
    }

    private static ArrayList<String> getIDData() throws FileNotFoundException
    {
        File file = new File("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\tweetKey.txt");
        Scanner reader = new Scanner(file);
        ArrayList<String> keys = new ArrayList<>();
        while (reader.hasNextLine())
        {
            keys.add(reader.nextLine());
        }
        return keys;
    }

    private static HashMap<String,String> idTweetMap() throws FileNotFoundException
    {
        HashMap<String,String> tweetMap = new HashMap<>();
        ArrayList<String> keys = getIDData();
        String[] values = getTweetData();
        for (int i = 0; i < values.length; i++)
        {
            tweetMap.put(keys.get(i),values[i]);
        }
        return tweetMap;
    }

    public static void myProfile(String email) throws FileNotFoundException
    {
        String[] tweets = getTweetData();
        String[][] allDataParsed = idParser();
        for (int i = 0; i < allDataParsed.length; i++)
        {
            if (allDataParsed[i][1].equals(email))
            {
                System.out.printf
                (
                                "------------------%n" +
                                "Tweet %d%n" +
                                "Owner's name: %s%n" +
                                "Owner's email: %s%n" +
                                "Tweet's code: %s%n" +
                                "Likes: %s%n" +
                                "Tweet's text: %n%s%n",
                        i+1,allDataParsed[i][0],allDataParsed[i][1],allDataParsed[i][2],allDataParsed[i][3],tweets[i]
                );
            }
        }
    }

    private static String[][] idParser() throws FileNotFoundException
    {
        ArrayList<String> keys = getIDData();
        String[][] parsedIds = new String[keys.size()][4];
        for (int i = 0; i < keys.size(); i++)
        {
            parsedIds[i] = keys.get(i).split("/");
        }
        return parsedIds;
    }
}