/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
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

public class IMProtocol {
    private static final int WAITING = 0;
    private static final int IN_CONVERSATION = 1;
    //private static final int SENTCLUE = 2;
    //private static final int ANOTHER = 3;

    //private static final int NUMJOKES = 6;

    private int state = WAITING;
    //private int currentJoke = 0;

    // CSC450 LAB3: NO LONGER NEED KNOCK KNOCK JOKES FOR THIS ASSIGNMENT. (IM (Instant Messaging Lab))
    //private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who", "BOO!"};
    //private String[] answers = { "Turnip the heat, it's cold in here!",
                                 //"I didn't know you could yodel!",
                                 //"Bless you!",
                                 //"Is there an owl in here?",
                                 //"Is there an echo in here?",
                                 //"Don't cry, it is just a joke."};


    // TENTH STEP: DOCUMENTING THE PROCESSINPUT METHOD.
    /**
     * processInput - Processes the input that the user gives.
     * 
     * @param - String that the user enters. (Possibilities: Who's there?, y, n, (clues[currentJoke]) + who?)
     * @return - String that the server should send back based on the client response.
     */
    public String processInput(String theInput) 
    {
        String theOutput = null;
        // CSC 450 LAB 3: MODIFYING KNOCK KNOCK PROTOCOL SO THAT THE SERVER SAYS "Connection Established"
        // WHEN IN THE WAITING STATE.
        
        // CSC 450 LAB 3: ADDING BUFFERED READER SO WE CAN SEND MESSAGES ON THE SERVER SIDE AS WELL.
        BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
        if (state == WAITING) {
            theOutput = "Connection Established.";
            // The status of the IM is in the middle of a conversation when a connection is established.
            state = IN_CONVERSATION;
        } else if (state == IN_CONVERSATION) {
            if (theInput.equalsIgnoreCase("Bye")) 
            {
                // Make the server send an automated "Bye" when there is a "Bye" sent on the client side.
                theOutput = "Bye";
                // Make the status back to WAITING so protocol knows its not in conversation.
                state = WAITING;
                // try
                // {
                    // theOutput = stdIn.readLine();
                // }
                // catch (IOException ioe)
                // {
                    // ioe.printStackTrace();
                // }//"How are you?";
              
                    //} else {
                        //theOutput = //"You're supposed to say \"Who's there?\"! " +
                        //Try again. Knock! Knock!";
                    //}
                // } else if (state == SENTCLUE) {
                    // if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                        // theOutput = answers[currentJoke] + " Want another? (y/n)";
                        // state = ANOTHER;
                    // } else {
                        // theOutput = "You're supposed to say \"" + 
                        // clues[currentJoke] + 
                        // " who?\"" + 
                        // "! Try again. Knock! Knock!";
                        // state = IN_CONVERSATION;
                    // }
                // } else if (state == ANOTHER) {
                    // if (theInput.equalsIgnoreCase("y")) {
                        // theOutput = "Knock! Knock!";
                        // if (currentJoke == (NUMJOKES - 1))
                            // currentJoke = 0;
                        // else
                            // currentJoke++;
                        // state = IN_CONVERSATION;
                    // } else {
                        // theOutput = "Bye.";
                        // state = WAITING;
                    // }
                // }
            }
            else if (state == IN_CONVERSATION)
            {
                // Set space to show that its the servers turn to send message.
                // System.out.print("Server: ");
                try
                {
                    // Allow to send messages manually on server side, thanks to BufferedReader object.
                    theOutput = stdIn.readLine();
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }//"How are you?";
            }
        }
        // Reurn the string representing the sent message.
        return theOutput;
    }
}
