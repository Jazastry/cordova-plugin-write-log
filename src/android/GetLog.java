package cordova-plugin-get-log-to-file;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class GetLog extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getLog")) {
            String message = args.getString(0);
            this.getLog(message, callbackContext);
            return true;
        }
        return false;
    }

    private void getLog(String message, CallbackContext callbackContext) {
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
