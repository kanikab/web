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

/**
 *
 * @author Kanika
 */
public class functions {

    String userid = "";
    protected int userLogin(String emailid, String password) {
        String ret = "";
        try {
            // TODO add your handling code here:

            //SENDING TO PHP
            String fname = "login.txt";
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
        if(Integer.parseInt(ret) == 1)
        {
            userid = emailid;
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
            System.out.println("firstname "+firstname);
            //String url = "http://kanikabhatia-photos.com/Team_File_Share/register.php?fname=" + fname;
            String url = "http://kanikabhatia-photos.com/Team_File_Share/reg2.php?firstname=" + firstname + "&lastname=" + lastname + "&emailid=" + emailid +  "&password=" + password;
            HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod("POST");
            OutputStream os = httpUrlConnection.getOutputStream();
            /*File fileRead = new File(fileName);
            int bytes = (int) fileRead.length();
            BufferedInputStream fos = new BufferedInputStream(new FileInputStream(fileName));
            for (int j = 0; j < bytes; j++) {
                os.write(fos.read());
            }
            os.close();*/
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
            String filePath = path;
            String fileName = fname;
            //String url = "http://kanikabhatia-photos.com/Team_File_Share/uploads/fileUpload.php?fname=" + fileName;
            String url = "http://kanikabhatia-photos.com/Team_File_Share/tempfileupload.php?fname=" + fileName + "&userid=" + userid;
            url = "http://kanikabhatia-photos.com/Team_File_Share/uploads/tempfileupload.php?fname=" + fileName + "&userid=" + "kanika@test.com";
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

    protected String filelist()
    {
        String ret = "";
        try {
            //String url = "http://kanikabhatia-photos.com/Team_File_Share/uploads/fileUpload.php?fname=" + fileName;
            String url = "http://kanikabhatia-photos.com/Team_File_Share/tempfilelist.php?userid="+ userid;
            //to be removed later" kanika
            url = "http://kanikabhatia-photos.com/Team_File_Share/tempfilelist.php?userid="+ "kanika@test.com";
            HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod("POST");
            OutputStream os = httpUrlConnection.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            String s = "";
            while ((s = in.readLine()) != null) {
                ret = ret +s;
                //System.out.println(s);
            }
            in.close();
            
        } catch (MalformedURLException ex) {
            System.out.println("Error here");
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error there");
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("ret:"+ret);
        return ret;
    }

   protected String[] fileDownload()
   {
       String filelist = filelist();
       System.out.println("filelist: "+filelist);
       String[] userRequestedFileName = filelist.split(",");
       System.out.println("Array"+userRequestedFileName);
     
        /*try {
            //String url = "http://kanikabhatia-photos.com/Team_File_Share/uploads/fileUpload.php?fname=" + fileName;
            String url = "http://kanikabhatia-photos.com/Team_File_Share/tempfiledownload.php?userid="+ userid +"&filename="+userRequestedFileName;
            //to be removed later
            url = "http://kanikabhatia-photos.com/Team_File_Share/uploads/tempfiledownload.php?userid="+ "kanika@test.com" +"&filename="+"test.html";
            HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod("POST");
            OutputStream os = httpUrlConnection.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            String s = "";
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }
            in.close();
            
        } catch (MalformedURLException ex) {
            System.out.println("Error here");
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error there");
            Logger.getLogger(functions.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return userRequestedFileName;
   }
}
    
   
