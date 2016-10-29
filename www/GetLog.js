var exec = require('cordova/exec');

module.exports = {
    getLog: function(arg0, success, error) {
        exec(success, error, "GetLog", "getLog", [arg0]);
    }
};
