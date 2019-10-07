package com.cuci.enticement.bean;

import java.io.Serializable;

public class HxBean implements Serializable {

    /**
     * code : 1
     * info : 环信注册成功
     * data : {"error":"duplicate_unique_property_exists","timestamp":1570431813174,"duration":0,"exception":"org.apache.usergrid.persistence.exceptions.DuplicateUniquePropertyExistsException","error_description":"Application 90f426d0-cfb9-11e9-9c82-ed4022d4af8d Entity user requires that property named username be unique, value of 18588564260 exists"}
     */

    private int code;
    private String info;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * error : duplicate_unique_property_exists
         * timestamp : 1570431813174
         * duration : 0
         * exception : org.apache.usergrid.persistence.exceptions.DuplicateUniquePropertyExistsException
         * error_description : Application 90f426d0-cfb9-11e9-9c82-ed4022d4af8d Entity user requires that property named username be unique, value of 18588564260 exists
         */

        private String error;
        private long timestamp;
        private int duration;
        private String exception;
        private String error_description;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getException() {
            return exception;
        }

        public void setException(String exception) {
            this.exception = exception;
        }

        public String getError_description() {
            return error_description;
        }

        public void setError_description(String error_description) {
            this.error_description = error_description;
        }
    }
}
