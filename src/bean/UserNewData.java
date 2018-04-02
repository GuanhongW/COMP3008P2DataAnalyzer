package bean;

public class UserNewData {
    //map to generated file
    private String userid;
    private String userip;
    private String scheme1;
    private String scheme2;
    private String scheme3;
    private int total_login;
    private int success_login;
    private int fail_login;
    private int success_time_taken;
    private int fail_time_taken;
    private boolean isFinished;

    public void setFinished(boolean finished) {
        isFinished = finished;
    }



    //getters setters constructors
    public UserNewData() {
        total_login = 0;
        success_login = 0;
        fail_login = 0;
        success_time_taken = 0;
        fail_time_taken = 0;
        scheme1="";
        scheme2="";
        scheme3="";

    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public String getUserip() {

        return userip;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setScheme1(String scheme1) {
        this.scheme1 = scheme1;
    }

    public void setScheme2(String scheme2) {
        this.scheme2 = scheme2;
    }

    public void setScheme3(String scheme3) {
        this.scheme3 = scheme3;
    }

    public void setTotal_login(int total_login) {
        this.total_login = total_login;
    }

    public void setSuccess_login(int success_login) {
        this.success_login = success_login;
    }

    public void setFail_login(int fail_login) {
        this.fail_login = fail_login;
    }

    public void setSuccess_time_taken(int success_time_taken) {
        this.success_time_taken = success_time_taken;
    }

    public void setFail_time_taken(int fail_time_taken) {
        this.fail_time_taken = fail_time_taken;
    }

    public String getUserid() {
        return userid;
    }



    public int getTotal_login() {
        return total_login;
    }

    public int getSuccess_login() {
        return success_login;
    }

    public int getFail_login() {
        return fail_login;
    }

    public int getSuccess_time_taken() {
        return success_time_taken;
    }

    public int getFail_time_taken() {
        return fail_time_taken;
    }

    public String getScheme1() {
        return scheme1;
    }

    public String getScheme2() {
        return scheme2;
    }

    public String getScheme3() {
        return scheme3;
    }

    public void addTotalLogin(int num){
        total_login+=num;
    }
    public void addSuccessLogin(int num){
        success_login+=num;
    }
    public void addFailLogin(int num){
        fail_login+=num;
    }
    public void addSuccessTime(int num){
        success_time_taken+=num;
    }
    public void addFailTime(int num){
        fail_time_taken+=num;
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public String toString() {
        return "\"" + userid + "\"," +
                "\"" + scheme1 + "\"," +
                "\"" + scheme2 + "\"," +
                "\"" + scheme3 + "\"," +
                "\"" + total_login +

                "\",\"" + success_login +
                "\",\"" + fail_login +
                "\",\"" + success_time_taken +
                "\",\""+fail_time_taken+
                "\",\""+isFinished+"\"";
    }
}
