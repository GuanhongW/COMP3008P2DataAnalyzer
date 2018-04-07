package util;

import bean.UserFullData;
import bean.UserNewData;
import bean.UserRemeData;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    The file IO: Read and write the csv file
    Read: read file line by line. And new a Object called UserFullData
    Write: Write to the file.
 */

public class FileIO {
    public static ArrayList<UserFullData> readData(String filename) {
        //set time format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        ArrayList<UserFullData> alldata = new ArrayList<>();
        String line = "";
        BufferedReader fileReader = null;
        try {
            //create file reader
            fileReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            /**
             * Read all lines
             */
            int linenum = 0;
            while ((line = fileReader.readLine()) != null) {
                //Verify data format
                if (StringUtils.count(line, ",") != 4) {
                    continue;
                }
                String[] data_arr = line.split(",");
                for (int i = 0; i < data_arr.length; i++) {
                    //set empty data = ""
                    if (data_arr[i].length() <=3) {//Assume the max id is 999
                        continue;
                    }
                    //remove double quote
                    else {
                        data_arr[i] = data_arr[i].substring(1, data_arr[i].length() - 1);
                    }
                }
                //create new userdata object
                UserFullData userFullData = new UserFullData(linenum++);
                Date actiondate = dateFormat.parse(data_arr[0]);
                userFullData.setDateTime(actiondate);
                userFullData.setUserid(data_arr[1]);
                userFullData.setAction(data_arr[2]);
                userFullData.setData1(data_arr[3]);
                userFullData.setData2(data_arr[4]);
                //add to list
                alldata.add(userFullData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            try {
                //close file
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return alldata;
    }

    public static void writeFile(String filename, List<UserNewData>... alldata) {
        BufferedWriter bufferedWriter = null;
        try {
            //create file writer
            bufferedWriter = new BufferedWriter(new FileWriter(filename));

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < alldata.length; i++) {
            //write to each line
            for (UserNewData u : alldata[i]) {
                try {
                    bufferedWriter.write(u.toString());
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        try {
            //close file buffer
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeRemeFile(String filename, List<UserRemeData>... alldata) {
        BufferedWriter bufferedWriter = null;
        try {
            //create file writer
            bufferedWriter = new BufferedWriter(new FileWriter(filename));

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < alldata.length; i++) {
            //write to each line
            for (UserRemeData u : alldata[i]) {
                try {
                    bufferedWriter.write(u.toString());
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        try {
            //close file buffer
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
