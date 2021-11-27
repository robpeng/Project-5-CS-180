import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;

public class StudentServer {

    String username = null;
    Scanner netscan = null;
    PrintWriter netpw = null;

    public void setScanner(Scanner netscan) {
        this.netscan = netscan;
    }

    public void setPrintWriter(PrintWriter netpw) {
        this.netpw = netpw;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void takeQuiz() {
        System.out.println("Entered take quiz method. !!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (username == null || netscan == null || netpw == null) {
            netpw.write("032~ Student server method finishes.\n");
            netpw.flush();
            return;
        }
        System.out.println("Entered take quiz method reached end of if loop. !!!!!!!!!!!!!!!!!!!!!!!!!!");
        String filename;
        File f;
        String course;
        FileReader fr;
        while (true) {
            netpw.write("026~ Enter the course of the quiz you want to take.\n");
            netpw.flush();
            course = netscan.nextLine();
            File directoryPath = new File(course);
            String[] content = directoryPath.list();
            if (content != null) {
                for (String contents : content) {
                    System.out.println(contents);
                }
            }
            netpw.write("026~ Enter the file name of the quiz you want to take.\n");
            netpw.flush();
            filename = netscan.nextLine();
            f = new File(course, filename  + ".txt");
            try {
                fr = new FileReader(f);
            } catch (IOException e) {
                netpw.write("028~ Quiz was not found!\n");
                netpw.flush();
                continue;
            }
            break;
        }
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> questions = new ArrayList<String>();
        String str = "";
        while(true) {
            String nextLine;
            try {
                nextLine = br.readLine();
                if (nextLine == null) {
                    if (str.length() > 0) {
                        questions.add(str);
                    }
                    break;
                }
                if (nextLine.equals("")){
                    str = str + nextLine;
                    questions.add(str);
                    str = "";
                    continue;
                }
                str = str + nextLine + "\n";
            } catch(IOException e) {
                e.printStackTrace();
                netpw.write("032~ Student server method finishes.\n");
                netpw.flush();
                return;
            }
        }
        File answerFile;
        FileOutputStream answersSaver;
        PrintWriter pw;
        String answersFileName;
        String answer;
        for (int i = 1; i <= questions.size(); i++) {
            netpw.write("026~ " + questions.get(i-1) + "\n");
            netpw.flush();
            answer = netscan.nextLine();
            answersFileName = filename + this.username + "Q" + i + "answer";
            answerFile = new File("Student Submissions" , answersFileName + ".txt");
            try {
                answersSaver = new FileOutputStream(answerFile, false);
            } catch (FileNotFoundException e) {
                netpw.write("028~ File can not be opened!\n");
                netpw.flush();
                netpw.write("032~ Student server method finishes.\n");
                netpw.flush();
                return;
            }
            pw = new PrintWriter(answersSaver);
            pw.println(answer);
            pw.close();
        }
        netpw.write("030~ Student server method finishes.\n");
        netpw.flush();
    }

    public void submitQuiz() {
        ////add timestamp
        if (username == null || netscan == null || netpw == null) {
            netpw.write("032~ Student server method finishes.\n");
            netpw.flush();
            return;
        }
        Calendar calendar = Calendar.getInstance();
        TimeZone timezone = TimeZone.getTimeZone("US/Eastern");
        calendar.setTimeZone(timezone);
        String timeSubmission = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
                + calendar.get(Calendar.DATE) + "-" + calendar.get(Calendar.HOUR) + "-" +
                calendar.get(Calendar.MINUTE) + "-" + calendar.get(Calendar.SECOND) + " EST";
        netpw.write("026~ Enter the course of the quiz you want to submit.\n");
        netpw.flush();
        String course = netscan.nextLine();
        netpw.write("026~ Enter the name of the quiz you want to submit.\n");
        netpw.flush();
        String submittedQuiz = netscan.nextLine();
        int count = 1;
        ArrayList<String> answers = new ArrayList<>();
        File f;
        FileReader fr;
        BufferedReader br;
        while(true) {
            String filename = submittedQuiz + this.username + "Q" + count + "answer";
            f = new File("Student Submissions", filename + ".txt");
            try {
                fr = new FileReader(f);
            } catch (FileNotFoundException e) {
                break;
            }
            br = new BufferedReader(fr);
            String str;
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                netpw.write("032~ Student server method finishes.\n");
                netpw.flush();
                return;
            }
            answers.add(str);
            File ff = new File(filename);
            try {
                br.close();
            } catch(IOException e) {
                e.printStackTrace();
                netpw.write("032~ Student server method finishes.\n");
                netpw.flush();
                return;
            }
            count++;
        }
        String str = submittedQuiz + this.username + "finalanswers" + ".txt";
        File finalanswers = new File("Student Submissions", str);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(finalanswers);
        } catch(IOException e) {
            e.printStackTrace();
            netpw.write("032~ Student server method finishes.\n");
            netpw.flush();
            return;
        }
        PrintWriter pw = new PrintWriter(fos);
        for (int i = 0; i < answers.size(); i++) {
            pw.println(answers.get(i));
        }
        pw.println(timeSubmission);
        pw.close();
        netpw.write("028~ Submission success!\n");
        netpw.flush();
        netpw.write("030~ Student server method finishes.\n");
        netpw.flush();
    }
    public void viewGradedQuiz() {
        if (username == null || netscan == null || netpw == null) {
            netpw.write("032~ Student server method finishes.\n");
            netpw.flush();
            return;
        }
        netpw.write("026~ Enter the quiz that you want to view the grade of.\n");
        netpw.flush();
        String quiz = netscan.nextLine();
        File f = new File("Student Submissions", quiz + this.username + "finalanswers.txt");
        FileReader fr;
        try {
            fr = new FileReader(f);
        } catch(FileNotFoundException e) {
            netpw.write("028~ Graded quiz was not found.\n");
            netpw.flush();
            netpw.write("032~ Student server method finishes.\n");
            netpw.flush();
            return;
        }
        BufferedReader br = new BufferedReader(fr);
        while(true) {
            String gradingLines;
            try {
                gradingLines = br.readLine();
                if (gradingLines == null) {
                    break;
                }
                netpw.write("028~ " + gradingLines + "\n");
                netpw.flush();
            } catch (IOException e) {
                e.printStackTrace();
                netpw.write("032~ Student server method finishes.\n");
                netpw.flush();
                return;
            }
        }
        netpw.write("030~ Student server method finishes.\n");
        netpw.flush();
    }
    public void takeQuizViaUpload() {
        if (username == null || netscan == null || netpw == null) {
            netpw.write("032~ Student server method finishes.\n");
            netpw.flush();
            return;
        }
        String filename;
        File f;
        String course;
        FileReader fr;
        while (true) {
            netpw.write("026~ Enter the course of the quiz you want to take.\n");
            netpw.flush();
            course = netscan.nextLine();
            netpw.write("026~ Enter the file name of the quiz you want to take.\n");
            netpw.flush();
            filename = netscan.nextLine();
            f = new File(course, filename  + ".txt");
            try {
                fr = new FileReader(f);
            } catch (IOException e) {
                netpw.write("028~ Quiz was not found!\n");
                netpw.flush();
                continue;
            }
            break;
        }
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> questions = new ArrayList<String>();
        String str = "";
        while(true) {
            String nextLine;
            try {
                nextLine = br.readLine();
                if (nextLine == null) {
                    if (str.length() > 0) {
                        questions.add(str);
                    }
                    break;
                }
                if (nextLine.equals("")){
                    str = str + nextLine;
                    questions.add(str);
                    str = "";
                    continue;
                }
                str = str + nextLine + "\n";
            } catch(IOException e) {
                e.printStackTrace();
                netpw.write("032~ Student server method finishes.\n");
                netpw.flush();
                return;
            }
        }
        File answerFile;
        FileOutputStream answersSaver;
        PrintWriter pw;
        String answersFileName;
        String filepath;
        File uploadedFile;
        FileReader uploadedFilefr;
        BufferedReader uploadedFilebr;


        for (int i = 1; i <= questions.size(); i++) {
            netpw.write("028~ " + questions.get(i-1) + "\n");
            netpw.flush();
            while(true) {
                netpw.write("026~ type in the filepath of the file you want to upload for this question. " +
                        "Make sure your answer choice" +
                        " for the question is on the first line of your file.\n");
                netpw.flush();
                filepath = netscan.nextLine();
                uploadedFile = new File(filepath);
                try {
                    uploadedFilefr = new FileReader(uploadedFile);
                } catch (IOException e) {
                    netpw.write("028~ Filepath was not found!\n");
                    netpw.flush();
                    continue;
                }
                break;
            }
            uploadedFilebr = new BufferedReader(uploadedFilefr);
            String u;
            try {
                u = uploadedFilebr.readLine();
            } catch(IOException e) {
                e.printStackTrace();
                netpw.write("032~ Student server method finishes.\n");
                netpw.flush();
                return;
            }

            answersFileName = filename + this.username + "Q" + i + "answer";
            answerFile = new File("Student Submissions", answersFileName + ".txt");
            try {
                answersSaver = new FileOutputStream(answerFile, false);
            } catch (FileNotFoundException e) {
                netpw.write("028~ File can not be opened!\n");
                netpw.flush();
                netpw.write("032~ Student server method finishes.\n");
                netpw.flush();
                return;
            }
            pw = new PrintWriter(answersSaver);
            pw.println(u);
            pw.close();

        }
        netpw.write("030~ Student server method finishes.\n");
        netpw.flush();
    }
}
