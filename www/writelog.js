var exec = require('cordova/exec');

module.exports = {
	write: function(arg0, success, error) {
		console.log('IN WRITE');
		exec(success, error, "WriteLog", "writeLog", [arg0]);
	},
	openFilledEmailClient: function(email, subject, text, success, error) {
		console.log('IN EMAIL PREPARE');
		exec(success, error, "WriteLog", "sendMail", [email, subject, text]);
	}
};