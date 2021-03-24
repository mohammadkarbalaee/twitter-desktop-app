package twitter.proj;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class User
{
    private static boolean canSetEmail(String email) throws FileNotFoundException
    {
        if (!email.matches("[\\w.]+@[\\w.]+\\.[\\w]+"))
            return false;
        Collection<String> emailSet = getFileData("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\email.txt");
        int sizeBefore = emailSet.size();
        emailSet.add(email);
        int sizeAfter = emailSet.size();
        if (sizeAfter == sizeBefore)
            return false;
        else
            return true;
    }

    private static boolean canSetPassword(String password)
    {
        if (
                password.matches("([\\w]+|[A-Z]+|[0-9]+|[^\\w]+)([\\w]+|[A-Z]+|[0-9]+|[^\\w]+)([\\w]+|[A-Z]+|[0-9]+|[^\\w]+)") &&
                password.length() >= 10
            )
            return true;
        return false;
    }

    public static boolean signUp(String name,String email,String password) throws IOException
    {
        if (canSetEmail(email))
        {
            if (canSetPassword(password))
            {
                setName(name);
                setEmail(email);
                setPassword(password);
                return true;
            }
            else
                System.out.println("This password is too weak");
        }
        else
            System.out.println("This email is not valid");
        return false;
    }

    private static void setName(String name) throws IOException
    {
        Collection<String> namesSet = getFileData("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\name.txt");
        namesSet.add(name);
        setFileData("twitter/proj/name.txt",namesSet);
    }

    public static void showID(String name,String email,String password)
    {
        System.out.printf("name: %s | email: %s | password : %s\n",name,email,password);
    }

    private static void setEmail(String email) throws IOException
    {
        Collection<String> emailSet = getFileData("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\email.txt");
        emailSet.add(email);
        setFileData("twitter/proj/email.txt",emailSet);
    }

    private static void setPassword(String password) throws IOException
    {
        Collection<String> passwordSet = getFileData("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\password.txt");
        passwordSet.add(password);
        setFileData("twitter/proj/password.txt",passwordSet);
    }

    protected static Collection<String> getFileData(String fileAddress) throws FileNotFoundException
    {
        File file = new File(fileAddress);
        Scanner reader = new Scanner(file);
        Collection<String> dataList;
        if (fileAddress.equals("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\name.txt") || fileAddress.equals("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\password.txt"))
            dataList = new ArrayList<>();
        else
            dataList = new LinkedHashSet<>();
        while (reader.hasNextLine())
        {
            dataList.add(reader.nextLine());
        }
        return dataList;
    }

    private static void setFileData(String fileAddress,Collection<String> dataList) throws IOException
    {
        FileWriter writer = new FileWriter(fileAddress);
        for (String temp : dataList)
        {
            if (!temp.equals(" "))
                writer.write(temp + "\n");
        }
        writer.close();
    }

    public static boolean logIn(String email,String password) throws FileNotFoundException
    {
        ArrayList<String> emailList = new ArrayList<>(getFileData("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\email.txt"));
        ArrayList<String> nameList = (ArrayList<String>) getFileData("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\name.txt");
        ArrayList<String> passwordList = (ArrayList<String>) getFileData("C:\\Users\\muham\\Desktop\\twitter\\src\\main\\java\\twitter\\proj\\password.txt");
        for (int i = 0; i < emailList.size(); i++)
        {
            if (emailList.get(i).equals(email))
            {
                if (passwordList.get(i).equals(password))
                {
                    System.out.printf("Logged in as: %s%n",nameList.get(i));
                    return true;
                }
            }
        }
        System.out.println("Either email or password is incorrect");
        return false;
    }
}