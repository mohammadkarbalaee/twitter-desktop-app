package twitter.proj;

import java.util.ArrayList;

public class User
{
    private ArrayList<User> followers = new ArrayList<>();
    private ArrayList<User> followings = new ArrayList<>();
    private String name;
    private String email;
    private String password;
    private ArrayList<Tweet> tweets = new ArrayList<>();

    public User(String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public ArrayList<User> getFollowers()
    {
        return followers;
    }

    public void setFollowers(User follower)
    {
        followers.add(follower);
    }

    public ArrayList<User> getFollowings()
    {
        return followings;
    }

    public void setFollowings(User following)
    {
        followings.add(following);
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public ArrayList<Tweet> getTweets()
    {
        return tweets;
    }

    public void setTweet(Tweet tweet)
    {
        tweets.add(tweet);
    }

    public boolean setUnfollower(User unfollower)
    {
        return followers.remove(unfollower);
    }

    public boolean setUnfollowing(User unfollowing)
    {
        return followings.remove(unfollowing);
    }
}