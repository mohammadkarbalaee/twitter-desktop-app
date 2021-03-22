import java.io.*;
import java.util.*;

public class User
{
    private String name;
    private String email;
    private String password;
    private boolean isNew;

    public void signUp(String name,String email,String password) throws IOException
    {
        boolean isNameSet = false;
        boolean isEmailSet = setEmail(email);
        boolean isPasswordSet = setPassword(password);
        if (isEmailSet && isPasswordSet)
            isNameSet = setName(name);
        if (isNameSet && isEmailSet && isPasswordSet)
            isNew = true;
        else
            isNew = false;
    }

    public boolean getIsNew()
    {
        return isNew;
    }

    private boolean setName(String name) throws IOException
    {
        File file = new File("name.txt");
        Scanner reader = new Scanner(file);
        Set<String> namesSet = new LinkedHashSet<>();
        while (reader.hasNextLine())
        {
            namesSet.add(reader.nextLine());
        }
        int sizeBefore = namesSet.size();
        namesSet.add(name);
        int sizeAfter = namesSet.size();
        if (sizeAfter == sizeBefore)
        {
            System.err.println("This name is already taken.choose another one");
            return false;
        }
        else
        {
            this.name = name;
            FileWriter writer = new FileWriter("name.txt");
            for (String temp : namesSet)
            {
                if (!temp.equals(" "))
                    writer.write(temp + "\n");
            }
            writer.close();
            return true;
        }
    }

    public void showID()
    {
        System.out.printf("name: %s | email: %s | password : %s\n",this.name,this.email,this.password);
    }

    private boolean setEmail(String email) throws IOException
    {
        if (!email.matches("[\\w.]+@[\\w.]+\\.[\\w]+"))
        {
            System.err.println("This email is not valid");
            return false;
        }
        File file = new File("email.txt");
        Scanner reader = new Scanner(file);
        Set<String> emailSet = new LinkedHashSet<>();
        while (reader.hasNextLine())
        {
            emailSet.add(reader.nextLine());
        }
        int sizeBefore = emailSet.size();
        emailSet.add(email);
        int sizeAfter = emailSet.size();
        if (sizeAfter == sizeBefore)
        {
            System.err.println("This email is already taken.choose another one");
            return false;
        }
        else
        {
            this.email = email;
            FileWriter writer = new FileWriter("email.txt");
            for (String temp : emailSet)
            {
                if (!temp.equals(" "))
                    writer.write(temp + "\n");
            }
            writer.close();
            return true;
        }
    }

    private boolean setPassword(String password) throws IOException
    {
        if (!password.matches("[\\w]+[A-Z]+[0-9]+[^\\w]+"))
        {
            System.err.println("This password is weak");
            return false;
        }
        File file = new File("password.txt");
        Scanner reader = new Scanner(file);
        List<String> passwordList = new ArrayList<>();
        while (reader.hasNextLine())
        {
            passwordList.add(reader.nextLine());
        }
        passwordList.add(password);
        this.password = password;
        FileWriter writer = new FileWriter("password.txt");
        for (String temp : passwordList)
        {
            if (!temp.equals(" "))
                writer.write(temp + "\n");
        }
        writer.close();
        return true;
    }
}