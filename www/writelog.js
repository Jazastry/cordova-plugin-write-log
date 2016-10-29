var exec = require('cordova/exec');

module.exports = {
	write: function(arg0, success, error) {
		console.log('IN WRITE');
		exec(success, error, "WriteLog", "writeLog", [arg0]);
	}
};