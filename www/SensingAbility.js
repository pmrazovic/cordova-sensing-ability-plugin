var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

function SensingAbility() {
    this.available = false;
    this.sensorTypes = null;

    var me = this;

    channel.onCordovaReady.subscribe(function() {
        me.getSensorTypes(function(result) {
            me.available = true;
            me.sensorTypes = result;
        },function(e) {
            me.available = false;
            utils.alert("[ERROR] Error initializing Cordova: " + e);
        });
    });
}

SensingAbility.prototype.getSensorTypes = function(successCallback, errorCallback) {
    exec(successCallback, errorCallback, "SensingAbility", "getSensorTypes", []);
};

module.exports = new SensingAbility();
