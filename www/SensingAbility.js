var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

function SensingAbility() {
    this.available = false;
    this.sensors = null;

    var me = this;

    channel.onCordovaReady.subscribe(function() {
        me.getSensors(function(result) {
            me.available = true;
            me.sensors = result;
        },function(e) {
            me.available = false;
            utils.alert("[ERROR] Error initializing Cordova: " + e);
        });
    });
}

SensingAbility.prototype.getSensors = function(successCallback, errorCallback) {
    exec(successCallback, errorCallback, "SensingAbility", "getSensors", []);
};

module.exports = new SensingAbility();
