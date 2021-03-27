import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tweet
{
    private int likes = 0;
    private String code;
    private String mainText;
    private LocalDateTime time;
    private User owner;

    public Tweet(User owner,String code,String mainText,LocalDateTime time)
    {
        this.code = code;
        this.mainText = mainText;
        this.time = time;
        this.owner = owner;
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
