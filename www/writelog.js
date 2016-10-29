var exec = require('cordova/exec');

module.exports = {
	write: function(success, error) {
		console.log('IN WRITE');
		exec(success, error, "WriteLog", "writeLog");
	}
};