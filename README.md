# Project-5-CS-180
## Group members

- Andrew Paul 
- Jashan Shukla
- Oviyaal Kannan Jegadesan Valsala - Upload Report on Vocareum 
- Robert Peng - Upload classes and document on Vocareum workspace
- Seojeong(Jennifer) Park
##  How to compile and run 

- Our program can be run by running Network Server class then running Network Client class. 
- If the gui display shows "Error with socket", change the host name to whatever your host name is of your computer.
- All the implementation is done in that class. Expected inputs and outputs are attached at the end of this document. 
 


- Constructor Summary

|Method|Description|
|------|------|
|public ExistingUser (String message)|Constructor that super message from the Exception class.|

## LoginServer.java

- This class deals with the login feature of our program.

- Field Summary 

|Modifier and Type|Field|Description|
|------|---|---|
|private ArrayList< String >|usernamesArray|stores usernames|
|private ArrayList< String >|passwordsArray|stores passwords|
|private int|userNumber|unique number for each user|
|private String|username|username created by a user|
private String|password|password created by a user|

- Constructor Summary 

|Method|Description|
|------|------|
|public Login(String username, String password)| constructs objects with two input parameters|

- Method Summary 

|Method|Description|
|------|------|
|public void addUsername(String username)|creates username for a new user|
|public void addPassword(String password)|creates password for a new user|
|public String alreadyUser(String username, String password)|login of an existing user|
|public int numberOfExistingUsers()|returning the number of users present in the record file containing usernames|
|public void editUsername(String username, String newUsername)|used when users want to edit their username|
|public void editPassword(String password, String newPassword)|used when users want to edit their password|


## StudentServer.java

- This class deals with student interaction with the program.

-Constructor Summary

|Method|Description|
|------|------|
|public Student(String username)|constructs student objects using input parameter username|

- Method Summary 

|Method|Description|
|------|------|
|public void takeQuiz(Scanner scanner)|students can take any quiz from any courses they want|
|public void submitQuiz(Scanner scanner)|students can submit their timestamped-answer files|
|public void viewGradedQuiz(Scanner scanner)|student can view any graded quiz|
|public void takeQuizViaUpload(Scanner scanner)|student can upload their answer file to any courses|

## TeacherServer.java

- This class deals with teacher interaction with the program.

-Method Summary

|Method|Description|
|------|------|
|public static File createCourse(String courseName)|teacher can create course|
|public static String createQuiz(File courseName, String quizName)|teacher can create a quiz|
|public static String deleteQuiz(File courseName, String quizName)|teacher can delete a quiz|
|public static void addQuestionTitle(File courseName, String quizName, String question)|teacher can add question title to a quiz|
|public static ArrayList< String > addAnswerChoice(File courseName, String quizName, String answer, ArrayList< String > answerChoices, int answerChoiceNumber)|prints out the answer choices of the question|
|public static void addSpace(File courseName, string quizName)|teacher can add a space between the questions|
|public static void chooseCorrectAnswer(File courseName, String quizName, int correctAnswer, ArrayList< String > answerChoices)|teacher can add correct answers to an answer sheet|
|public static ArrayList< String > displayQuizLineNumbers(File courseName, String quizName)|the quiz file displays the line numbers|
|public static void removeQuestion(int firstLine, int secondLine, ArrayList< String > quiz, File courseName, String quizName)|teacher can remove question from the question file|
|public static ArrayList< String > displayLinesForAnswerSheet(File courseName, String quizName)|the answer sheet displays the line numbers|
|public static void removeAnswerFromAnswerSheet(File courseName, String quizName, ArrayList< String > answerSheet, int lineNumber)|teacher can remove answer from the answer sheet when the question is removed|
|public static ArrayList< String > printQuiz(File courseName, String quizName)|prints out quizzes on the quiz file|
|public static int numQuestions(ArrayList< String > scores)|returns the number of questions|
|public static int printIndividualQuestion(ArrayList< String > scores, int indexOfBlank)|returns the index of blank|
|public static void addPoints(File courseName, String quizName, int numPoints, ArrayList< Integer > points)|add the points earned from a quiz|
|public static void gradeStudent(File courseName, File studentSubmissions, String quizName, String studentAnswerSheet)|teacher can grade a student's quiz|
|public static void viewStudentSubmission(File student, String studentSubmission)|teacher can view student's quiz submissions|
|public static ArrayList< String > printAnswers(File studentSubmissions, String studentAnswerSheet)|stores student's answers in a String arrayList|
|public static printIndividualAnswer(ArrayList< String > studentAnswers, int indexNewLine)|return index of a new line|

## Testing steps

**- Test 1: Teacher creating a course and quiz**
  - To create quiz use Manage Course, then add quiz
  - To create questions, after adding the quiz, use Manage Quiz, then add questions
- After closing the program, run the NetworkClient class again(If needed).

```
Are you a current member? Type in yes or no.
Input: [yes]

Enter the number of the action that you want to do
1. Do you want to login?
2. Do you want to delete your account?
3. Do you want to edit your account?
Input: [1]

Enter your username:
Input: [anne00]

Enter your password:
Input: [ann11]

Are you a student or a teacher? (type in (student) or (teacher))
Input: [teacher]

When inputting names of quizzes, courses, and student answers; please do not include-- .txt -- in your response
Please enter the number of the option that you would like to choose: 
1. Create Course
2. Manage Course
3. View/Grade Student Submissions
4. Exit
Input: [1]

Please enter the name of the course that you would like to create
Input: [English]

Please enter the number of the option that you would like to choose: 
1. Create Course
2. Manage Course
3. View/Grade Student Submissions
4. Exit
Input: [2]

Enter the course name that you would like to manage
Input: [English]

Please enter the number of the option that you would like to choose: 
1. Add Quiz
2. Delete Quiz
3. Manage Quiz
4. Exit
Input: [1]


Please enter the name of the quiz that you would like to create
Input: [englishquiz1]

Please enter the number of the option that you would like to choose: 
1. Add Quiz
2. Delete Quiz
3. Manage Quiz
4. Exit
Input: [3]

Please enter the quiz name that you would like to manage
Input: [englishquiz1]

Please enter the number of the option that you would like to choose: 
1. Add Question
2. Remove Question
3. Exit
Input: [1]

What is the question that you would like to add?
Input: [Who wrote The Great Gatsby]

How many answer choices would you like this question to have?
Input: [4]

Please enter the answer choice for option number 1
Input: [F. Scott Fitzgerald]

Please enter the answer choice for option number 2
F. Scott Fitzgerald
Input: [Virginia Woolf]

Please enter the answer choice for option number 3
F. Scott Fitzgerald
Virginia Woolf
Input: [Jane Austen]

Please enter the answer choice for option number 4
F. Scott Fitzgerald
Virginia Woolf
Jane Austen
Input: [William Shakespeare]

A) F. Scott Fitzgerald
B) Virginia Woolf
C) Jane Austen
D) William Shakespeare
Which answer choice is the correct answer? 1 is A, 2 is B, etc…
Input: [1]

Would you like to add another question for this quiz? Please enter yes or no
Input: [no]

Please enter the number of the option that you would like to choose: 
1. Add Question
2. Remove Question
3. Exit
Input: [3]

Please enter the number of the option that you would like to choose: 
1. Add Quiz
2. Delete Quiz
3. Manage Quiz
4. Exit
Input: [4]

Please enter the number of the option that you would like to choose: 
1. Create Course
2. Manage Course
3. View/Grade Student Submissions
4. Exit
Input: [4]

Thank you for logging in!

The process has finished. Please exit the GUI through clicking the X on the top right corner of the screen. 
If you need to use our program again, please run the program again.

```

**- Test 2: User creating an account and logging in**
```
Are you a current member? Type in yes or no.
Input: [no]

Do you want to create a new account? Type in yes or no
Input: [yes]

Enter your username: 
Input: [seojeong24]

Enter your password: 
Input: [seo_10]

The process has finished. Please exit the GUI through clicking the X on the top right/left corner of the screen. If you need to use our program again, please run the program again.

* (After closing the program, run the NetworkClinet class again.) *

Are you a current member? Type in yes or no.
Input: [yes]

Enter the number of the action that you want to do
1. Do you want to login?
2. Do you want to delete your account?
3. Do you want to edit your account?
Input: [1]

Are you a student or a teacher? (type in (student) or (teacher))
Input: [student]

1. Do you want to take a quiz?
2. Do you want to submit a quiz?
3. Do you want to view your grading report?
4. Exit
Input: [1]

1. Do you want to take your quiz via the terminal?
2. Do you want to take your quiz by uploading a file?
3. Exit
Input: [1]

Enter the course of the quiz you want to take.
Input: [English]

Enter the file name of the quiz you want to take.
Input: [englishquiz1]


 who wrote The Great Gatsby?
A) F. Scott Fitzgerald
B) Virginia Woolf
C) Jane Austen
D) William Shakespeare
Input: [A]

1. Do you want to take a quiz?
2. Do you want to submit a quiz?
3. Do you want to view your grading report?
4. Exit
Input: [2]

Enter the course of the quiz you want to submit.
Input: [English]

Enter the name of the quiz you want to submit.
Input: [englishquiz1]

 Submission success!

The process has finished. Please exit the GUI through clicking the X on the top right corner of the screen. 
If you need to use our program again, please run the program again.

```
**- Test 3: Teacher grading a quiz submitted by a student**

```
Enter your username:
Input: [anne00]

Enter your password: 
Input: [ann11]

Are you a student or a teacher? (type in (student) or (teacher))
Input: [teacher]

When inputting names of quizzes, courses, and student answers; please do not include-- .txt -- in your response
Please enter the number of the option that you would like to choose: 
1. Create Course
2. Manage Course
3. View/Grade Student Submissions
4. Exit
Input: [3]

englishquiz1seojeong24Q1answer.txt
englishquiz1seojeong24finalanswers.txt
Enter the number next to the option that you would like to choose
1. View Student's Submitted Quiz
2. Grade Student's Submitted Quiz
3. Exit
Input: [2]

What is the name of the course of the quiz you want to grade?
Input: [English]

englishquiz1.txt
englishquiz1points.txt
englishquiz1answersheet.txt
englishquiz1.txt
englishquiz1points.txt
englishquiz1answersheet.txt
What is the name of the quiz that you would like to grade?
Input: [englishquiz1]

englishquiz1seojeong24Q1answer.txt
englishquiz1seojeong24finalanswers.txt
What is the name of the student answer sheet that you would like to grade? Do NOT include .txt in the name. Please select the answer sheet that contains ‘finalanswers'.
Input: [englishquiz1seojeong24finalanswers]

who wrote The Great Gatsby?
A) F. Scott Fitzgerald
B) Virginia Woolf
C) Jane Austen
D) William Shakespeare
A
 A
How many points would you like to assign to this question?
Input: [3]

Enter the number next to the option that you would like to choose
1. View Student's Submitted Quiz
2. Grade Student's Submitted Quiz
3. Exit
Input: [3]

Please enter the number of the option that you would like to choose: 
1. Create Course
2. Manage Course
3. View/Grade Student Submissions
4. Exit
Input: [4]

Thank you for logging in!

The process has finished. Please exit the GUI through clicking the X on the top right corner of the screen. 
If you need to use our program again, please run the program again.

```
**- Test 4: Teacher viewing student’s quiz submission report**
```
Enter your username:
Input: [anne00]

Enter your password:
Input: [ann11]

Are you a student or a teacher? (type in (student) or (teacher))
Input: [teacher]

When inputting names of quizzes, courses, and student answers; please do not include-- .txt -- in your response
Please enter the number of the option that you would like to choose: 
1. Create Course
2. Manage Course
3. View/Grade Student Submissions
4. Exit
Input: [3]

englishquiz1seojeong24Q1answer.txt
englishquiz1seojeong24finalanswers.txt
Enter the number next to the option that you would like to choose
1. View Student's Submitted Quiz
2. Grade Student's Submitted Quiz
3. Exit
Input: [1]

englishquiz1seojeong24Q1answer.txt
englishquiz1seojeong24finalanswers.txt
Please enter the name of the student submission that you would like to view
Input: [englishquiz1seojeong24finalanswers]

 A
 2021-12-11-1-48-47 EST

Student Grading Report: 
 
 Student answered this question correctly!
 3/3
 who wrote The Great Gatsby?
 A) F. Scott Fitzgerald
 B) Virginia Woolf
 C) Jane Austen
 D) William Shakespeare
 
 Total Score: 3/3
Enter the number next to the option that you would like to choose
1. View Student's Submitted Quiz
2. Grade Student's Submitted Quiz
3. Exit
Input: [3]

Please enter the number of the option that you would like to choose: 
1. Create Course
2. Manage Course
3. View/Grade Student Submissions
4. Exit
Input: [4]

Thank you for logging in!

The process has finished. Please exit the GUI through clicking the X on the top right corner of the screen. 
If you need to use our program again, please run the program again.


