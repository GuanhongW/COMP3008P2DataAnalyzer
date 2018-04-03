import bean.UserFullData;
import bean.UserNewData;
import bean.UserRemeData;
import util.FileIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class Main {

//    private static ArrayList<UserNewData> combineData (ArrayList<UserNewData> datalist) {
//        ArrayList<UserNewData> combined_list = new ArrayList<>();
//        //set of uid and index of the index in combined_data
//        Hashtable<String, Integer> uid_set = new Hashtable<>();
//        int index = 0;
//        for (UserNewData data : datalist) {
//            if (!uid_set.containsKey(data.getUserid())) {
//                //first time see the uid(aka the list does not contain the object with the same uid)
//                uid_set.put(data.getUserid(), index++);
//                combined_list.add(data);
//            } else {
//                //not first time see the uid
//                //add all data together
//                combined_list.get(uid_set.get(data.getUserid())).addTotalLogin(data.getTotal_login());
//                combined_list.get(uid_set.get(data.getUserid())).addFailLogin(data.getFail_login());
//                combined_list.get(uid_set.get(data.getUserid())).addFailTime(data.getFail_time_taken());
//                combined_list.get(uid_set.get(data.getUserid())).addSuccessLogin(data.getSuccess_login());
//                combined_list.get(uid_set.get(data.getUserid())).addSuccessTime(data.getSuccess_time_taken());
//            }
//        }
//        return combined_list;
//    }



    public static void main(String[] args) throws IOException {
        /**
         * Format:
         * "userid","password scheme","total number of login","successful login","fail login","time taken(success)","time taken(fail)"
         *
         */
        String[] filenames = {"tmp.csv"};

        //Imagept21 BELOW


        //load all user data
        ArrayList<UserFullData> rawdata = FileIO.readData(filenames[0]);
        ArrayList<UserFullData> userFullData = new ArrayList<>();
        //remove data that Event is not enter
        for (int i = 0; i < rawdata.size(); i++) {
                userFullData.add(rawdata.get(i));
        }

        ArrayList<UserNewData> newdatalist = new ArrayList<>();
        ArrayList<UserRemeData> remedatalist = new ArrayList<>();
        UserNewData userNewData = new UserNewData();
        UserRemeData userRemeData = new UserRemeData();
        Date start_time = null;
        int time_consume = 0;


        for (int i = 0; i<userFullData.size(); i++) {
            switch (userFullData.get(i).getAction()) {
                case "Login":
                    //when the user start enter their password
                    userRemeData.setUserid(userFullData.get(i).getUserid());
                    userRemeData.setUserip(userFullData.get(i).getData1());
                    break;
                case "START":
                    userNewData = new UserNewData();
                    userRemeData = new UserRemeData();
                    start_time = userFullData.get(i).getDateTime();
                    time_consume = 0;
                    if (remedatalist.size()>1) {
                        if (userFullData.get(i).getUserid().equals(remedatalist.get(remedatalist.size() - 1).getUserid())) {
                            for (int j = 0; j < remedatalist.size(); j++) {
                                if (remedatalist.get(j).getUserid().equals(userFullData.get(i).getUserid())) {
                                    remedatalist.remove(remedatalist.get(j));
                                    --j;
                                }
                            }
                            for (int j = 0; j < newdatalist.size(); j++) {
                                if (newdatalist.get(j).getUserid().equals(userFullData.get(i).getUserid())) {
                                    newdatalist.remove(newdatalist.get(j));
                                    --j;
                                }
                            }
                        }
                    }
                    break;
//                case "REQUESTPASS":
//                    try {
//                        time_consume = (int) (userFullData.get(i).getDateTime().getTime() - start_time.getTime()) / 1000;
//                    } catch (NullPointerException e) {
//                        System.out.printf("Missing start tag at line: %d\n", userFullData.get(i).getLinenum() + 1);
//                        break;
//                    }
//                    userRemeData.setRemember_time(time_consume);
//                    remedatalist.add(userRemeData);
//
//                    userRemeData = new UserRemeData();
//                    start_time = userFullData.get(i).getDateTime();
//                    time_consume = 0;
//                    break;
                case "SETSCHEME":
                    userRemeData.setUserid(userFullData.get(i).getUserid());
                    userRemeData.setScheme(userFullData.get(i).getData1());
                    if (userFullData.get(i).getData1().equals(userFullData.get(i-2).getData1())){
                        break;
                    }
                    start_time = userFullData.get(i).getDateTime();
                    break;
                case "CONFIRM":
                    if (userFullData.get(i-1).getAction().equals("RESULT")){
                        break;
                    }
                    try {
                        time_consume = (int) (userFullData.get(i).getDateTime().getTime() - start_time.getTime()) / 1000;
                    } catch (Exception e) {
                        System.out.printf("Missing start tag at line: %d\n", userFullData.get(i).getLinenum() + 1);
                        break;
                    }
                    userRemeData.setRemember_time(time_consume);
                    //userRemeData.addRemember_time(time_consume);
                    remedatalist.add(userRemeData);

                    userRemeData = new UserRemeData();
                    start_time = null;
                    time_consume = 0;
                    break;
                case "VERIFY":
                    if (userNewData.getTotal_login() == 0) {
                        if (!userNewData.getScheme1().equals(userFullData.get(i).getData1())) {
                            userNewData.setScheme1(userFullData.get(i).getData1());
                            start_time = userFullData.get(i).getDateTime();
                            userNewData.setUserid(userFullData.get(i).getUserid());
                        }
                    }
                    else if (userNewData.getTotal_login() == 1){
                        if (!userNewData.getScheme2().equals(userFullData.get(i).getData1())) {
                            userNewData.setScheme2(userFullData.get(i).getData1());
                            start_time = userFullData.get(i).getDateTime();
                            userNewData.setUserid(userFullData.get(i).getUserid());
                        }
                    }
                    else {
                        if (!userNewData.getScheme3().equals(userFullData.get(i).getData1())) {
                            userNewData.setScheme3(userFullData.get(i).getData1());
                            start_time = userFullData.get(i).getDateTime();
                            userNewData.setUserid(userFullData.get(i).getUserid());
                        }
                    }
                    break;
                case "RESULT":
                    if (userFullData.get(i).getData1().equals("Fail")){
                        userNewData.addFailLogin(1);
                        userNewData.addTotalLogin(1);
                        time_consume = (int) (userFullData.get(i).getDateTime().getTime() - start_time.getTime()) / 1000;
                        userNewData.addFailTime(time_consume);
                        if (userNewData.getTotal_login() == 3){
                            newdatalist.add(userNewData);
                            userNewData = new UserNewData();
                            start_time = userFullData.get(i).getDateTime();
                            time_consume = 0;
                            break;
                        }
                        start_time = userFullData.get(i).getDateTime();
                        time_consume = 0;
                        break;
                    }
                    else {
                        userNewData.addSuccessLogin(1);
                        userNewData.addTotalLogin(1);
                        time_consume = (int) (userFullData.get(i).getDateTime().getTime() - start_time.getTime()) / 1000;
                        userNewData.addSuccessTime(time_consume);
                        newdatalist.add(userNewData);
                        userNewData = new UserNewData();
                        start_time = null;
                        time_consume = 0;
                        break;
                    }
                case "FINISH":
                    if (userFullData.get(i-1).getAction().equals("FINISH")){
                        break;
                    }
                    for (int j=0; j<remedatalist.size(); j++){
                        if (remedatalist.get(j).getUserid().equals(userFullData.get(i).getUserid())){
                            System.out.println(i);
                            remedatalist.get(j).setFinished(true);
                        }
                    }
                    for (int j=0; j<newdatalist.size(); j++){
                        if (newdatalist.get(j).getUserid().equals(userFullData.get(i).getUserid())){
                            newdatalist.get(j).setFinished(true);
                        }
                    }
                default:
                    break;
            }

        }

//        //final version of new user data
//        ArrayList<UserNewData> combined_new_user_data_image = combineData(newdatalist);
//        //Imagept21 UP
//
//
//
//        //Text21 Below
//        //load all user data
//        rawdata = FileIO.readData(filenames[1]);
//        userFullData = new ArrayList<>();
//        //remove data that Event is not enter
//        for (int i = 0; i < rawdata.size(); i++) {
//            if (rawdata.get(i).getMode().equals("enter") | rawdata.get(i).getMode().equals("login")) {
//                userFullData.add(rawdata.get(i));
//            }
//        }
//        newdatalist = new ArrayList<>();
//        start_time = null;
//        time_consume = 0;
//        for (UserFullData data : userFullData) {
//            if (data.getMode().equals("enter")) {
//                if (data.getEvent().equals("start")) {
//                    //start enter password
//                    start_time = data.getDateTime();
//                } else if (data.getEvent().equals("passwordSubmitted")) {
//                    //finish enter password
//                    time_consume += (int) ((data.getDateTime().getTime() - start_time.getTime()) / 1000);
//                    start_time = null;
//                }
//            } else if (data.getMode().equals("login")) {
//                //login verification
//                userNewData = new UserNewData();
//                userNewData.setUserid(data.getUserid());
//                userNewData.setScheme(data.getScheme());
//                userNewData.setTotal_login(1);
//                if (data.getEvent().equals("success")) {
//                    //login success
//                    userNewData.setSuccess_login(1);
//                    userNewData.setSuccess_time_taken(time_consume);
//                } else {
//                    //login fail
//                    userNewData.setFail_login(1);
//                    userNewData.setFail_time_taken(time_consume);
//                }
//                newdatalist.add(userNewData);//add data to list
//                time_consume = 0;//reset time to 0
//            }
//        }
//        //final version of userdata
//        ArrayList<UserNewData> combined_new_user_data_text = combineData(newdatalist);
//        //TEXT 21 UP
        ArrayList<UserRemeData> remedatalistFin = new ArrayList<>();
        ArrayList<UserNewData> newdatalistFin = new ArrayList<>();
        for (int i=0;i<remedatalist.size();i++){

            if (remedatalist.get(i).isFinished() == true){
                remedatalistFin.add(remedatalist.get(i));
            }
        }
        for (int i=0;i<newdatalist.size();i++){

            if (newdatalist.get(i).isFinished() == true){
                newdatalistFin.add(newdatalist.get(i));
            }
        }
        //write data to file
        FileIO.writeRemeFile("output_reme.csv", remedatalist);
        FileIO.writeFile("output_text.csv", newdatalist);
        FileIO.writeRemeFile("output_remeFin.csv", remedatalistFin);
        FileIO.writeFile("output_textFin.csv", newdatalistFin);
    System.out.println(remedatalist);


    }



}
