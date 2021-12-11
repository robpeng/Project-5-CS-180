

import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


class Gui implements ActionListener {
    JFrame frame;
    JLabel label1;
    JLabel label2;
    JTextArea area;
    JTextField field;
    JScrollPane scroll;
    JButton button;
    volatile boolean isRunning = false;
    volatile boolean guiFlag = false;

    public void createGUI() {
        frame = new JFrame("Quizzes");
        area = new JTextArea();
        area.setBounds(50,40,700,200);
        area.setEditable(false);
        scroll = new JScrollPane(area);
        scroll.setBounds(50,40,710,210);
        ///scroll.setAutoscrolls(true);
        label1 = new JLabel("Display");
        label1.setBounds(370, 10, 150,25);
        field = new JTextField();
        field.setBounds(100,310,600,30);
        label2 = new JLabel("Input");
        label2.setBounds(370,280, 150,20);
        button = new JButton("Submit");
        button.setBounds(330, 400, 100, 20);
        button.addActionListener(this);
        //frame.add(area);
        frame.add(field);
        frame.add(button);
        frame.add(label1);
        frame.add(label2);
        frame.add(scroll);
        frame.setSize(800,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        isRunning = true;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            guiFlag = true;
        }
    }

    public String getText() {
        String str = field.getText();
        field.setText("");
        return str;
    }

    public void setText(String str) {
        area.setText(str + "\n");
    }
    public void appendText(String str) {
        area.append(str + "\n");
    }

    public boolean getGuiFlag() {
        return guiFlag;
    }

    public void resetGuiFlag() {
        guiFlag = false;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

}

public class NetworkClient {

    private static final String WELCOME_MESSAGE = "Welcome to our Quiz Bank!";
    private static final String CURRENT_MEMBER = "Are you a current member? Type in yes or no.";
    private static final String ACCOUNT_MENU = "Enter the number of the action that you want to do\n" +
            "1. Do you want to login?\n" + "2. Do you want to delete your account?\n"
            + "3. Do you want to edit your account?";

    private static final String STUDENT_OR_TEACHER = "Are you a student or a teacher? (type in (student) or (teacher))";
    private static final String USERNAME_PROMPT = "Enter your username:";
    private static final String PASSWORD_PROMPT = "Enter your password:";

    private static final String NEW_ACCOUNT = "Do you want to create a new account? Type in yes or no";

    static Scanner netscan;
    static PrintWriter netpw;
    static ArrayList<String> returnedArrayList = null;
    static String[] returnedArray = null;
    static int returnedInt = 0;
    static Gui gui = new Gui();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui.createGUI();
            }
        });

        while (!gui.getIsRunning()) {
            ;
        }
        boolean flag;
        Socket socket;
        try {
            socket = new Socket("localhost.localdomain", 6457);
        } catch (IOException e) {
            gui.appendText("Error with socket.");
            return;
        }

        try {
            netscan = new Scanner(socket.getInputStream());
            netpw = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            gui.appendText("Error getting stream from socket.");
            return;
        }
        String serverInfo;
        String[] serverInfoSplit;
        while(true) {
            serverInfo = netscan.nextLine();
            serverInfoSplit = serverInfo.split("~");
            if (serverInfoSplit[0].equals("000")) {
                gui.appendText("Server failed");
                return;
            } else if (serverInfoSplit[0].equals("002")) {
                gui.appendText("Server ready!");
                break;
            }
        }

        // start of old main

        Scanner sc = new Scanner(System.in);
        boolean success = false;
        int flag4 = 1;
        do {
            flag4 = 1;
            gui.appendText(WELCOME_MESSAGE);
            gui.appendText("PLEASE DO NOT ENTER .txt when entering the file names of quizzes, courses," +
                    " and submissions");
            gui.appendText(CURRENT_MEMBER);
            String currentMember = getInput();

            if (currentMember.equalsIgnoreCase("Yes")) {

                gui.appendText(ACCOUNT_MENU);
                int accountChoice = getInt();
                //getInput();

                if (accountChoice == 1) {
                    while (true) {
                        gui.appendText(USERNAME_PROMPT);
                        String username = getInput();
                        gui.appendText(PASSWORD_PROMPT);
                        String password = getInput();

                        netpw.write("003~" + username + "~" + password + "\n");
                        netpw.flush();
                        boolean user = processServerInput();


                        if (user) {
                            gui.appendText(STUDENT_OR_TEACHER);
                            String teacherOrStudent = getInput();

                            if (teacherOrStudent.equalsIgnoreCase("teacher")) {
                                gui.appendText("When inputting names of quizzes, courses, and student answers;" +
                                        " please do not include" +
                                        "-- .txt -- in your response");
                                String courseName;
                                ArrayList<Integer> points = new ArrayList<>();
                                String directoryPath = "Student Submissions";
                                while (true) {
                                    int ongoing = 0;
                                    boolean finished = false;
                                    do {
                                        try {
                                            gui.appendText("Please enter the number of the option that you would " +
                                                    "like to choose: \n" +
                                                    "1. Create Course\n" +
                                                    "2. Manage Course\n" +
                                                    "3. View/Grade Student Submissions\n" +
                                                    "4. Exit");
                                            ongoing = getInt();
                                            //getInput();
                                            finished = true;
                                        } catch (InputMismatchException e) {
                                            gui.appendText("This is not an integer, please try again!");
                                            getInput();
                                        }
                                    } while (!finished);
                                    if (ongoing == 1) {
                                        String courseName1;
                                        while (true) {
                                            gui.appendText("Please enter the name of the course that you would" +
                                                    " like to create");
                                            courseName1 = getInput();

                                            netpw.write("051~" + courseName1 + "\n");
                                            netpw.flush();
                                            flag = processServerInput();
                                            if (flag == true) {
                                                break;
                                            }
                                        }
                                        //Teacher1.createCourse(courseName1);
                                    } else if (ongoing == 2) {
                                        while (true) {
                                            gui.appendText("Enter the course name that you would like to manage");
                                            //courseName = new File(getInput());
                                            courseName = getInput();

                                            netpw.write("053~" + courseName + "\n");
                                            netpw.flush();
                                            flag = processServerInput();
                                            if (flag == true) {
                                                break;
                                            } else {
                                                gui.appendText("This course does not exist, please try again!");
                                            }
                                        }

                                        while (true) {
                                            int ongoing1 = 0;
                                            finished = false;
                                            do {
                                                try {
                                                    gui.appendText("Please enter the number of the option that" +
                                                            " you would like to choose: \n" +
                                                            "1. Add Quiz\n" +
                                                            "2. Delete Quiz\n" +
                                                            "3. Manage Quiz\n" +
                                                            "4. Exit");
                                                    ongoing1 = getInt();
                                                    //getInput();
                                                    finished = true;
                                                } catch (InputMismatchException e) {
                                                    gui.appendText("This is not an integer, please try again!");
                                                    getInput();
                                                }
                                            } while (!finished);
                                            if (ongoing1 == 1) {
                                                String quizName;
                                                while (true) {
                                                    gui.appendText("Please enter the name of the quiz that you" +
                                                            " would like to create");
                                                    quizName = getInput();

                                                    netpw.write("055~" + courseName + "~" + quizName + "\n");
                                                    netpw.flush();
                                                    flag = processServerInput();

                                                    if (!flag) {
                                                        gui.appendText("This quiz already exists, " +
                                                                "please try again!");
                                                    } else {

                                                        netpw.write("055~" + courseName + "~" + quizName + "points"
                                                                + "\n");
                                                        netpw.flush();
                                                        processServerInput();
                                                        break;
                                                    }
                                                }
                                            } else if (ongoing1 == 2) {
                                                String quizName;
                                                while (true) {
                                                    gui.appendText("What quiz would you like to delete?");
                                                    quizName = getInput();

                                                    netpw.write("057~" + courseName + "~" + quizName + "\n");
                                                    netpw.flush();
                                                    flag = processServerInput();

                                                    //String quiz = Teacher1.deleteQuiz(courseName, quizName);

                                                    if (!flag) {
                                                        gui.appendText("This quiz does not exist," +
                                                                " please try again!");
                                                    } else {
                                                        netpw.write("057~" + courseName + "~" + quizName + "points\n");
                                                        netpw.flush();
                                                        processServerInput();
                                                        netpw.write("057~" + courseName + "~" + quizName + "answersheet\n");
                                                        netpw.flush();
                                                        processServerInput();

                                                        ///Teacher1.deleteQuiz(courseName, quizName + "points");
                                                        ///Teacher1.deleteQuiz(courseName, quizName + "answersheet");

                                                        break;
                                                    }
                                                }
                                            } else if (ongoing1 == 3) {
                                                String quizName;
                                                while (true) {
                                                    gui.appendText("Please enter the quiz" +
                                                            " name that you would like to manage");
                                                    quizName = getInput();

                                                    netpw.write("053~" + courseName + "/" + quizName + ".txt" + "\n");
                                                    netpw.flush();
                                                    flag = processServerInput();

                                                    if (flag) {
                                                        break;
                                                    } else {
                                                        gui.appendText("This quiz does not exist," +
                                                                " please try again!");
                                                    }
                                                }
                                                while (true) {
                                                    int ongoing2 = 0;
                                                    finished = false;
                                                    do {
                                                        try {
                                                            gui.appendText("Please enter the number of the " +
                                                                    "option that you would like to choose: \n" +
                                                                    "1. Add Question\n" +
                                                                    "2. Remove Question\n" +
                                                                    "3. Exit");
                                                            ongoing2 = getInt();
                                                            //getInput();
                                                            finished = true;
                                                        } catch (InputMismatchException e) {
                                                            gui.appendText("This is not an integer," +
                                                                    " please try again!");
                                                            getInput();
                                                        }
                                                    } while (!finished);
                                                    if (ongoing2 == 1) {
                                                        String again = "yes";
                                                        do {
                                                            gui.appendText("What is the question" +
                                                                    " that you would like to add?");
                                                            String question = getInput();

                                                            netpw.write("059~" + courseName + "~" + quizName + "~" + question + "\n");
                                                            netpw.flush();
                                                            flag = processServerInput();

                                                            //Teacher1.addQuestionTitle(courseName, quizName, question);

                                                            if (!flag){
                                                                gui.appendText("File was not found.");
                                                                return;
                                                            }

                                                            int numAnswerChoices = 0;
                                                            finished = false;
                                                            do {
                                                                try {
                                                                    gui.appendText("How many answer choices would" +
                                                                            " you like this question to have?");
                                                                    numAnswerChoices = getInt();
                                                                    //getInput();
                                                                    finished = true;
                                                                } catch (InputMismatchException e) {
                                                                    gui.appendText("This is not an integer," +
                                                                            " please try again!");
                                                                    getInput();
                                                                }
                                                            } while (!finished);
                                                            int answerChoiceNumber = 1;
                                                            ArrayList<String> answerChoices = new ArrayList<>();
                                                            while (answerChoiceNumber <= numAnswerChoices) {
                                                                gui.appendText("Please enter the answer choice" +
                                                                        " for option number " + answerChoiceNumber);
                                                                String answer = getInput();

                                                                String choices = "";

                                                                for (int i = 0; i < answerChoices.size(); i++) {
                                                                    if (i == answerChoices.size() - 1) {
                                                                        choices = choices + answerChoices.get(i);
                                                                        break;
                                                                    }
                                                                    choices = choices + answerChoices.get(i) + "@";
                                                                }

                                                                netpw.write("061~" + courseName + "~" + quizName + "~"
                                                                        + answer + "~" + choices
                                                                        + "~" + answerChoiceNumber +  "\n");
                                                                netpw.flush();
                                                                boolean flaglocal = processServerInput();

                                                                answerChoices = returnedArrayList;


                                                                // Teacher1.addAnswerChoice(courseName, quizName,
                                                                // answer, answerChoices, answerChoiceNumber);


                                                                if(!flaglocal) {
                                                                    gui.appendText("File not Found");
                                                                    return;
                                                                }
                                                                answerChoiceNumber++;

                                                            }

                                                            netpw.write("063~" + courseName + "~" + quizName + "\n");
                                                            netpw.flush();
                                                            flag = processServerInput();

                                                            ///Teacher1.addSpace(courseName, quizName);

                                                            if (!flag) {
                                                                gui.appendText("File not found.");
                                                                return;
                                                            }

                                                            int count = 0;
                                                            while (count < numAnswerChoices) {
                                                                String s = String.valueOf(Character.toChars(
                                                                        count + 65));
                                                                gui.appendText(s + ") " + answerChoices.get(count));
                                                                count++;
                                                            }
                                                            int correctAnswer = 0;
                                                            finished = false;
                                                            do {
                                                                try {

                                                                    gui.appendText("Which answer choice is the" +
                                                                            " correct answer? 1 is A, 2 is B, etc...");
                                                                    correctAnswer = getInt();
                                                                    //getInput();
                                                                    if (correctAnswer > numAnswerChoices) {
                                                                        gui.appendText("This is not a number " +
                                                                                "correlated to an answer choice," +
                                                                                " please try again");
                                                                    }
                                                                    finished = true;
                                                                } catch (InputMismatchException e) {
                                                                    gui.appendText("This is not an integer, " +
                                                                            "please try again!");
                                                                    getInput();
                                                                }
                                                            } while (!finished || correctAnswer > numAnswerChoices);

                                                            String choices = "";
                                                            for (int i = 0; i < answerChoices.size(); i++) {
                                                                if (i == answerChoices.size() - 1) {
                                                                    choices = choices + answerChoices.get(i);
                                                                    break;
                                                                }
                                                                choices = choices + answerChoices.get(i) + "@";
                                                            }

                                                            netpw.write("065~" + courseName + "~" + quizName +
                                                                    "~" + correctAnswer + "~" +
                                                                    choices + "\n");
                                                            netpw.flush();
                                                            flag = processServerInput();


                                                            //Teacher1.chooseCorrectAnswer(courseName, quizName,
                                                            // correctAnswer, answerChoices);
                                                            if (!flag) {
                                                                gui.appendText("File not found.");
                                                                return;
                                                            }
                                                            while (true) {
                                                                gui.appendText("Would you like to add another " +
                                                                        "question for this quiz? " +
                                                                        "Please enter yes or no");
                                                                again = getInput();
                                                                again = again.toLowerCase();
                                                                if (again.equals("yes")) {
                                                                    break;
                                                                } else if (again.equals("no")) {
                                                                    break;
                                                                } else {
                                                                    gui.appendText("Please try again");
                                                                }
                                                            }
                                                        } while (!again.equals("no"));
                                                    } else if (ongoing2 == 2) {
                                                        ArrayList<String> quiz;

                                                        netpw.write("067~" + courseName + "~" + quizName + "\n");
                                                        netpw.flush();
                                                        flag = processServerInput();
                                                        quiz = returnedArrayList;

                                                        //quiz =Teacher1.displayQuizLineNumbers
                                                        //(courseName, quizName);


                                                        if (!flag) {
                                                            gui.appendText("File not found.");
                                                            return;
                                                        }
                                                        int firstLine = 0;
                                                        finished = false;
                                                        do {
                                                            try {
                                                                gui.appendText("Enter the number to the left of" +
                                                                        " the first line of the question you would " +
                                                                        "like to remove");
                                                                firstLine = getInt();
                                                                //getInput();
                                                                finished = true;
                                                            } catch (InputMismatchException e) {
                                                                gui.appendText("This is not an integer, " +
                                                                        "please try again!");
                                                                sc.hasNextLine();
                                                            }
                                                        } while (!finished);
                                                        int secondLine = 0;
                                                        finished = false;
                                                        do {
                                                            try {
                                                                gui.appendText("Enter the number to the left of " +
                                                                        "the blank at the last line of the question " +
                                                                        "you would like to remove");
                                                                secondLine = getInt();
                                                                //getInput();
                                                                finished = true;
                                                            } catch (InputMismatchException e) {
                                                                gui.appendText("This is not an integer," +
                                                                        " please try again!");
                                                                sc.hasNextLine();
                                                            }
                                                        } while (!finished);

                                                        String str = convertToString(quiz);

                                                        netpw.write("069~" + firstLine + "~" + secondLine + "~" +
                                                                str + "~" + courseName + "~" +
                                                                quizName + "\n");
                                                        netpw.flush();
                                                        flag = processServerInput();



                                                        //Teacher1.removeQuestion(firstLine, secondLine, quiz,
                                                        //courseName, quizName);
                                                        if (!flag) {
                                                            gui.appendText("File not found");
                                                            return;
                                                        }
                                                        ArrayList<String> answerSheet;


                                                        netpw.write("071~" + courseName + "~" + quizName  + "\n");
                                                        netpw.flush();
                                                        flag = processServerInput();

                                                        answerSheet = returnedArrayList;
                                                        //answerSheet = Teacher1.displayLinesForAnswerSheet
                                                        //(courseName, quizName);
                                                        if (!flag) {
                                                            gui.appendText("File was not found.");
                                                            return;
                                                        }
                                                        int lineNumber = 0;
                                                        finished = false;
                                                        do {
                                                            try {
                                                                gui.appendText("Enter the number to the left of" +
                                                                        " the answer to the question that you removed");
                                                                lineNumber = getInt();
                                                                //getInput();
                                                                finished = true;
                                                            } catch (InputMismatchException e) {
                                                                gui.appendText("This is not an integer," +
                                                                        " please try again");
                                                                getInput();
                                                            }
                                                        } while (!finished);
                                                        String string = convertToString(answerSheet);
                                                        netpw.write("073~" + courseName + "~" + quizName + "~" +
                                                                string + "~" +
                                                                lineNumber +  "\n");
                                                        netpw.flush();
                                                        flag = processServerInput();

                                                        // Teacher1.removeAnswerFromAnswerSheet
                                                        //(courseName, quizName, answerSheet, lineNumber);
                                                        if (!flag) {
                                                            gui.appendText("File was not found.");
                                                            return;
                                                        }
                                                    } else if (ongoing2 == 3) {
                                                        break;
                                                    } else {
                                                        gui.appendText("Invalid input!");
                                                    }
                                                }
                                            } else if (ongoing1 == 4) {
                                                break;
                                            } else {
                                                gui.appendText("Invalid input!");
                                            }
                                        }
                                    } else if (ongoing == 3) {

                                        netpw.write("075~" + directoryPath + "\n");
                                        netpw.flush();
                                        processServerInput();
                                        String[] contents = returnedArray;

                                        //String[] contents = directoryPath.list();

                                        while (true) {
                                            int ongoing3 = 0;
                                            finished = false;
                                            do {
                                                try {
                                                    gui.appendText("Enter the number next to the option that" +
                                                            " you would like to choose\n" +
                                                            "1. View Student's Submitted Quiz\n" +
                                                            "2. Grade Student's Submitted Quiz\n" +
                                                            "3. Exit");
                                                    ongoing3 = getInt();
                                                    //getInput();
                                                    finished = true;
                                                } catch (InputMismatchException e) {
                                                    gui.appendText("This is not an integer, please try again!");
                                                    getInput();
                                                }
                                            } while (!finished);
                                            if (ongoing3 == 1) {
                                                if (contents != null) {
                                                    for (String content : contents) {
                                                        gui.appendText(content);
                                                    }
                                                }
                                                String studentSubmission;
                                                gui.appendText("Please enter the name of the student " +
                                                        "submission that you would like to view");
                                                studentSubmission = getInput();

                                                netpw.write("077~" + directoryPath + "~" + studentSubmission + "\n");
                                                netpw.flush();
                                                flag = processServerInput();

                                                ///File f;

                                                ///f = Teacher1.viewStudentSubmission
                                                ///(directoryPath, studentSubmission);
                                                if (!flag) {
                                                    gui.appendText("File not found.");
                                                    return;
                                                }
                                            } else if (ongoing3 == 2) {
                                                String quizName;

                                                while (true) {
                                                    gui.appendText("What is the name of the course of the " +
                                                            "quiz you want to grade?");
                                                    courseName = getInput();

                                                    netpw.write("075~" + courseName + "\n");
                                                    netpw.flush();
                                                    processServerInput();
                                                    String[] content = returnedArray;

                                                    //File directoryPath1 = new File(String.valueOf(courseName));
                                                    //String[] content = directoryPath1.list();


                                                    if (content != null) {
                                                        for (String contents1 : content) {
                                                            gui.appendText(contents1);
                                                        }
                                                    }
                                                    gui.appendText("What is the name of the quiz that " +
                                                            "you would like to grade?");
                                                    quizName = getInput();

                                                    netpw.write("053~" + courseName + "/" + quizName + ".txt" + "\n");
                                                    netpw.flush();
                                                    flag = processServerInput();

                                                    if (flag) {
                                                        break;
                                                    } else {
                                                        gui.appendText("This quiz does not exist, " +
                                                                "please try again!");
                                                    }
                                                }
                                                if (contents != null) {
                                                    for (String content : contents) {
                                                        gui.appendText(content);
                                                    }
                                                }
                                                String studentAnswerSheet;
                                                while (true) {
                                                    gui.appendText("What is the name of the student answer" +
                                                            " sheet that you would like to grade? Do NOT include .txt in" +
                                                            " the name." +
                                                            " Please select the answer sheet that contains " +
                                                            "'finalanswers'.");
                                                    studentAnswerSheet = getInput();

                                                    netpw.write("053~" + directoryPath + "/" + studentAnswerSheet
                                                            + ".txt" + "\n");
                                                    netpw.flush();
                                                    flag = processServerInput();

                                                    if (flag) {
                                                        break;
                                                    } else {
                                                        gui.appendText("This answer sheet does not exist," +
                                                                " please try again");
                                                    }
                                                }
                                                ArrayList<String> scores;

                                                netpw.write("079~" + courseName + "~" + quizName + "\n");
                                                netpw.flush();
                                                flag = processServerInput();
                                                scores = returnedArrayList;

                                                /// scores = Teacher1.printQuiz(courseName, quizName);

                                                if (!flag) {
                                                    gui.appendText("File not found.");
                                                    return;
                                                }
                                                ArrayList<String> studentAnswers;

                                                netpw.write("081~" + directoryPath + "~" + studentAnswerSheet + "\n");
                                                netpw.flush();
                                                flag = processServerInput();
                                                studentAnswers = returnedArrayList;


                                                //studentAnswers = Teacher1.printAnswers(directoryPath,
                                                //studentAnswerSheet);

                                                if (!flag) {
                                                    gui.appendText("File not found.");
                                                    return;
                                                }

                                                String str = convertToString(scores);

                                                netpw.write("083~" + str + "\n");
                                                netpw.flush();
                                                flag = processServerInput();
                                                int numQuestions = returnedInt + 1;

                                                //int numQuestions = Teacher1.numQuestions(scores);



                                                int counter = 0;
                                                int indexOfBlank = 0;
                                                int indexNewLine = 0;
                                                while (counter < numQuestions) {

                                                    String str2 = convertToString(scores);
                                                    netpw.write("085~" + str2 + "~" + indexOfBlank + "\n");
                                                    netpw.flush();
                                                    processServerInput();
                                                    int j = returnedInt;

                                                    //int j = Teacher1.printIndividualQuestion(scores, indexOfBlank);

                                                    //System.out.print("Student Answer: ");

                                                    String str3 = convertToString(studentAnswers);
                                                    netpw.write("087~" + str3 + "~" + indexNewLine + "\n");
                                                    netpw.flush();
                                                    processServerInput();
                                                    int i = returnedInt;


                                                    //int i = Teacher1.printIndividualAnswer(studentAnswers, indexNewLine);



                                                    int numPoints = 0;
                                                    finished = false;
                                                    do {
                                                        try {
                                                            gui.appendText("How many points would you like to" +
                                                                    " assign to this question?");
                                                            numPoints = getInt();
                                                           // getInput();
                                                            finished = true;
                                                        } catch (InputMismatchException e) {
                                                            gui.appendText("This is not an integer, " +
                                                                    "please try again!");
                                                            getInput();
                                                        }
                                                    } while (!finished);
                                                    points.add(numPoints); //////////changed here
                                                    String str4 = convertIntsToString(points);
                                                    netpw.write("089~" + courseName + "~" + quizName + "~" +
                                                            numPoints + "~" +
                                                            str4 + "\n");
                                                    netpw.flush();
                                                    flag = processServerInput();

                                                    //Teacher1.addPoints(courseName, quizName, numPoints, points);
                                                    if (!flag) {
                                                        gui.appendText("File not found.");
                                                        return;
                                                    }
                                                    counter++;
                                                    indexOfBlank = j;
                                                    indexNewLine = i;
                                                }

                                                netpw.write("091~" + courseName + "~" +directoryPath + "~" +
                                                        quizName + "~" + studentAnswerSheet + "\n");
                                                netpw.flush();
                                                flag = processServerInput();


                                                ////Teacher1.gradeStudent(courseName, directoryPath, quizName,
                                                //studentAnswerSheet);
                                                if (!flag) {
                                                    gui.appendText("File not found.");
                                                    return;
                                                }
                                            } else if (ongoing3 == 3) {
                                                break;
                                            } else {
                                                gui.appendText("Invalid input!");
                                            }
                                        }
                                    } else if (ongoing == 4) {
                                        gui.appendText("Thank you for logging in!");
                                        break;
                                    } else {
                                        gui.appendText("Invalid input!");
                                    }
                                }
                                break;
                            } else if (teacherOrStudent.equalsIgnoreCase("student")) {
                                netpw.write("011~" + username + "\n");
                                netpw.flush();
                                processServerInput();

                                while (true) {
                                    //////Student student = new Student(username);
                                    gui.appendText("1. Do you want to take a quiz?\n" +
                                            "2. Do you want to submit a quiz?\n" +
                                            "3. Do you" +
                                            " want to view your grading report?\n" +
                                            "4. Exit");
                                    int choice = getInt();
                                    //getInput();
                                    if (choice == 1) {
                                        while (true) {
                                            gui.appendText("1. Do you want to take your quiz via the terminal?\n" +
                                                    "2. Do you want to take your" +
                                                    " quiz by uploading a file?\n3. Exit");
                                            int quizChoice = getInt();
                                            //getInput();

                                            if (quizChoice == 1) {
                                                netpw.write("031~" + "calling student takequiz" + "\n");
                                                netpw.flush();
                                                processServerInput();
                                                //student.takeQuiz(sc);
                                                break;
                                            } else if (quizChoice == 2) {
                                                netpw.write("033~" + "calling student takequizviaupload" + "\n");
                                                netpw.flush();
                                                processServerInput();
                                                ///student.takeQuizViaUpload(sc);
                                                break;
                                            } else if (quizChoice == 3) {
                                                break;
                                            } else {
                                                gui.appendText("Invalid Input.");
                                            }
                                        }
                                    } else if (choice == 2) {
                                        netpw.write("035~" + "calling student submitQuiz" + "\n");
                                        netpw.flush();
                                        processServerInput();
                                        //student.submitQuiz(sc);
                                        break;
                                    } else if (choice == 3) {
                                        netpw.write("037~" + "calling student viewGradedQuiz" + "\n");
                                        netpw.flush();
                                        processServerInput();
                                        ///student.viewGradedQuiz(sc);
                                    } else if (choice == 4) {
                                        break;
                                    }
                                    else {
                                        gui.appendText("Invalid input.");
                                    }
                                }
                                break;
                            } else {
                                gui.appendText("Wrong Choice, try again");
                            }
                        } else {
                            gui.appendText("Wrong username or password, try again");
                        }
                    }
                }
                if (accountChoice == 2) {
                    int flag1 = 0;

                    do {
                        gui.appendText(USERNAME_PROMPT);
                        String username1 = getInput();
                        gui.appendText(PASSWORD_PROMPT);
                        String password1 = getInput();

                        //Login loginObj1 = new Login();

                        netpw.write("003~" + username1 + "~" + password1  + "\n");
                        netpw.flush();
                        boolean a = processServerInput();

                        //boolean a = loginObj1.alreadyUser(username1, password1);

                        if (a) {

                            netpw.write("005~" + username1 + "~" + password1  + "\n");
                            netpw.flush();
                            processServerInput();

                            ////loginObj1.deleteUser(username1, password1);


                            gui.appendText("Account was successfully deleted!");
                            flag1 = 1;
                        } else {
                            gui.appendText("Please try again");
                        }
                    } while (flag1 == 0);
                }
                if (accountChoice == 3) {
                    int flag2 = 0;

                    do {
                        gui.appendText(USERNAME_PROMPT);
                        String username2 = getInput();
                        gui.appendText(PASSWORD_PROMPT);
                        String password2 = getInput();

                        netpw.write("003~" + username2 + "~" + password2  + "\n");
                        netpw.flush();
                        flag = processServerInput();

                        /////boolean b = loginObj2.alreadyUser(username2, password2);

                        if (flag) {
                            gui.appendText("What would you like your new username to be?");
                            String newUserName = getInput();


                            ////loginObj2.editUsername(username2, newUserName);

                            gui.appendText("What would you like your new password to be?");
                            String newPassword = getInput();

                            netpw.write("007~" + username2 + "~" + newUserName  + "~" + newPassword + "\n");
                            netpw.flush();
                            flag = processServerInput();
                            ////loginObj2.editPassword(newUserName, newPassword);
                            if (!flag) {
                                gui.appendText("Account was not successfully edited.");
                            } else {
                                flag2 = 1;
                                gui.appendText("Account was successfully edited!");
                            }
                        }
                    } while (flag2 == 0);
                }
            } else if (currentMember.equalsIgnoreCase("no")) {
                gui.appendText(NEW_ACCOUNT);
                String newAccountOrNot = getInput();

                if (newAccountOrNot.equalsIgnoreCase("yes")) {
                    while (true) {
                        gui.appendText(USERNAME_PROMPT);
                        String username = getInput();
                        gui.appendText(PASSWORD_PROMPT);
                        String password = getInput();

                        netpw.write("009~" + username + "~" + password  + "\n");
                        netpw.flush();
                        flag = processServerInput();

                        ///String login = loginObj.addUsername(username);



                        if (flag) {
                            ///loginObj.addPassword(password);
                            gui.appendText("Your account has successfully been created!");
                            break;
                        } else {
                            gui.appendText("Try again.");
                        }
                    }
                }
            } else {
                gui.appendText("Wrong choice. Do you want to try again (yes/no)?");
                String yesOrNo = getInput();

                if (yesOrNo.equalsIgnoreCase("yes")) {
                    flag4 = 0;
                } else {
                    gui.appendText("Thank you for using the quiz bank.");
                    flag4 = 1;
                }
            }
        } while(flag4 == 0);

        //end of old main


        //end of old main
        netpw.close();
        netscan.close();

        gui.appendText("\nThe process has finished. Please exit the GUI through clicking the X on the" +
                " top right corner of the screen. \nIf you need to use our program again," +
                    " please run the program again.");
    }

    private static boolean processServerInput() {
        String stringInfo = "";
        //Scanner scan = new Scanner(System.in);
        while(true) {

            stringInfo = netscan.nextLine();
            ///gui.appendText(stringInfo +"######## from server");  ////////////////////////////we are printing here
            String[] strArray = stringInfo.split("~");
            if (strArray[0].equals("028")) {
                gui.appendText(strArray[1]);
            } else if (strArray[0].equals("026")) {
                gui.appendText(strArray[1]);
                String str = getInput();
                netpw.write(str + "\n");
                netpw.flush();
            } else if (strArray[0].equals("030")) {
                //gui.appendText(strArray[1]);
                return true;
            } else if (strArray[0].equals("032")) {
                //gui.appendText(strArray[1]);
                return false;
            } else if (strArray[0].equals("034")) {
                String[] splitted = strArray[1].split("@");
                ArrayList<String> arrayList = new ArrayList<String>();
                for (String each: splitted) {
                    arrayList.add(each);
                }
                for (String each: arrayList) {
                    gui.appendText(each);
                }
                returnedArrayList = arrayList;
            } else if (strArray[0].equals("036")) {
                String[] splitted = strArray[1].split("@");
                for (String each: splitted) {
                    gui.appendText(each);
                }
                returnedArray = splitted;
            } else if (strArray[0].equals("038")) {
                returnedInt = Integer.parseInt(strArray[1]);
            } else if (strArray[0].equals("040")) {
                String[] splitted = strArray[1].split("@");
                for (String each: splitted) {
                    gui.appendText(each);
                }
                String str = getInput();
                netpw.write(str + "\n");
                netpw.flush();
            }
        }
    }

    public static String convertToString(ArrayList<String> arrList) {
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

    public static String convertIntsToString(ArrayList<Integer> arrList) {
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

    public static ArrayList<String> convertToArrayList(String str) {
        String[] splitted = str.split("@");
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String each : splitted) {
            arrayList.add(each);
        }
        return arrayList;
    }

    public static String getInput() {
        gui.resetGuiFlag();
        while (true) {
            if (gui.getGuiFlag()) {
                gui.resetGuiFlag();
                return gui.getText();
            }
        }
    }

    public static int getInt() {
        String result = getInput();
        int a;
        try {
            a = Integer.parseInt(result);
        } catch (NumberFormatException e) {
            gui.appendText("Not a number.");
            return -1;
        }
        return a;
    }
}
