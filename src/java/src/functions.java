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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kanika
 */
public class functions {

    protected int userLogin(String emailid, String password) {
        String ret = "";
        try {
            // TODO add your handling code here:

            //SENDING TO PHP
            String url = "http://kanikabhatia-photos.com/Team_File_Share/userlogin.php?emailid=" + emailid + "&password=" + password;
            HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod("POST");
            OutputStream os = httpUrlConnection.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            String s = null;
            while ((s = in.readLine()) != null) {
                ret = ret + s;
            }
            in.close();

        } catch (IOException ex) {
            //Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (Integer.parseInt(ret) == 1) {
            writeusername(emailid);
        }
        return Integer.parseInt(ret);
    }

    protected String userRegistration(String data) {
        String retData = "";
        try {
            String array[] = data.split(",,");
            String firstname = array[0];
            String lastname = array[1];
            String emailid = array[2];
            String password = array[3];
            String url = "http://kanikabhatia-photos.com/Team_File_Share/reg2.php?firstname=" + firstname + "&lastname=" + lastname + "&emailid=" + emailid + "&password=" + password;
            HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod("POST");
            OutputStream os = httpUrlConnection.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            String s = null;
            while ((s = in.readLine()) != null) {
                if (s.equals("Error")) {
                    retData = s;
                } else if (s.equals("Success")) {
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
            String userid = readusername();
            String filePath = path;
            String fileName = fname;
            String url = "http://kanikabhatia-photos.com/Team_File_Share/uploads/tempfileupload.php?fname=" + fileName + "&userid=" + userid;
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
            }
            in.close();

        } catch (MalformedURLException ex) {
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retData;
    }

    protected String filelist() {
        String ret = "";
        try {
            int t = 0;
            String userid = readusername();
            String url = "http://kanikabhatia-photos.com/Team_File_Share/tempfilelist.php?userid=" + "kanikabhtia@gmail.com";
            HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod("POST");
            OutputStream os = httpUrlConnection.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            String s = "";
            while ((s = in.readLine()) != null) {
                ret = ret + s;
            }
            in.close();

        } catch (MalformedURLException ex) {
            System.out.println("Error here");
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error there");
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    protected String[] fileDownload() {
        String filelist = filelist();
        String[] userRequestedFileName = filelist.split(",");
        String fname;
        String path = "C:\\Users\\Kanika\\Documents\\My_Downloads\\";
        String userid = readusername();
        userid = "prateek.donni@gmail.com";
        fname = "testplan.pdf";
        try {
         String url = "http://kanikabhatia-photos.com/Team_File_Share/uploads/tempfiledownload.php?userid="+ userid +"&filename="+fname;
         //to be removed later
         HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
         httpUrlConnection.setDoOutput(true);
         httpUrlConnection.setRequestMethod("POST");
         OutputStream os = httpUrlConnection.getOutputStream();
         InputStream reader = httpUrlConnection.getInputStream();
         String s = "";
         fname = path+fname;
         FileWriter file = new FileWriter(fname);
         System.out.println("Reading the file");
         FileOutputStream writer = new FileOutputStream(fname);
        byte[] buffer = new byte[1000];
        int totalBytesRead = 0;
        int bytesRead = 0;
        while ((bytesRead = reader.read(buffer)) > 0)
        {  
            writer.write(buffer, 0, bytesRead);
            buffer = new byte[1000];
            totalBytesRead += bytesRead;
        }
        writer.close();
        reader.close();
        os.close();
          /*fname = path+fname;
         BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
         BufferedWriter buff = new BufferedWriter(new FileWriter(fname));
            String s = null;
            buff.write("");
            while ((s = in.readLine()) != null) {
                System.out.println(s);
                buff.append(s);
            }
            buff.close();
            in.close();*/
            
         } catch (MalformedURLException ex) {
         System.out.println("Error here");
         Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
         System.out.println("Error there");
         Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
         }
        return userRequestedFileName;
    }

    protected String readusername() {
        String username = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Temp\\userfile.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                username = line;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return username;
    }

    protected void writeusername(String name) {
        try {
            BufferedWriter buff = new BufferedWriter(new FileWriter("C:\\Temp\\userfile.txt"));
            buff.write(name);
            buff.close();
        } catch (IOException ex) {
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
