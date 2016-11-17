//Add ChangelogFunc to the IMS Package
package IMS;

//Import necessary components
import java.io.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextArea;

/**
 * @author Gianfranco Cusmano
 */
public class ChangelogFunc 
{  
//Create a date format 
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
//Create first iteration of changelog function for file writing (buffered writer) for purpose of Changed items    
    public static void ChangelogFunc(String proID, String stOld, String stNew, String table, String col)
    {
       //Create an instance of a date for the changelog change 
       Date date = new Date();
       
       //attempt to make a buffered writer for a text file changelog.txt
       //this will also create a new changelog.txt file if it is not present already
       //if changelog.txt exists, it will be appended to.
       try (BufferedWriter out = new BufferedWriter(new FileWriter("changelog.txt", true)))
       {   
           //write the change to the changelog.txt file, given the date, and the old info and new info
            out.write("Change on " + df.format(date) + ": Changed item " + proID + " - " + stOld + " in " + table + "/" + col + " to " + stNew + ". ");
            //output a new line
            out.newLine();
       }
       //Catch an input outout exeception if the buffered writer was not able to be created for a changelog.txt file
       catch(IOException eW)
       {
           //Print out the BufferedWriter Error
            System.out.println("BufferedWriter Error: " + eW);
       }
    }
    
    //Create an overloaded method of the ChangelogFunc for the purpose of when a table entry is deleted.
    //  This is unused, its functionality is here for the purpose of if it was needed, however, it was decided
    //to leave in any table entries for history, rather than remove them from the table. 
    public static void ChangelogFunc(int proID, String stOld, Connection conn)
    {
       //Similar to the previous ChangelogFunc, create an instance of the date
       Date date = new Date();
       
       //attempt to create a bufferedwriter or a changelog.txt file
       try (BufferedWriter out = new BufferedWriter(new FileWriter("changelog.txt",true)))
       {   
           //output to the text file the change/delete given the item that was deleted and the delete time and date
            out.write("Change on " + df.format(date) + " - Deleted item" + proID  + " - " + stOld + ". ");
            
       }
       //catch any exception thrown by the bufferedwriter function.
       catch(IOException eW)
       {
           //print out the buffered writer error
            System.out.println("BufferedWriter Error: " + eW);
       }
    }
    
    //create an overloaded method for the purpose of using a bufferedreader, this will allow for 
    //reading from a text file and outputting the information that was read into a JTextArea
    public static void ChangelogFunc(JTextArea txtOutputCL)
    {
       //try to make an instance of a bufferedreader to read from a text file named changelog.txt if it exists
       try (BufferedReader in = new BufferedReader(new FileReader("changelog.txt")))
       {
           //read in the first line of the text file 
            String line = in.readLine();
            
            //while there is a next line to the text file
            while (line != null)
            {      
                //append and read the next line
                   txtOutputCL.append(String.format("%-50s\n",line));
                    line = in.readLine();
            }
            //close the input stream reading from the text file
            in.close();
       }
       //throw an exception if the bufferedreader failed
       catch(IOException eR)
       {
           System.out.println("BufferedReader Error: " + eR);
       }
    }
}
