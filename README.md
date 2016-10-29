# cordova-plugin-write-log


Write Log plugin for Cordova
========================================

A Codova plugin that simply allows you to write log to the Android device memory.


# Installation

This plugin use the Cordova CLI's plugin command. To install it to your application, simply execute the following.

```
cordova plugin add cordova-plugin-write-log
```


# Using

Just call the  `write` method.

```
cordova.plugins.WriteLog.write([string argument], [completeCallback], [errorCallback]);
```

The plugin is adding API level and device model as first row to the log file.

## Example

```javascript
cordova.plugins.WriteLog.write(function(mes) {
    alert(JSON.stringify(mes));            
}, function(err) {
    alert(JSON.stringify(err));
});
```

# Licence MIT

Copyright 2013 Quentin Aupetit

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.