package twitter.proj;

import java.time.LocalDateTime;

public class Tweet
{
    public int likes = 0;
    public String code;
    public String mainText;
    public LocalDateTime time;
    public User owner;

    public Tweet(User owner,String code,String mainText,LocalDateTime time)
    {
        this.code = code;
        this.mainText = mainText;
        this.time = time;
        this.owner = owner;
    }

    public Tweet()
    {
    }

    public void addLike()
    {
        likes++;
    }

    public int getLikes()
    {
        return likes;
    }

    public String getCode()
    {
        return code;
    }

    public String getMainText()
    {
        return mainText;
    }

    public LocalDateTime getTime()
    {
        return time;
    }

    public User getOwner()
    {
        return owner;
    }
}
