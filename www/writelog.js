var exec = require('cordova/exec');

exports.write = function(arg0, success, error) {
    exec(success, error, "WriteLog", "writeLog", [arg0]);
};
