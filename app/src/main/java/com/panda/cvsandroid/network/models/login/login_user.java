package com.panda.cvsandroid.network.models.login;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class login_user implements Parcelable
{
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("nationality_id")
    @Expose
    private Integer nationalityId;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("user_address")
    @Expose
    private String userAddress;
    @SerializedName("user_box")
    @Expose
    private Integer userBox;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("trade_license")
    @Expose
    private int tradeLicense;
    @SerializedName("allowed_lang_id")
    @Expose
    private Integer allowedLangId;

    public login_user(){}
    protected login_user(Parcel in) {
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        firstName = in.readString();
        lastName = in.readString();
        if (in.readByte() == 0) {
            nationalityId = null;
        } else {
            nationalityId = in.readInt();
        }
        mobileNumber = in.readString();
        phoneNumber = in.readString();
        userAddress = in.readString();
        if (in.readByte() == 0) {
            userBox = null;
        } else {
            userBox = in.readInt();
        }
        email = in.readString();
        if (in.readByte() == 0) {
            countryId = null;
        } else {
            countryId = in.readInt();
        }
        userType = in.readString();
        companyName = in.readString();
        tradeLicense = in.readInt();
        if (in.readByte() == 0) {
            allowedLangId = null;
        } else {
            allowedLangId = in.readInt();
        }
    }

    public static final Creator<login_user> CREATOR = new Creator<login_user>() {
        @Override
        public login_user createFromParcel(Parcel in) {
            return new login_user(in);
        }

        @Override
        public login_user[] newArray(int size) {
            return new login_user[size];
        }
    };

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getUserBox() {
        return userBox;
    }

    public void setUserBox(Integer userBox) {
        this.userBox = userBox;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getTradeLicense() {
        return tradeLicense;
    }

    public void setTradeLicense(int tradeLicense) {
        this.tradeLicense = tradeLicense;
    }

    public Integer getAllowedLangId() {
        return allowedLangId;
    }

    public void setAllowedLangId(Integer allowedLangId) {
        this.allowedLangId = allowedLangId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (userId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(userId);
        }
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        if (nationalityId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(nationalityId);
        }
        parcel.writeString(mobileNumber);
        parcel.writeString(phoneNumber);
        parcel.writeString(userAddress);
        if (userBox == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(userBox);
        }
        parcel.writeString(email);
        if (countryId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(countryId);
        }
        parcel.writeString(userType);
        parcel.writeString(companyName);
        parcel.writeInt(tradeLicense);
        if (allowedLangId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(allowedLangId);
        }
    }
}
