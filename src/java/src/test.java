/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.applet.Applet;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kanika
 */
public class test extends Applet{
   public String test1(String datas) 
   {
         //WRITING DATA TO A FILE ON LOCAL MACHINE
       try{
            String data = datas;
            if (data.equalsIgnoreCase("success")) {

                String fileName = "C:\\Temp\\pass.txt";
                File file = new File(fileName);
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write(data);
                bw.close();
                // CLOSED

                //READING THE FILE AND SENDING TO PHP
                String fname = "pass.txt";
                String url = "http://kanikabhatia-photos.com/Team_File_Share/login.php?fname=" + fname;
                HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
                httpUrlConnection.setDoOutput(true);
                httpUrlConnection.setRequestMethod("POST");
                OutputStream os = httpUrlConnection.getOutputStream();
                File fileRead = new File(fileName);
                int bytes = (int) fileRead.length();
                BufferedInputStream fos = new BufferedInputStream(new FileInputStream(fileName));
                for (int j = 0; j < bytes; j++) {
                    os.write(fos.read());
                }
                os.close();
                //CLOSED
                BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
                String s = null;
                while ((s = in.readLine()) != null) {
                    if(s.contains("error"))
                    {
                        String msg = "Could not complete registration. \n Try Again..!!";
                        System.out.println(msg);
                    }
                    else if(s.contains("Success"))
                    {
                        System.out.println("Successfully Registered!! login with the user id an password");
                        
                    }
                }
                in.close();
            }
   }catch (IOException ex) {
           Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
       return "hello";
   }
}
