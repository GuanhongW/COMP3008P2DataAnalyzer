package bean;

public class UserRemeData {
    //map to generated file
    private String userid;
    private String userip;
    private String scheme;
    private int remember_time;
    private boolean isFinished;


    public void setFinished(boolean finished) {
        isFinished = finished;
    }


    public boolean isFinished() {
        return isFinished;
    }

    //getters setters constructors
    public UserRemeData() {
        remember_time = 0;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public String getUserip() {

        return userip;
    }

    public String getUserid() {
        return userid;
    }

    public String getScheme() {
        return scheme;
    }

    public int getRemember_time() {
        return remember_time;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setRemember_time(int remember_time) {
        this.remember_time = remember_time;
    }

    public void addRemember_time(int remember_time){
        this.remember_time += remember_time;
    }


    @Override
    public String toString() {
        return "\"" + userid + "\"," +
                "\"" + scheme + "\"," +
                "\"" + remember_time + "\"," +
                "\"" + isFinished +"\"";

    }
}
