package com.panda.cvsandroid.network.models.skillsAndJops;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jopitem implements Parcelable
{
    @SerializedName("job_id")
    @Expose
    private Integer jobId;
    @SerializedName("job_name")
    @Expose
    private String jobName;

    protected Jopitem(Parcel in)
    {
        if (in.readByte() == 0) {
            jobId = null;
        } else {
            jobId = in.readInt();
        }
        jobName = in.readString();
    }

    public static final Creator<Jopitem> CREATOR = new Creator<Jopitem>() {
        @Override
        public Jopitem createFromParcel(Parcel in) {
            return new Jopitem(in);
        }

        @Override
        public Jopitem[] newArray(int size) {
            return new Jopitem[size];
        }
    };

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (jobId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(jobId);
        }
        parcel.writeString(jobName);
    }
}
