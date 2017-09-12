package org.chzz.demo.model;

/**
 * Created by copy on 2017/9/12.
 */

public class LoginEntity extends BaseEntity {

    /**
     * code : 0
     * msg : {"hospitalId":1,"id":1,"cookie":"48C4AA7307349271676FDB1AF42C0E55","managerTypeCode":"0","managerTypeValue":"","hospitalName":"四川大学华西医院","token":"","url":""}
     */

    private MsgBean msg;

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * hospitalId : 1
         * id : 1
         * cookie : 48C4AA7307349271676FDB1AF42C0E55
         * managerTypeCode : 0
         * managerTypeValue :
         * hospitalName : 四川大学华西医院
         * token :
         * url :
         */

        private int hospitalId;
        private int id;
        private String cookie;
        private String managerTypeCode;
        private String managerTypeValue;
        private String hospitalName;
        private String token;
        private String url;

        public int getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCookie() {
            return cookie;
        }

        public void setCookie(String cookie) {
            this.cookie = cookie;
        }

        public String getManagerTypeCode() {
            return managerTypeCode;
        }

        public void setManagerTypeCode(String managerTypeCode) {
            this.managerTypeCode = managerTypeCode;
        }

        public String getManagerTypeValue() {
            return managerTypeValue;
        }

        public void setManagerTypeValue(String managerTypeValue) {
            this.managerTypeValue = managerTypeValue;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
