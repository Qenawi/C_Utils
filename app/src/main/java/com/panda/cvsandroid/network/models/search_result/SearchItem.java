package com.panda.cvsandroid.network.models.search_result;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

public class SearchItem implements Parcelable
{@SerializedName("employee_id")
@Expose
private Integer employeeId;
    @SerializedName("employee_name")
    @Expose
    private String employeeName;
    @SerializedName("skill_name")
    @Expose
    private String skillName;
    @SerializedName("job_name")
    @Expose
    private String jobName;
    @SerializedName("employee_bio")
    @Expose
    private String employeeBio;
    @SerializedName("employee_commission")
    @Expose
    private String employeeCommission;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;

    protected SearchItem(Parcel in) {
        if (in.readByte() == 0) {
            employeeId = null;
        } else {
            employeeId = in.readInt();
        }
        employeeName = in.readString();
        skillName = in.readString();
        jobName = in.readString();
        employeeBio = in.readString();
        employeeCommission = in.readString();
        profilePath = in.readString();
        byte tmpIs_Checked = in.readByte();
        Is_Checked = tmpIs_Checked == 0 ? null : tmpIs_Checked == 1;
    }

    public static final Creator<SearchItem> CREATOR = new Creator<SearchItem>() {
        @Override
        public SearchItem createFromParcel(Parcel in) {
            return new SearchItem(in);
        }

        @Override
        public SearchItem[] newArray(int size) {
            return new SearchItem[size];
        }
    };

    public Boolean getIs_Checked()
    {
        if (this.Is_Checked==null)
        {
            this.Is_Checked=false;
        }
        return Is_Checked;
    }

    public void setIs_Checked(Boolean is_Checked) {
        Is_Checked = is_Checked;
    }

    @Nullable
    private Boolean Is_Checked=false;




    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getEmployeeBio() {
        return employeeBio;
    }

    public void setEmployeeBio(String employeeBio) {
        this.employeeBio = employeeBio;
    }

    public String getEmployeeCommission() {
        return employeeCommission;
    }

    public void setEmployeeCommission(String employeeCommission) {
        this.employeeCommission = employeeCommission;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        if (employeeId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(employeeId);
        }
        parcel.writeString(employeeName);
        parcel.writeString(skillName);
        parcel.writeString(jobName);
        parcel.writeString(employeeBio);
        parcel.writeString(employeeCommission);
        parcel.writeString(profilePath);
        parcel.writeByte((byte) (Is_Checked == null ? 0 : Is_Checked ? 1 : 2));
    }


}
