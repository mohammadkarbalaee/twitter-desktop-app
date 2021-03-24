package twitter.proj;

import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Twitter extends User
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

    public static void tweet(String text,String email) throws IOException
    {
        ArrayList<String> emailList = new ArrayList<>(getFileData("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\email.txt"));
        ArrayList<String> nameList = (ArrayList<String>) getFileData("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\name.txt");
        String name = "";
        for (int i = 0; i < emailList.size(); i++)
        {
            if (emailList.get(i).equals(email))
            {
                name = nameList.get(i);
            }
        }
        String code = tweetCode();
        String likes = "0";
        setTweetKey(name,email,code,likes);
        setTweetValue(text);
    }

    private static void setTweetKey(String name,String email,String code,String likes) throws IOException
    {
        FileWriter writer = new FileWriter("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\tweetKey.txt",true);
        writer.write(name + "/" + email + "/" + code + "/" + likes + "\n");
        writer.close();
    }

    private static void setTweetValue(String text) throws IOException
    {
        FileWriter writer = new FileWriter("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\tweetValue.txt",true);
        writer.write("<tweet>\n" + text + "\n</tweet>");
        writer.close();
    }

    private static String baseCode()
    {
        SecureRandom rand = new SecureRandom();
        StringBuilder tempPassword = new StringBuilder();
        tempPassword.setLength(20);
        char randomCharacter = ' ';
        for (int i = 0; i < 20; i++)
        {
            randomCharacter = (char)(rand.nextInt(26) + 'a');
            tempPassword.setCharAt(i,randomCharacter);
        }
        return tempPassword.toString();
    }

    private static String tweetCode()
    {
        SecureRandom rand = new SecureRandom();
        StringBuilder tempPassword = new StringBuilder(baseCode());
        char[] digits = {'0','1','2','3','4','5','6','7','8','9'};
        int quantityOfDigits = rand.nextInt(20) + 1;
        for (int i = 0; i < quantityOfDigits; i++)
        {
            int randomI = rand.nextInt((19) / 2);
            int randomDigit = rand.nextInt(10);
            tempPassword.setCharAt(randomI,digits[randomDigit]);
        }
        char[] specialCharacters = {'?','<','>','@','!','#','$','%','^','&','*','(',')','-','_','=','.','~','`'};
        int quantityOfSpecialCharacters = rand.nextInt(19) + 1;
        for (int i = 0; i < quantityOfSpecialCharacters; i++)
        {
            int randomI = rand.nextInt((19) / 2) + (19) / 2;
            int randomSpecialCharacter = rand.nextInt(19);
            tempPassword.setCharAt(randomI,specialCharacters[randomSpecialCharacter]);
        }
        return tempPassword.toString();
    }
}