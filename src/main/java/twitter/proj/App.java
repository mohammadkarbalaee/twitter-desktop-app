package twitter.proj;

import java.io.IOException;
import java.util.Scanner;

public class App
{
    public static void main(String[] args) throws IOException
    {
        Scanner jin = new Scanner(System.in);
        System.out.println("\t\tCLI-based Twitter application\n");
        String input;
        do
        {
            input = jin.nextLine();
            clearScreen();
            if (input.equalsIgnoreCase("Sign up"))
            {
                System.out.println("\rEnter your full name");
                String name = jin.nextLine();
                System.out.println("Enter a valid email of yours");
                String email = jin.next();
                System.out.println
                (
                        "Enter a strong password\n" +
                        "Strong passwords include at least:\n1: an uppercase letter\n2: a digit\n3: a special character\n4: are at least 10 characters long"
                );
                String password = jin.next();
                if (User.signUp(name,email,password))
                {
                    User.showID(name,email,password);
                    System.out.println("Successful sign up\nto access your account, use Login command;for more info use Help command.");
                }
                else
                {
                    System.out.println("Failed to sign you up.use Sign up command again, not repeating the shown warnings");
                }
            }
            else if (input.equalsIgnoreCase("Login"))
            {
                System.out.println("Enter your email");
                String email = jin.next();
                System.out.println("Enter your password");
                String password = jin.next();
                boolean successfulLogin = User.logIn(email,password);
                if (successfulLogin)
                {
                    String thisInput;
                    do
                    {
                        thisInput = jin.nextLine();
                        clearScreen();
                        if (thisInput.equalsIgnoreCase("My profile"))
                        {
                            Twitter.myProfile(email);
                        }
                        else if (thisInput.equalsIgnoreCase("tweet"))
                        {
                            System.out.println("Enter the text of your tweet");
                            String text = jin.nextLine();
                            Twitter.tweet(text,email);
                            System.out.println("tweet is saved successfully");
                        }
                        else if (thisInput.equalsIgnoreCase("follow"))
                        {
                            System.out.println("Enter the email of the user you want to follow");
                            String followedEmail = jin.next();
                            Twitter.follow(followedEmail,email);
                            System.out.println("followed successfully");
                        }
                        else if (thisInput.equalsIgnoreCase("unfollow"))
                        {
                            System.out.println("Enter the email of the user you want to unfollow");
                            String unfollowedEmail = jin.next();
                            Twitter.unfollow(unfollowedEmail,email);
                            System.out.println("unfollowed successfully");
                        }
                        else if (thisInput.equalsIgnoreCase("following"))
                        {
                            String[] parsedFollowingLine = Twitter.following(email);
                            for (String tmp : parsedFollowingLine)
                            {
                                System.out.println(tmp);
                            }
                        }
                        else if (thisInput.equalsIgnoreCase("follower"))
                        {
                            Twitter.follower(email);
                        }
                        else if (thisInput.equalsIgnoreCase("Timeline"))
                        {
                            Twitter.timeLine(email);
                        }
                        else if (thisInput.equalsIgnoreCase("Profile"))
                        {
                            System.out.println("Enter the email of the user whom you want his/her tweets");
                            String targetEmail = jin.next();
                            Twitter.profile(targetEmail);
                        }
                        else if (thisInput.equalsIgnoreCase("Like"))
                        {
                            System.out.println("Enter the code of the tweet that you want to like");
                            String code = jin.next();
                            Twitter.like(code);
                        }
                        else
                        {
                            if (!input.equalsIgnoreCase("logout"))
                                wrongCommandError(input);
                        }
                    }
                    while (!thisInput.equalsIgnoreCase("Logout"));
                }

            }
            else if (input.equalsIgnoreCase("Help"))
            {
                helpContent();
            }
            else
            {
                if (!input.equalsIgnoreCase("Quit"))
                    wrongCommandError(input);
            }
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

    public static void helpContent()
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

    public static void wrongCommandError(String input)
    {
        System.err.println(input + " is not a Twitter command");
    }
}