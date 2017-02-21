//-----------------------------------------
// NAME		: chris Nkeshimana
// STUDENT NUMBER	: 7735006
// COURSE		: COMP 2150
// INSTRUCTOR	: Gord Boyer
// ASSIGNMENT	: assignment 1
// QUESTION	: question #
//
// REMARKS: Simulation of Mailing DVD's services
//
//
//-----------------------------------------

import java.io.*;
 
public class A1
{
  public static void main(String [] args)
  {
      Netflix account = new Netflix ();
      Event type = null;
      String name = "";
      String title = "";
      int number = 0;

    if(args.length == 0)
    {
        System.out.println("Usage: java A1 <input file>");
        System.exit(0);
    }

    BufferedReader inFile = File.openInputFile(args[0]);

    String line = File.getLine(inFile);

    while(line != null)
    {
        String [] tokens = line.trim().split ("\\s+");

        if (tokens[0].equals("ACN") || tokens[0].equals("ACQ"))
        {
            type = twoCommand(tokens, title, number);
            type.processEvent(account);
        }
        else if (tokens[0].equals("ADD") || tokens[0].equals("REM"))
        {
            type = fourCommand(tokens, name, title);
            type.processEvent(account);
        }
        else if (tokens[0].equals("RET") || tokens[0].equals("LOS" ))
        {
            type = retLosCommand(tokens, name, title);
            type.processEvent(account);
        }
        else if (tokens[0].equals("RCN"))
        {
            type = oneCommand(tokens, name);
            type.processEvent(account);
        }

        line = File.getLine(inFile);
    }// while

      System.out.println ("**********PRINTING ALL CUSTOMERS**********");

      account.getCustomerList().printList();

      File.closeFile(inFile);

      System.out.println("\n\nEnd of processing.");

      System.exit(0);
  }// main


    //For ACN and ACQ
    public static Event twoCommand (String[] tokens, String title, int number)
    {
        Event result = null;

        assert tokens != null && title != null : "Error: two command input";
        if (tokens != null && title != null)
        {
            number = Integer.parseInt(tokens[1]);
            for (int i = 2; i < tokens.length ; i++)
            {
                title += tokens[i]+ " ";
            }
            switch (tokens[0])
            {
                case "ACN":
                    result = new acnEvent(title, number);
                    break;
                case "ACQ":
                    result = new acqEvent(title, number);
                    break;
            }
        }
        return result;
    }

    //For RET, LOS
    public static Event retLosCommand (String[] tokens, String name, String title)
    {
        Event result = null;

        int j = 0;
        assert tokens != null && name != null && title != null : "Error four command";
        for (int i = 1; i < tokens.length; i++)
        {
            if ( !tokens[i].equals("FROM") && j == 0)
                title += tokens[i] + " ";
            else if ( !tokens[i].equals("FROM") && j > 0)
                name += tokens[i] + " ";
            else
                j++;
        }
        switch (tokens[0])
        {
            case "RET":
                result = new retEvent(name, title);
                break;

            case "LOS":
                result = new lostEvent(name, title);
                break;
        }

        return result;
    }

    //For ADD, REM.
    public static Event fourCommand (String[] tokens, String name, String title)
    {
        Event result = null;

        int j = 0;
        assert tokens != null && name != null && title != null : "Error four command";
        for (int i = 1; i < tokens.length; i++)
        {
            if ( !tokens[i].equals("FOR") && j == 0)
                title += tokens[i] + " ";
            else if ( !tokens[i].equals("FOR") && j > 0)
                name += tokens[i] + " ";
            else
                j++;
        }
        switch (tokens[0])
        {
            case "ADD":
                result = new addEvent(name, title);
                break;
            case "REM":
                result = new remEvent(name, title);
                break;
        }

        return result;
    }


    //For RCN
    public static Event oneCommand (String[] tokens, String name)
    {
        Event result = null;
        assert tokens != null && name != null : "Error: one command input";
        if (tokens != null && name != null)
        {
            for (int i = 1; i < tokens.length ; i++)
            {
                name += tokens[i] + " ";
            }
            switch (tokens[0])
            {
                case "RCN":
                    result = new rcnEvent(name);
                    break;
            }
        }
        return result;
    }



}// class A1

