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

/**
 * This class echoes a string called from JavaScript.
 */
public class WriteLog extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args,final CallbackContext callbackContext) throws JSONException {
        if (action.equals("writeLog")) {
            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    boolean err = false;

                    try {
                        File file = new File(Environment.getExternalStorageDirectory(),
                            String.valueOf(System.currentTimeMillis()) + ".log");
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
        }
        return false;
    }

    private void writeLog(CallbackContext callbackContext) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                String.valueOf(System.currentTimeMillis()));
            Runtime.getRuntime().exec("logcat -d -v time -f " + file.getAbsolutePath());
            callbackContext.success("Log file created successfully");
        } catch (IOException e){
            callbackContext.error("Error in creating log file.");
        }
    }

    protected void sendEmail() {
          Log.i("Send email", "");
          String[] TO = {"jazastry@gmail.com"};
          final Intent emailIntent = new Intent(Intent.ACTION_SEND);
          
          emailIntent.setData(Uri.parse("mailto:"));
          emailIntent.setType("text/plain");
          emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
          emailIntent.putExtra(Intent.EXTRA_SUBJECT, "DW streaming app debug log");
          emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
          
          // try {
          //    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
          //    finish();
          //    Log.i("Finished sending email...", "");
          // }
          // catch (android.content.ActivityNotFoundException ex) {
          //    Toast.makeText(WriteLog.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
          // }

          final EmailComposer plugin = this;

          cordova.getThreadPool().execute(new Runnable() {
              public void run() {
                  cordova.startActivityForResult(plugin, Intent.createChooser(emailIntent, "Send mail..."), 0);
              }
          });
       }
}
