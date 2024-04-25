
/*
 * Copyright (c) 1995, 2014, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import java.net.*;
import java.io.*;

public class IMServer {
    public static void main(String[] args) throws IOException {
        
        // CSC 450 LAB 6: ARGS LENGTH WILL BE 2 NOW, SINCE WE NEED AN EXTRA ARGS ELEMENT TO
        // WRITE THE NAME OF THE SERVER IN THE COMMAND LINE.
        if (args.length != 1) {
            System.err.println("Usage: java IMServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        // CSC 450 LAB 6: SAVE THE NAME OF THE SERVER FROM COMMAND LINE ARGUMENT.
        
        try ( 
            // FIRST STEP: LISTEN ON A SPECIFIC PORT FOR A CONNECTION REQUEST.
            // SECOND STEP IN THE CLIENT CLASS.
            ServerSocket serverSocket = new ServerSocket(portNumber);
            
            // THIRD STEP: ACCEPT CONNECTION REQUEST.
            Socket clientSocket = serverSocket.accept();
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            //PrintWriter out =
                //new PrintWriter(clientSocket.getOutputStream(), true);
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());//new BufferedReader(
                //new InputStreamReader(clientSocket.getInputStream()));
        ) {
        
            String inputLine, outputLine;
            
            // Initiate conversation with client
            IMProtocol imp = new IMProtocol();
            outputLine = imp.processInput(null);
            Message sendToClient = new Message("Server:", outputLine);
            
            out.writeObject(sendToClient);
            
            Message fromClient; //= new Message(null, null);
            
            // NINTH STEP: RECEIVE RESPONSE FROM CLIENT.
            // TWELVETH STEP: REPEAT STEPS 5-11 UNTIL SOMEONE SAYS BYE..
            while ((fromClient = (Message)in.readObject()) != null) {
                 
             
                // CSC 450 LAB 6: READ THE MESSAGE OBJECT SENT BY THE CLIENT AND DISPLAY IT.
                //Message receivedMessage = new Message(" ", " ");
                //try
                //{
                    //receivedMessage = fromClient;//(Message)in.readObject();
                    System.out.println(fromClient.getName() + ": " + fromClient.getCharContent());//("Client: " + inputLine);
                //}
                //catch (ClassNotFoundException cnfe)               // WE DO NOT NEED A TRY-CATCH
                                                                    // BECAUSE WERE READING OBJECT
                                                                    // IN THE WHILE LOOP BOOLEAN STATEMENT.
                                                                    // CATCHING IT AT THE BOTTOM OF THE GRAND TRY-CATCH WERE IN.
                //{
                    //cnfe.printStackTrace();               
                //}

                // TENTH STEP: DETERMINE SERVER'S REPLY.
                outputLine = imp.processInput(fromClient);
                
                
                // ELEVENTH STEP: SEND SERVER'S REPLY TO CLIENT.
                Message serversReply = new Message("Server: ", outputLine);
                out.writeObject(serversReply);
                System.out.println(serversReply.getName() + ": " + serversReply.getMessageContent()); // Display own message on own terminal.
                
                // End the conversation if server says bye.
                if (outputLine.equals("Goodbye!"))
                    break;
              
            }
            
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
