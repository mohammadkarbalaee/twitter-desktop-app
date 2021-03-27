import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

public class Twitter
{
    private ArrayList<User> signedUps = new ArrayList<>();
    private User loggedInUser;

    public boolean signUp(User user)
    {
        boolean isValid = false;
        boolean isUnique = false;
        if (user.getEmail().matches("[\\w.]+@[\\w.]+\\.[\\w]+"))
        {
            isValid = true;
        }
        if (isValid)
        {
            if (signedUps.isEmpty())
            {
                signedUps.add(user);
                isUnique = true;
            }
            else
            {
                boolean isNameUnique = true;
                for (User tmp : signedUps)
                {
                    if (tmp.getName().equals(user.getName()))
                    {
                        isNameUnique = false;
                        break;
                    }
                }
                boolean isEmailUnique = true;
                if (isNameUnique)
                {
                    for (User tmp : signedUps)
                    {
                        if (tmp.getEmail().equals(user.getEmail()))
                        {
                            isEmailUnique = false;
                            break;
                        }
                    }
                }

                if (isNameUnique && isEmailUnique)
                {
                    signedUps.add(user);
                    isUnique = true;
                }
            }
        }
        return (isUnique && isValid);
    }

    public boolean login(String email,String password)
    {
        boolean isLoggedIn = false;
        for (int i = 0; i < signedUps.size(); i++)
        {
            if (signedUps.get(i).getEmail().equals(email))
            {
                if (signedUps.get(i).getPassword().equals(password))
                {
                    isLoggedIn = true;
                    loggedInUser = new User(signedUps.get(i).getName(),signedUps.get(i).getEmail(),signedUps.get(i).getPassword());
                    break;
                }
            }
        }
        return isLoggedIn;
    }

    public void tweet(String mainText)
    {
        Tweet newTweet = new Tweet(loggedInUser,genCode(),mainText, LocalDateTime.now());
        loggedInUser.setTweet(newTweet);
    }

    public boolean like(String code)
    {
        boolean isTweetAvailable = false;
        for (int i = 0; i < signedUps.size(); i++)
        {
            for (int j = 0; j < signedUps.get(i).getTweets().size(); j++)
            {
                System.out.println("i am in second loop");
                if (signedUps.get(i).getTweets().get(j).getCode().equals(code))
                {
                    isTweetAvailable = true;
                    signedUps.get(i).getTweets().get(j).addLike();
                }
            }
        }
        System.out.println(isTweetAvailable);
        return isTweetAvailable;
    }

    private String baseCode()
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

    private String genCode()
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

    public void myProfile()
    {
        for (Tweet tmp : loggedInUser.getTweets())
        {
            System.out.printf("---------------------\nTweet's code: %s\n" +
                    "likes: %d\n" +
                    "main text: %s\n",
                    tmp.getCode(),
                    tmp.getLikes(),
                    tmp.getMainText());
        }
        System.out.println("---------------------");
    }

    public boolean profile(String name)
    {
        boolean isUserAvailable = false;
        for (int i = 0; i < signedUps.size(); i++)
        {
            if (signedUps.get(i).getName().equals(name))
            {
                isUserAvailable = true;
                for (Tweet tmp : signedUps.get(i).getTweets())
                {
                    System.out.printf("---------------------\nTweet's code: %s\n" +
                            "likes: %d\n" +
                            "main text: %s\n",
                            tmp.getCode(),
                            tmp.getLikes(),
                            tmp.getMainText());
                }
                System.out.println("---------------------");
            }
        }
        return isUserAvailable;
    }

    public boolean follow(String name)
    {
        boolean isUserAvailable = false;
        for (int i = 0; i < signedUps.size(); i++)
        {
            if (signedUps.get(i).getName().equals(name))
            {
                isUserAvailable = true;
                signedUps.get(i).setFollowers(loggedInUser);
                loggedInUser.setFollowings(signedUps.get(i));
            }
        }
        return isUserAvailable;
    }

    public boolean unfollow(String name)
    {
        boolean isUserAvailable = false;
        boolean isUnfollowed = false;
        for (int i = 0; i < signedUps.size(); i++)
        {
            if (signedUps.get(i).getName().equals(name))
            {
                isUserAvailable = true;
                loggedInUser.setUnfollowing(signedUps.get(i));
                isUnfollowed = signedUps.get(i).setUnfollower(loggedInUser);
            }
        }
        return isUserAvailable && isUnfollowed;
    }

    public void followers()
    {
        for (User tmp : loggedInUser.getFollowers())
        {
            System.out.println(tmp.getName());
        }
    }

    public void following()
    {
        for (User tmp : loggedInUser.getFollowings())
        {
            System.out.println(tmp.getName());
        }
    }

    public void timeline()
    {
        ArrayList<LocalDateTime> tweetsTime = new ArrayList<>();
        ArrayList<Tweet> allRelatedTweets = new ArrayList<>();
        loggedInUser.getTweets().addAll(allRelatedTweets);
        for (Tweet tmp : loggedInUser.getTweets())
        {
            tweetsTime.add(tmp.getTime());
        }
        for (User tmp : loggedInUser.getFollowers())
        {
            tmp.getTweets().addAll(allRelatedTweets);
            for (Tweet temp : tmp.getTweets())
            {
                tweetsTime.add(temp.getTime());
            }
        }
        Collections.sort(tweetsTime);
        for (LocalDateTime tmp : tweetsTime)
        {
            for (Tweet temp : allRelatedTweets)
            {
                if (tmp.compareTo(temp.getTime()) == 0)
                {
                    System.out.printf("---------------------\nTweet's owner: %s" +
                            "Tweet's code: %s\n" +
                            "likes: %d\n" +
                            "main text: %s\n",
                            temp.getOwner().getName(),
                            temp.getCode(),
                            temp.getLikes(),
                            temp.getMainText());
                }
            }
        }
    }
}