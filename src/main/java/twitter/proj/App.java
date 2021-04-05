package twitter.proj

import java.io.*;

public class App
{
    final static String fileAddress = System.getProperty("user.dir") + "\\log.bin";
    final static File file = new File(fileAddress);

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException
    {
        Twitter twitter = twitterConstruct();
        twitter.userInterface();
        writeTwitterOnFile(twitter);
    }

    public static Twitter twitterConstruct() throws IOException, ClassNotFoundException, IOException
    {
        Twitter twitter;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        if (file.createNewFile())
        {
            return new Twitter();
        }
        else
        {
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            twitter = (Twitter) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }
        return twitter;
    }

    public static void writeTwitterOnFile(Twitter twitter) throws IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(twitter);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
    }
}
