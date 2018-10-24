package com.panda.cvsandroid.network.models.Contracttype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class contract_type_response
{




        @SerializedName("Status")
        @Expose
        private String status;
        @SerializedName("Message")
        @Expose
        private String message;
        @SerializedName("Data")
        @Expose
        private List<contract_item> data = null;

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

        public List<contract_item> getData() {
            return data;
        }

        public void setData(List<contract_item> data) {
            this.data = data;
        }

    }
