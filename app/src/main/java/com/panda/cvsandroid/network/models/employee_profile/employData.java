package com.panda.cvsandroid.network.models.employee_profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class employData
{
    @SerializedName("employee_id")
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
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("employee_commission")
    @Expose
    private String employeeCommission;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    @SerializedName("video_path")
    @Expose
    private String videoPath;
    @SerializedName("cv_path")
    @Expose
    private String cvPath;
    @SerializedName("photos")
    @Expose
    private List<String> photos = null;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeCommission() {
        return employeeCommission;
    }

    public void setEmployeeCommission(String employeeCommission) {
        this.employeeCommission = employeeCommission;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getCvPath() {
        return cvPath;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
