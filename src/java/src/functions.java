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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kanika
 */
public class functions {

    String userid = "";
    protected int userLogin(String username, String password) {
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
            String url = "http://kanikabhatia-photos.com/Team_File_Share/userlogin.php?fname=" + fname;
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
        if(Integer.parseInt(ret) != 0)
        {
            userid = username;
        }
        return Integer.parseInt(ret);
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
            //String url = "http://kanikabhatia-photos.com/Team_File_Share/register.php?fname=" + fname;
            String url = "http://kanikabhatia-photos.com/Team_File_Share/registration1.php?fname=" + fname;
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
                if (s.contains("Error")) {
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

    protected String fileUpload(String path, String fname) {
        String retData = "";
        try {
            String filePath = path;
            String fileName = fname;
            //fileName = fileName+"..."+userid;
            //fileName = fileName+".."+"kanika";
            //String url = "http://kanikabhatia-photos.com/Team_File_Share/uploads/fileUpload.php?fname=" + fileName;
            String url = "http://kanikabhatia-photos.com/Team_File_Share/tempfileupload.php?fname=" + fileName;
            HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod("POST");
            OutputStream os = httpUrlConnection.getOutputStream();
            File fileRead = new File(filePath);
            int bytes = (int) fileRead.length();
            BufferedInputStream fos = new BufferedInputStream(new FileInputStream(filePath));
            for (int j = 0; j < bytes; j++) {
                os.write(fos.read());
            }
            os.close();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            String s = null;
            while ((s = in.readLine()) != null) {
                retData = s;
                System.out.println(s);
            }
            in.close();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retData;
    }
    
   
}
