package com.example.kevinwu.planner;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Kevin Wu on 3/1/2016.
 */
public class TaskInfo implements Parcelable{
    private String task;
    private boolean isChecked;

    public TaskInfo()
    {
    }


    public TaskInfo(JSONObject jsonObject)
    {
        try {
            task = jsonObject.getString("Task");
            isChecked = jsonObject.getBoolean("CheckBox");
        }catch(JSONException e)
        {
            e.printStackTrace();
        }

    }


    public String getTask() {
        return task;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(task);
        out.writeByte((byte) (isChecked ? 1 : 0));
    }

    public static final Parcelable.Creator<TaskInfo> CREATOR = new Parcelable.Creator<TaskInfo>() {
        public TaskInfo createFromParcel(Parcel in) {
            return new TaskInfo();
        }

        public TaskInfo[] newArray(int size) {
            return new TaskInfo[size];
        }
    };

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("Task", task);
            obj.put("CheckBox", isChecked);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
