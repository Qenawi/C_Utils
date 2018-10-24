package com.panda.cvsandroid.network.models.skillsAndJops;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class jops_skills_item implements Parcelable
{
    @SerializedName("skill_id")
    @Expose
    private Integer skillId;
    @SerializedName("skill_name")
    @Expose
    private String skillName;
    @SerializedName("jobs")
    @Expose
    private List<Jopitem> Jopitems = null;


    protected jops_skills_item(Parcel in) {
        if (in.readByte() == 0) {
            skillId = null;
        } else {
            skillId = in.readInt();
        }
        skillName = in.readString();
        Jopitems = in.createTypedArrayList(Jopitem.CREATOR);
    }

    public static final Creator<jops_skills_item> CREATOR = new Creator<jops_skills_item>() {
        @Override
        public jops_skills_item createFromParcel(Parcel in) {
            return new jops_skills_item(in);
        }

        @Override
        public jops_skills_item[] newArray(int size) {
            return new jops_skills_item[size];
        }
    };

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public List<Jopitem> getJopitems() {
        return Jopitems;
    }

    public void setJopitems(List<Jopitem> Jopitems) {
        this.Jopitems = Jopitems;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (skillId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(skillId);
        }
        parcel.writeString(skillName);
        parcel.writeTypedList(Jopitems);
    }
}
