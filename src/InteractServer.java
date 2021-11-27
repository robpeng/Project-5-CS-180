import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.io.*;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.FileNotFoundException;

public class InteractServer {

    Scanner netscan;
    PrintWriter netpw;
    LoginServer loginserver = new LoginServer();
    StudentServer studentserver = new StudentServer();
    TeacherServer teacherserver = new TeacherServer();
    String netString = "";
    private static Object gateKeeper = new Object();

    public InteractServer(Scanner scan, PrintWriter pw) {
        this.netscan = scan;
        this.netpw = pw;
        studentserver.setScanner(netscan);
        studentserver.setPrintWriter(netpw);
        teacherserver.setScanner(netscan);
        teacherserver.setPrintWriter(netpw);
        loginserver.setNetScanPw(netscan, netpw);
    }

    public void interact() throws IOException, FileNotFoundException {
        boolean success = false;
        File directory = new File("Student Submissions");
        synchronized(gateKeeper) {
            if (!directory.exists()) {
                while (true) {
                    try {
                        success = directory.mkdir();
                    } catch (SecurityException e) {
                        netpw.write("000~ Student Submissions could not be created.\n");
                        netpw.flush();
                        return;
                    }

                    if (success) {
                        netpw.write("002~ Student Submissions was created!\n");
                        netpw.flush();
                        break;
                    } else {
                        netpw.write("000~ Student Submissions was not successfully created.\n");
                        netpw.flush();
                        return;
                    }
                }
            } else {
                netpw.write("002~ Student Submissions found!\n");
                netpw.flush();
            }
        }
        while (true) {
            try {
                netString = netscan.nextLine();
                //System.out.println(netString + "!!!!!!!! from client"); //////////////////////////////////////////////
            } catch (NoSuchElementException e) {
                System.out.println("Client stopped.");
                return;
            }
            String[] split = netString.split("~");
            if (split[0].equals("001")) {
                boolean condition = loginserver.addUsername(split[1]);
                if (condition == true) {
                    netpw.write("030~ New User\n");
                    netpw.flush();
                } else {
                    netpw.write("032~ User already exists\n");
                    netpw.flush();
                }
            } else if (split[0].equals("003")) {
                boolean a = loginserver.alreadyUser(split[1], split[2]);
                if (a) {
                    netpw.write("030~ Login successful\n");
                    netpw.flush();

                } else {
                    netpw.write("032~ Login not successful\n");
                    netpw.flush();
                }
            } else if (split[0].equals("005")) {
                boolean a = loginserver.deleteUser(split[1], split[2]);
                if (a) {
                    netpw.write("030~ Deletion successful\n");
                    netpw.flush();

                } else {
                    netpw.write("032~ Deletion not successful\n");
                    netpw.flush();
                }
            } else if (split[0].equals("007")) {
                boolean a = loginserver.editUsername(split[1], split[2]);
                if (a) {
                    loginserver.editPassword(split[2], split[3]);
                    netpw.write("030~ Account edit successful\n");
                    netpw.flush();

                } else {
                    netpw.write("032~ Account edit not successful\n");
                    netpw.flush();
                }
            } else if (split[0].equals("009")) {
                boolean a = loginserver.addUsername(split[1]);
                if (a) {
                    loginserver.addPassword(split[2]);
                    netpw.write("030~ Account creation successful\n");
                    netpw.flush();

                } else {
                    netpw.write("032~ Account creation not successful\n");
                    netpw.flush();
                }
            } else if (split[0].equals("011")) {
                studentserver.setUsername(split[1]);
                netpw.write("024~ Student username set is successful\n");
                netpw.flush();
            }  else if (split[0].equals("051")) {
                teacherserver.createCourse(split[1]);
            } else if (split[0].equals("053")) {
                existingDirectory(split[1]);
            } else if (split[0].equals("055")) {
                teacherserver.createQuiz(split[1], split[2]);
            } else if (split[0].equals("057")) {
                teacherserver.deleteQuiz(split[1], split[2]);
            } else if (split[0].equals("059")) {
                teacherserver.addQuestionTitle(split[1], split[2], split[3]);
            } else if (split[0].equals("061")) {
                String[] answerChoices = split[4].split("@");
                ArrayList<String> answers = new ArrayList<String>();
                for (String each: answerChoices) {
                    answers.add(each);
                }
                teacherserver.addAnswerChoice(split[1], split[2], split[3], answers, Integer.parseInt(split[5]));
            } else if (split[0].equals("063")) {
                teacherserver.addSpace(split[1], split[2]);
            } else if (split[0].equals("065")) {
                String[] answerChoices = split[4].split("@");
                ArrayList<String> answers = new ArrayList<String>();
                for (String each: answerChoices) {
                    answers.add(each);
                }
                teacherserver.chooseCorrectAnswer(split[1], split[2], Integer.parseInt(split[3]), answers);
            } else if (split[0].equals("067")) {
                teacherserver.displayQuizLineNumbers(split[1], split[2]);
            } else if (split[0].equals("069")) {
                ArrayList<String> arrayList = convertToArrayList(split[3]);
                teacherserver.removeQuestion(Integer.parseInt(split[1]), Integer.parseInt(split[2]), arrayList,
                        split[4], split[5]);
            } else if (split[0].equals("071")) {
                teacherserver.displayLinesForAnswerSheet(split[1], split[2]);
            } else if (split[0].equals("073")) {
                ArrayList<String> arr = convertToArrayList(split[3]);
                teacherserver.removeAnswerFromAnswerSheet(split[1], split[2], arr, Integer.parseInt(split[4]));
            } else if (split[0].equals("075")) {
                returnFileNames(split[1]);
            } else if (split[0].equals("077")) {
                teacherserver.viewStudentSubmission(split[1], split[2]);
            } else if (split[0].equals("079")) {
                teacherserver.printQuiz(split[1], split[2]);
            } else if (split[0].equals("081")) {
                teacherserver.printAnswers(split[1], split[2]);
            } else if (split[0].equals("083")) {
                ArrayList<String> arr = convertToArrayList(split[1]);
                teacherserver.numQuestions(arr);
            } else if (split[0].equals("085")) {
                ArrayList<String> arr = convertToArrayList(split[1]);
                teacherserver.printIndividualQuestion(arr, Integer.parseInt(split[2]));
            } else if (split[0].equals("087")) {
                ArrayList<String> arr = convertToArrayList(split[1]);
                teacherserver.printIndividualAnswer(arr, Integer.parseInt(split[2]));
            } else if (split[0].equals("089")) {
                ArrayList<Integer> arr = convertToIntArrayList(split[4]);
                teacherserver.addPoints(split[1], (split[2]), Integer.parseInt(split[3]), arr);
            } else if (split[0].equals("091")) {
                teacherserver.gradeStudent(split[1], (split[2]), split[3], split[4]);
            } else if (split[0].equals("031")) {
                studentserver.takeQuiz();
            } else if (split[0].equals("033")) {
                studentserver.takeQuizViaUpload();
            } else if (split[0].equals("035")) {
                studentserver.submitQuiz();
            } else if (split[0].equals("037")) {
                studentserver.viewGradedQuiz();
            }
        }
    }

    public boolean existingDirectory(String path) {
        File f = new File(path);
        if (f.exists()) {
            netpw.write("030~ directory exists.\n");
            netpw.flush();
            return true;
        } else {
            netpw.write("032~ directory does not exist.\n");
            netpw.flush();
            return false;
        }
    }

    public String convertToString(ArrayList<String> arrList) {
        String str = "";
        for (int i = 0; i < arrList.size(); i++) {
            if (i == arrList.size() - 1) {
                str = str + arrList.get(i);
                break;
            }
            str = str + arrList.get(i) + "@";
        }
        return str;
    }

    public ArrayList<String> convertToArrayList(String str) {
        String[] splitted = str.split("@");
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String each: splitted) {
            arrayList.add(each);
        }
        return arrayList;
    }

    public ArrayList<Integer> convertToIntArrayList(String str) {
        String[] splitted = str.split("@");
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (String each: splitted) {
            arrayList.add(Integer.parseInt(each));
        }
        return arrayList;
    }

    public String convertToString(String[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                str = str + arr[i];
                break;
            }
            str = str + arr[i] + "@";
        }
        return str;
    }

    public String[] returnFileNames(String str) {
        File file = new File(str);
        String[] contents = file.list();
        String string = convertToString(contents);

        netpw.write("036~" + string + "\n");
        netpw.flush();

        netpw.write("030~ Files exist.\n");
        netpw.flush();
        return contents;
    }


}
