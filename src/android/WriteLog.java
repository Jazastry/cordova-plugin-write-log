package com.moust.cordova.writelog;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import android.os.Environment;
import java.io.IOException;
import java.lang.Runtime;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import android.net.Uri;

import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * This class echoes a string called from JavaScript.
 */
public class WriteLog extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args,final CallbackContext callbackContext) throws JSONException {
        final WriteLog plugin = this;
        if (action.equals("writeLog")) {            
            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    boolean err = false;

                    String VERSION = "API - " + android.os.Build.VERSION.SDK;
                    String MODEL = "Model - " + android.os.Build.MODEL;
                    String DEVICE_INFO_HEADER = " ::: " + VERSION + " ::: " + MODEL + " ::: " + System.getProperty("line.separator");

                    try {
                        File file = new File(Environment.getExternalStorageDirectory(),
                            "DW_STREAM." + String.valueOf(System.currentTimeMillis()) + ".log");

                        /*  write device info header */
                        FileOutputStream stream = new FileOutputStream(file);
                        try {
                            stream.write(DEVICE_INFO_HEADER.getBytes());
                        } finally {
                            stream.close();
                        }                        

                        Runtime.getRuntime().exec("logcat -d -v time -f " + file.getAbsolutePath());                        
                    } catch (IOException e){
                        err = true;
                    }

                    if (err) {
                        callbackContext.error("Error in creating log file.");
                    } else {
                        callbackContext.success("Log file created successfully");                                            
                    }
                }
            });
            return true;
        } else if (action.equals("sendMail")) {
            String email = args.getString(0);
            String subject = args.getString(1);
            String text = args.getString(2);
            plugin.sendEmail(email, subject, text);
            return true;
        }
        return false;
    }

    protected void sendEmail(String mail, String subject, String text) {
          Log.i("Send email", "");
          String[] TO = {mail};
          final Intent emailIntent = new Intent(Intent.ACTION_SEND);
          
          emailIntent.setData(Uri.parse("mailto:"));
          emailIntent.setType("text/plain");
          emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
          emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
          emailIntent.putExtra(Intent.EXTRA_TEXT, text);
          
          final WriteLog plugin = this;

          cordova.getThreadPool().execute(new Runnable() {
              public void run() {
                  cordova.startActivityForResult(plugin, Intent.createChooser(emailIntent, "Send mail..."), 0);
              }
          });
   }
}

