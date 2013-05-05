/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

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
public class functions {

    protected String userLogin(String username, String password) {
        String ret = "";
        try {
            // TODO add your handling code here:

            //WRITING DATA TO A FILE ON LOCAL MACHINE
            String fileName = "C:\\Temp\\login.txt";
            File file = new File(fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(username + ",," + password);
            bw.close();
            // CLOSED

            //READING THE FILE AND SENDING TO PHP
            String fname = "login.txt";
            String url = "http://kanikabhatia-photos.com/Team_File_Share/login.php?fname=" + fname;
            //String url = "http://donniprateek.com/kanika.php?fname="+ fname;
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
                ret = ret + s;
            }
            in.close();

        } catch (IOException ex) {
            //Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    protected String userRegistration(String data) {
        String retData = "";
        try {
            String fileName = "C:\\Temp\\register.txt";
            File file = new File(fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(data);
            bw.close();
            // CLOSED
            //READING THE FILE AND SENDING TO PHP
            String fname = "register.txt";
            String url = "http://kanikabhatia-photos.com/Team_File_Share/register.php?fname=" + fname;
            //String url = "http://donniprateek.com/kanika.php?fname=" + fname;
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
            BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            String s = null;
            while ((s = in.readLine()) != null) {
                if (s.contains("error")) {
                    retData = s;
                    
                } else if (s.contains("Success")) {
                    retData = s;
                }
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retData;
    }
}
