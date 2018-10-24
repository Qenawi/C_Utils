package com.panda.cvsandroid.network.models.Contracttype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class contract_item
{
    @SerializedName("job_type_id")
    @Expose
    private Integer jobTypeId;
    @SerializedName("job_type_name")
    @Expose
    private String jobTypeName;

    public Integer getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(Integer jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public String getJobTypeName() {
        return jobTypeName;
    }

    public void setJobTypeName(String jobTypeName) {
        this.jobTypeName = jobTypeName;
    }

}
