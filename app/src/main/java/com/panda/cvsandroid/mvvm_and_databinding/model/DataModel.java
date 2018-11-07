package com.panda.cvsandroid.mvvm_and_databinding.model;

import io.reactivex.annotations.Nullable;


    public class DataModel
    {
        private String title;

        public DataModel() {
        }

        @Nullable
        public String getTitle() {
            return title;
        }

        public void setTitle(@Nullable String title) {
            this.title = title;
        }
    }

