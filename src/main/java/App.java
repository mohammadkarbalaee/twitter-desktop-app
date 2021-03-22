import java.io.IOException;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        Scanner jin = new Scanner(System.in);
        String input;
        System.out.println("\t\tWelcome to Twitter application\n");
        do
        {
            input = jin.nextLine();
            clearScreen();
            if ("Sign up".equalsIgnoreCase(input));
            else if ("Login".equalsIgnoreCase(input));
            else if ("Help".equalsIgnoreCase(input))
            {
                System.out.printf
                        (
                                "%-15s||\tto terminate the program%n" +
                                        "%-15s||\tto make a new account%n" +
                                        "%-15s||\tto register in your Twitter account%n" +
                                        "%-15s||\tto unregister from your Twitter account%n" +
                                        "%-15s||\tto get the data of the person who has logged in now%n" +
                                        "%-15s||\tto make a tweet%n" +
                                        "%-15s||\tto follow a user%n" +
                                        "%-15s||\tto unfollow a user%n" +
                                        "%-15s||\tto see a list of your followers%n" +
                                        "%-15s||\tto see a list of the people you follow%n" +
                                        "%-15s||\tto see a list of all tweets in details%n" +
                                        "%-15s||\tto shows ones' profile%n" +
                                        "%-15s||\tto like a tweet%n%n" +
                                        "%-15s%n",
                                "Quit",
                                "Sign up",
                                "Login",
                                "Logout",
                                "My profile",
                                "Tweet",
                                "Follow",
                                "Unfollow",
                                "Followers",
                                "Following",
                                "Timeline",
                                "Profile",
                                "Like",
                                "***\t This commnads aren't case-sensistive\t***"
                        );
            }
            else if ("Quit".equalsIgnoreCase(input));
            else
                System.out.println(input + " is not a Twitter command");
        }
        while (!input.equalsIgnoreCase("Quit"));
    }

    public static void clearScreen()
    {
        try
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch (IOException | InterruptedException ex){}
    }
}