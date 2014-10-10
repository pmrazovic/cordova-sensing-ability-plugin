package com.kth.scs.sensingability;

import java.util.TimeZone;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.hardware.*;
import java.util.*;

import android.provider.Settings;

public class SensingAbility extends CordovaPlugin {
    SensorManager Sensors;

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Sensors = (SensorManager) cordova.getActivity().getSystemService( Context.SENSOR_SERVICE );
        if (action.equals("getSensors")) {
            callbackContext.success(poolAllSensors());
        }
        else {
            return false;
        }
        return true;
    }

    public JSONArray poolAllSensors() {
        List<Sensor> SensorList = Sensors.getSensorList( Sensor.TYPE_ALL );
        JSONArray rtnJSON = new JSONArray();    
        for( Sensor s : SensorList ){
            JSONObject o = new JSONObject();
            try {
                o.put("vendor", s.getVendor());
                o.put("name", s.getName());
                o.put("type", checkType(s.getType()));
                o.put("version", s.getVersion());
                o.put("maxRange", s.getMaximumRange());
                o.put("power", s.getPower());
                o.put("resolution", s.getResolution());
                rtnJSON.put(o);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return rtnJSON;
    }

    public static String checkType(int type) {
        switch(type){
            case Sensor.TYPE_ACCELEROMETER :
                return "TYPE_ACCELEROMETER";
            case Sensor.TYPE_AMBIENT_TEMPERATURE :
                return "TYPE_AMBIENT_TEMPERATURE";
            case Sensor.TYPE_LIGHT :
                return "TYPE_LIGHT";
            case Sensor.TYPE_GRAVITY :
                return "TYPE_GRAVITY";
            case Sensor.TYPE_GYROSCOPE :
                return "TYPE_GYROSCOPE";
            case Sensor.TYPE_LINEAR_ACCELERATION :
                return "TYPE_LINEAR_ACCELERATION";
            case Sensor.TYPE_MAGNETIC_FIELD :
                return "TYPE_MAGNETIC_FIELD";
            case Sensor.TYPE_PRESSURE :
                return "TYPE_PRESSURE";
            case Sensor.TYPE_PROXIMITY :
                return "TYPE_PROXIMITY";
            case Sensor.TYPE_RELATIVE_HUMIDITY :
                return "TYPE_RELATIVE_HUMIDITY";
            case Sensor.TYPE_ROTATION_VECTOR :
                return "TYPE_ROTATION_VECTOR";
            case Sensor.TYPE_ORIENTATION :
                return "TYPE_ORIENTATION";
            case Sensor.TYPE_TEMPERATURE :
                return "TYPE_TEMPERATURE";
            default:
                return "Unknown";
        }
    }

}
