import java.io.*;
 
/**
 * Write a description of class Message here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Message implements Serializable
{
    // instance variables - replace the example below with your own
    private String name;
    private String messageContent;

    /**
     * Constructor for objects of class Message
     */
    public Message(String inputtedName, String inputtedResponse)
    {
        // initialise instance variables
        name = inputtedName;
        messageContent = inputtedResponse;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String newName)
    {
        name = newName;
    }
    
    public String getMessageContent()
    {
        return messageContent;
    }
    
    public void setMessage(String inputtedMessage)
    {
        messageContent = inputtedMessage;
    }
}

