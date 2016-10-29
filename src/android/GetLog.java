package com.jazastry.cordova.getlogfile;

import org.apache.cordova;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import android.os.Environment;
import java.io.IOException;
import java.lang.Runtime;

/**
 * This class echoes a string called from JavaScript.
 */
public class GetLog extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getLog")) {
            String message = args.getString(0);

            cordova.getThreadPool()
                .execute(new Runnable() {
                        public void run() {
                            this.getLog(callbackContext);
                        }
                    });

            this.getLog(callbackContext);
            return true;
        }
        return false;
    }

    private void getLog(CallbackContext callbackContext) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                String.valueOf(System.currentTimeMillis()));
            Runtime.getRuntime().exec("logcat -d -v time -f " + file.getAbsolutePath());
            callbackContext.success("Log file created successfully");
        } catch (IOException e){
            callbackContext.error("Error in creating log file.");
        }
    }
}
