var exec = require('cordova/exec');

exports.getLog = function(arg0, success, error) {
    exec(success, error, "GetLog", "getLog", [arg0]);
};
