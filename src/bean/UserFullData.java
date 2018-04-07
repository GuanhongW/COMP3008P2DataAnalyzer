package bean;

/*
    The Object of UserFullData
    UserFullData means the data from the Logging file. One line one data.
 */
import java.util.Date;

public class UserFullData {
    //map to each part in log file
    private Date dateTime;
    private String userid;
    private String action;
    private String data1;
    private String data2;
    private int linenum;
    //getters setters constructors
    public UserFullData(int linenum) {
        this.linenum = linenum;

    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getUserid() {
        return userid;
    }

    public String getAction() {
        return action;
    }

    public String getData1() {
        return data1;
    }

    public String getData2() {
        return data2;
    }

    public int getLinenum() {
        return linenum;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }
}
