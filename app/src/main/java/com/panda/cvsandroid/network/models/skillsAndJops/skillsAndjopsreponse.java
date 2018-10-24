package com.panda.cvsandroid.network.models.skillsAndJops;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class skillsAndjopsreponse implements Parcelable
{
    /*
    {
    "Status": "Success",
    "Message": "",
    "Data": [
        {
            "skill_id": 1,
            "skill_name": "Semi Skilled",
            "jobs": [
                {
                    "job_id": 4,
                    "job_name": "Tutor"
                },
                {
                    "job_id": 5,
                    "job_name": "Driver"
                },
                {
                    "job_id": 6,
                    "job_name": "Caregiver"
                }
            ]
        },
        {
            "skill_id": 2,
            "skill_name": "Skilled",
            "jobs": [
                {
                    "job_id": 11,
                    "job_name": "Acountant"
                },
                {
                    "job_id": 12,
                    "job_name": "Technician"
                }
            ]
        },
        {
            "skill_id": 3,
            "skill_name": "UnSkiiled",
            "jobs": [
                {
                    "job_id": 7,
                    "job_name": "House keeping"
                },
                {
                    "job_id": 8,
                    "job_name": "Cleaner"
                },
                {
                    "job_id": 9,
                    "job_name": "Helpers"
                },
                {
                    "job_id": 10,
                    "job_name": "Construction Worker"
                },
                {
                    "job_id": 13,
                    "job_name": "Clerk"
                }
            ]
        }
    ]
}
     */
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<jops_skills_item> data = null;


    public skillsAndjopsreponse(){};
    protected skillsAndjopsreponse(Parcel in) {
        status = in.readString();
        message = in.readString();
        data = in.createTypedArrayList(jops_skills_item.CREATOR);
    }

    public static final Creator<skillsAndjopsreponse> CREATOR = new Creator<skillsAndjopsreponse>() {
        @Override
        public skillsAndjopsreponse createFromParcel(Parcel in) {
            return new skillsAndjopsreponse(in);
        }

        @Override
        public skillsAndjopsreponse[] newArray(int size) {
            return new skillsAndjopsreponse[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<jops_skills_item> getData() {
        return data;
    }

    public void setData(List<jops_skills_item> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(status);
        parcel.writeString(message);
        parcel.writeTypedList(data);
    }
}
