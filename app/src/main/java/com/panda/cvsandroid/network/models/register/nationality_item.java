package com.panda.cvsandroid.network.models.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class nationality_item
{
    @SerializedName("nationality_id")
    @Expose
    private Integer nationalityId;
    @SerializedName("nationality_name")
    @Expose
    private String nationalityName;

    public Integer getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }
}
