import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;

public class TeacherServer {

    Scanner netscan = null;
    PrintWriter netpw = null;

    public void setScanner(Scanner netscan) {
        this.netscan = netscan;
    }

    public void setPrintWriter(PrintWriter netpw) {
        this.netpw = netpw;
    }

    public File createCourse(String courseName) {
        boolean success = false;
        File directory = new File(courseName);
        if (directory.exists()) {
            netpw.write("030~ Course was successfully created!\n");
            netpw.flush();
            return directory;
        }
        while (true) {
            try {
                success = directory.mkdir();
            } catch (SecurityException e) {
                //netpw.write("032~ Course could not be created.\n");
                //netpw.flush();
            }

            if (success) {
                netpw.write("030~ Course was successfully created!\n");
                netpw.flush();
                break;
            } else {
                netpw.write("032~ Course was not successfully created.\n");
                netpw.flush();
            }
        }
        return directory;
    }

    public void createQuiz(String courseName, String quizName) {

        boolean success = false;
        File f = new File(courseName, quizName + ".txt");
        try {
            success = f.createNewFile();
        } catch (IOException e) {
            //return "Quiz could not be created.";
        }

        if (success) {
            netpw.write("030~ Quiz was successfully created!\n");
            netpw.flush();
        } else {
            netpw.write("032~ Quiz was not successfully created.\n");
            netpw.flush();
        }
    }

    public void deleteQuiz(String courseName, String quizName) {
        File f = new File(courseName, quizName + ".txt");
        if (f.delete()) {
            netpw.write("030~ The quiz " + quizName + " has successfully been deleted\n");
            netpw.flush();
        } else {
            netpw.write("032~ The quiz " + quizName + " was unable to be deleted\n");
            netpw.flush();
        }
    }

    public void addQuestionTitle(String courseName, String quizName, String question) {
        File f = new File(courseName, quizName + ".txt");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(f, true);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return;
        }
        PrintWriter pw = new PrintWriter(fos);
        pw.println(question);
        pw.close();
        netpw.write("030~ Question has successfully been added!\n");
        netpw.flush();
    }
    //* put question above this before the number of answer choices print out
    //* this will need to be put in a for loop and ask the user how many answer choices they want to add, the index i will equal the answer choice number
    public ArrayList<String> addAnswerChoice(String courseName, String quizName, String answer,
                                                    ArrayList<String> answerChoices, int answerChoiceNumber) {
        answerChoices.add(answer);
        String s = String.valueOf(Character.toChars(answerChoiceNumber + 64));
        String answerFormat = s + ") " + answer;

        File f = new File(courseName, quizName + ".txt");

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(f, true);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return null;
        }

        //FileOutputStream fos = new FileOutputStream(f, true);
        PrintWriter pw = new PrintWriter(fos);
        pw.println(answerFormat);
        pw.close();

        netpw.write("030~ Answer choice " + answerChoiceNumber + " has successfully been added!\n");
        netpw.flush();
        return answerChoices;
    }

    public void addSpace(String courseName, String quizName) {
        File f = new File(courseName, quizName + ".txt");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(f, true);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return;
        }
        PrintWriter pw = new PrintWriter(fos);
        pw.println();
        pw.close();
        netpw.write("030~ Correct answer has been added to answer sheet!\n");
        netpw.flush();
    }

    // in the main method, System.out.println("Which answer choice is the correct answer? 1 is A, 2 is B, etc...");
    // have this method called right after the for loop for writing out a question
    public void chooseCorrectAnswer(String courseName, String quizName, int correctAnswer, ArrayList<String>
            answerChoices) {
        String s = String.valueOf(Character.toChars(correctAnswer + 64));
        String correct = answerChoices.get(correctAnswer - 1);
        File f1 = new File(courseName, quizName + "answersheet.txt");
        FileOutputStream fos1;
        try {
            fos1 = new FileOutputStream(f1, true);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return;
        }
        PrintWriter pw1 = new PrintWriter(fos1);
        pw1.println(s + ") " + correct);
        pw1.close();
        netpw.write("030~ Correct answer has been added to answer sheet!\n");
        netpw.flush();
    }

    public ArrayList<String> displayQuizLineNumbers(String courseName, String quizName) {
        ArrayList<String> quiz = new ArrayList<>();
        ArrayList<String> quiz1 = new ArrayList<>();
        File f = new File(courseName, quizName + ".txt");
        FileReader fr;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return null;
        }
        BufferedReader bfr = new BufferedReader(fr);
        String line = null;
        try {
            line = bfr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        while (line != null) {
            quiz.add(count + ": " + line);
            quiz1.add(line);
            count++;
            try {
                line = bfr.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : quiz) {
            netpw.write("028~" + s + "\n");
            netpw.flush();
        }

        String choices = "";
        for (int i = 0; i < quiz1.size(); i++) {
            if (i == quiz1.size() - 1) {
                choices = choices + quiz1.get(i);
                break;
            }
            choices = choices + quiz1.get(i) + "@";
        }


        netpw.write("034~" + choices + "\n");
        netpw.flush();

        netpw.write("030~ " + "\n");
        netpw.flush();
        return quiz1;
    }

    // ask the user to enter the number of the first line and the number to the left of the blank line at the end of
    // the question
    // use the returned arraylist from the displayLineNumbers method
    public void removeQuestion(int firstLine, int secondLine, ArrayList<String> quiz, String courseName,
                                      String quizName) {
        secondLine = secondLine + 1;
        quiz.subList(firstLine, secondLine).clear();
        File fNew = new File(courseName, quizName + ".txt");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fNew);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return;
        }
        PrintWriter pw = new PrintWriter(fos);
        for (String s : quiz) {
            pw.println(s);
        }
        pw.close();
        netpw.write("030~ Question has successfully been removed!\n");
        netpw.flush();
    }

    public ArrayList<String> displayLinesForAnswerSheet(String courseName, String quizName) {
        ArrayList<String> answerSheet = new ArrayList<>();
        ArrayList<String> answerSheet1 = new ArrayList<>();
        File f1 = new File(courseName, quizName + "answersheet.txt");
        FileReader fr1;
        try {
            fr1 = new FileReader(f1);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return null;
        }
        BufferedReader bfr1 = new BufferedReader(fr1);
        String line1 = null;
        try {
            line1 = bfr1.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int counter = 0;
        while (line1 != null) {
            answerSheet.add(counter + ": " + line1);
            answerSheet1.add(line1);
            counter++;
            try {
                line1 = bfr1.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bfr1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : answerSheet) {
            netpw.write("028" + s + "\n");
            netpw.flush();
        }

        String str = convertToString(answerSheet1);
        netpw.write("034~ " + str + "\n");
        netpw.flush();

        netpw.write("030~ " +  "\n");
        netpw.flush();
        return answerSheet1;
    }

    public void removeAnswerFromAnswerSheet(String courseName, String quizName, ArrayList<String> answerSheet,
                                                   int lineNumber) {

        answerSheet.remove(lineNumber);
        File f2 = new File(courseName, quizName + "answersheet.txt");
        FileOutputStream fos2;
        try {
            fos2 = new FileOutputStream(f2);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return;
        }
        PrintWriter pw2 = new PrintWriter(fos2);
        for (String s : answerSheet) {
            pw2.println(s);
        }
        pw2.close();
        netpw.write("030~ Correct answer has successfully been removed from answer sheet!\n");
        netpw.flush();

    }

    public ArrayList<String> printQuiz(String courseName, String quizName) {
        ArrayList<String> scores = new ArrayList<>();
        File f2 = new File(courseName, quizName + ".txt");
        FileReader fr2;
        try {
            fr2 = new FileReader(f2);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return null;
        }
        BufferedReader bfr2 = new BufferedReader(fr2);
        String line2 = null;
        try {
            line2 = bfr2.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line2 != null) {
            scores.add(line2);
            try {
                line2 = bfr2.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bfr2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str2 = convertToString(scores);
        netpw.write("034~" + str2 + "\n");
        netpw.flush();

        netpw.write("030~ Success\n");
        netpw.flush();
        return scores;
    }

    public int numQuestions(ArrayList<String> scores) {
        int counter = 0;
        for (String score : scores) {
            if (score.equals("")) {
                counter++;
            }
        }
        netpw.write("038~" + counter + "\n");
        netpw.flush();

        netpw.write("030~ Success\n");
        netpw.flush();
        return counter;
    }

    public int printIndividualQuestion(ArrayList<String> scores, int indexOfBlank) {
        int j = 0;
        for (j = indexOfBlank; j < scores.size(); j++) {
            System.out.println(scores.get(j));
            indexOfBlank++;
            if (scores.get(j).equals("")) {
                j = indexOfBlank;
                break;
            }
        }
        netpw.write("038~" + j + "\n");
        netpw.flush();

        netpw.write("030~ Success\n");
        netpw.flush();
        return j;
    }

    public void addPoints(String courseName, String quizName, int numPoints, ArrayList<Integer> points) {
        points.add(numPoints);
        File f2 = new File(courseName, quizName + "points.txt");
        FileOutputStream fos2;
        try {
            fos2 = new FileOutputStream(f2);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return;
        }
        PrintWriter pw2 = new PrintWriter(fos2);
        for (Integer s : points) {
            pw2.println(s);
        }
        pw2.close();
        netpw.write("030~ Success\n");
        netpw.flush();
    }

    public void gradeStudent(String courseName, String studentSubmissions, String quizName,
                                    String studentAnswerSheet) {
        ArrayList<Integer> points = new ArrayList<>();
        File f5 = new File(courseName, quizName + "points.txt");
        FileReader fr5;
        try {
            fr5 = new FileReader(f5);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return;
        }
        BufferedReader bfr5 = new BufferedReader(fr5);
        String line5 = null;
        try {
            line5 = bfr5.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line5 != null) {
            points.add(Integer.valueOf(line5));
            try {
                line5 = bfr5.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bfr5.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (points.isEmpty()) {
            netpw.write("030~ Points have not been assigned to this quiz yet, " +
                    "please choose option 6 to assign points\n");
            netpw.flush();
            return;
        }
        ArrayList<String> answers = new ArrayList<>();
        ArrayList<String> studentAnswers = new ArrayList<>();
        File f1 = new File(courseName, quizName + "answersheet.txt");
        FileReader fr1;
        try {
            fr1 = new FileReader(f1);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return;
        }
        BufferedReader bfr1 = new BufferedReader(fr1);
        String line1 = null;
        try {
            line1 = bfr1.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line1 != null) {
            answers.add(line1.toLowerCase().substring(0, 1));
            try {
                line1 = bfr1.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bfr1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f = new File(studentSubmissions, studentAnswerSheet + ".txt");
        FileReader fr;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return;
        }
        BufferedReader bfr = new BufferedReader(fr);
        String line = null;
        try {
            line = bfr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            studentAnswers.add(line);
            try {
                line = bfr.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> scores = new ArrayList<>();
        File f3 = new File(courseName, quizName + ".txt");
        FileReader fr2;
        try {
            fr2 = new FileReader(f3);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return;
        }
        BufferedReader bfr2 = new BufferedReader(fr2);
        String line2 = null;
        try {
            line2 = bfr2.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line2 != null) {
            scores.add(line2);
            try {
                line2 = bfr2.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bfr2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f2 = new File(studentSubmissions, studentAnswerSheet + ".txt");
        FileOutputStream fos2;
        try {
            fos2 = new FileOutputStream(f2, true);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return;
        }
        PrintWriter pw2 = new PrintWriter(fos2);
        int score = 0;
        int total = 0;
        int indexOfBlank = 0;
        pw2.println();
        pw2.println();
        pw2.println("Student Grading Report: ");
        pw2.println();
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).equals(studentAnswers.get(i))) {
                score = score + points.get(i);
                pw2.println("Student got this question correct!");
                pw2.println(points.get(i) + "/" + points.get(i));
            } else {
                pw2.println("Student answered this question incorrectly!");
                pw2.println("0/" + points.get(i));
            }
            total = total + points.get(i);
            for (int j = indexOfBlank; j < scores.size(); j++) {
                pw2.println(scores.get(j));
                indexOfBlank++;
                if (scores.get(j).equals("")) {
                    j = indexOfBlank + 1;
                    break;
                }
            }
        }
        pw2.println("Total Score: " + score + "/" + total);
        pw2.close();
        netpw.write("030~ Success\n");
        netpw.flush();
    }

    public File viewStudentSubmission(String student, String studentSubmission) {
        ArrayList<String> studentAnswers = new ArrayList<>();
        File f = new File(student, studentSubmission + ".txt");
        FileReader fr;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return null;
        }
        BufferedReader bfr = new BufferedReader(fr);
        String line = null;
        try {
            line = bfr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            studentAnswers.add(line);
            try {
                line = bfr.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String studentAnswer : studentAnswers) {
            netpw.write("028~ " + studentAnswer + "\n");
            netpw.flush();
        }
        netpw.write("030~ Success\n");
        netpw.flush();
        return f;
    }

    public ArrayList<String> printAnswers(String studentSubmissions, String studentAnswerSheet) {
        ArrayList<String> studentAnswers = new ArrayList<>();
        File f2 = new File(studentSubmissions, studentAnswerSheet + ".txt");
        FileReader fr2;
        try {
            fr2 = new FileReader(f2);
        } catch (FileNotFoundException e) {
            netpw.write("032~ The file was not found.\n");
            netpw.flush();
            return null;
        }
        BufferedReader bfr2 = new BufferedReader(fr2);
        String line2 = null;
        try {
            line2 = bfr2.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line2 != null) {
            studentAnswers.add(line2);
            try {
                line2 = bfr2.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bfr2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        studentAnswers.removeIf(s -> s.contains("2"));

        String str2 = convertToString(studentAnswers);
        netpw.write("034~" + str2 + "\n");
        netpw.flush();

        netpw.write("030~ Success\n");
        netpw.flush();
        return studentAnswers;
    }

    public int printIndividualAnswer(ArrayList<String> studentAnswers, int indexNewLine) {
        int i = 0;
        for (i = indexNewLine; i < studentAnswers.size(); i++) {
            netpw.write("028~ " + studentAnswers.get(i) + "\n");
            netpw.flush();
            indexNewLine++;
            i = indexNewLine;
            break;
        }
        netpw.write("038~" + i + "\n");
        netpw.flush();
        netpw.write("030~ Success\n");
        netpw.flush();
        return i;
    }
/////// aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    public static void randomizeQuestions(String courseName, String quizName) throws IOException {
        ArrayList<String> randomized = new ArrayList<>();
        File f = new File(courseName, quizName + ".txt");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        File f1 = new File(courseName, quizName + "answersheet.txt");
        FileReader fr1 = new FileReader(f1);
        BufferedReader bfr1 = new BufferedReader(fr1);
        String line = bfr.readLine();
        int counter = 0;
        while (line!= null) {
            randomized.add(line);
            if (line.equals("")) {
                String line1 = bfr1.readLine();
                if (counter >= 70) {
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    counter +=10;
                } else if (counter >= 63) {
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    counter +=9;
                } else if (counter >= 56) {
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    counter +=8;
                } else if (counter >= 49) {
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    counter +=7;
                } else if (counter >= 42) {
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    counter +=6;
                } else if (counter >= 35) {
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    counter +=5;
                } else if (counter >= 28) {
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    counter +=4;
                } else if (counter >= 21) {
                    randomized.add("");
                    randomized.add("");
                    randomized.add("");
                    counter +=3;
                } else if (counter >= 14) {
                    randomized.add("");
                    randomized.add("");
                    counter +=2;
                } else if (counter >= 7) {
                    randomized.add("");
                    counter++;
                }
                randomized.add(counter, line1.toLowerCase().substring(0, 1));
            }
            counter++;
            line = bfr.readLine();
        }
        bfr.close();
        ArrayList<String> randomized1 = new ArrayList<>();
        counter = 0;
        for (int i = 0; i < randomized.size(); i++) {
            int indexOfBlank = 0;
            if (randomized.get(i).equals("")) {
                indexOfBlank = i;
            }
            if (indexOfBlank != 0) {
                String question = "";
                int j = 0;
                for (j = counter; j < indexOfBlank; j++) {
                    question = question + randomized.get(j) + "\n";
                }
                randomized1.add(question);
                counter = j + 1;
            }
        }

        for (int i = 0; i < randomized1.size(); i++) {
            if (randomized1.get(i).length() == 0) {
                randomized1.remove(i);
            }
        }
        Collections.shuffle(randomized1);
        System.out.println(randomized1);
        ArrayList<String> randomizedAnswers = new ArrayList<>();
        for (String value : randomized1) {
            String s = value.substring(value.length() - 2, value.length() - 1);
            randomizedAnswers.add(s);
        }
        File f2 = new File(courseName, quizName + "answersheet.txt");
        FileOutputStream fos = new FileOutputStream(f2);
        PrintWriter pw = new PrintWriter(fos);

        for (String randomizedAnswer : randomizedAnswers) {
            pw.println(randomizedAnswer);
        }
        pw.close();
        File f3 = new File(courseName, quizName + ".txt");
        FileOutputStream fos1 = new FileOutputStream(f3);
        PrintWriter pw1 = new PrintWriter(fos1);
        for (String randomized11 : randomized1) {
            pw1.println(randomized11.substring(0, randomized11.length() - 2));
        }
        pw1.close();
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

}
