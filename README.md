# Project-5-CS-180
## Group members

- Andrew Paul 
- Jashan Shukla
- Oviyaal Kannan Jegadesan Valsala - Upload Report on Vocareum 
- Robert Peng - Upload classes and document on Vocareum workspace
- Seojeong(Jennifer) Park
##  How to compile and run 

- Our program can be run by a Main.java. All the implementation is done in that class. Expected inputs and outputs are attached at the end of this document. 
 


- Constructor Summary

|Method|Description|
|------|------|
|public ExistingUser (String message)|Constructor that super message from the Exception class.|

## Login.java

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

## Question.java

- This class has a method that randomizes the question order in the question file.

|Method|Description|
|------|------|
|public static void randomizedQuestions()|reads the question file and shuffles the order of the questions.|

## Student.java

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

## Teacher.java

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

- User creating account
```
Welcome to our Quiz Bank!
Are you a current member? Type in yes or no.

[no]

Do you want to create a new account?

[yes]

Enter your username:

[jennifer24]

Enter your password:

[hunn30]

Your account has successfully been created!
```

- Student interaction
```
Welcome to our Quiz Bank!
Are you a current member? Type in yes or no.

[yes]

1. Do you want to login?
2. Do you want to delete your account?
3. Do you want to edit your account?

[1]

Enter your username:

[jennifer24]

Enter your password:

[hunn30]

Login success!

Are you a student or a teacher?

[student]

1. Do you want to take a quiz?
2. Do you want to submit a quiz?
3. Do you want to view your grading report?
4. Exit

[1]

1. Do you want to take your quiz via the terminal?
2. Do you want to take your quiz by uploading a file?
3. Exit

[1]

Enter the course of the quiz you want to take.

[english]

Enter the name of the quiz you want to take.

[quiz1]

Quiz was not found!
```
- Teacher interaction to create course and add quiz questions
```
Welcome to our Quiz Bank!
Are you a current member? Type in yes or no.

[yes]

1. Do you want to login?
2. Do you want to delete your account?
3. Do you want to edit your account?

[1]

Enter your username:

[seojeong24]

Enter your password:

[jenn30]

Login success!
Are you a student or a teacher?

[teacher]

When inputting names of quizzes, courses, and student answers; please do not include-- .txt -- in your response
Please enter the number of the option that you would like to choose: 
1. Create Course
2. Manage Course
3. View/Grade Student Submissions
4. Exit

[2]

Enter the course name that you would like to manage

[science]

Please enter the number of the option that you would like to choose: 
1. Add Quiz
2. Delete Quiz
3. Manage Quiz
4. Exit

[3]

Please enter the quiz name that you would like to manage

[science1]

Please enter the number of the option that you would like to choose: 
1. Add Question
2. Remove Question
3. Exit

[1]

What is the question that you would like to add?

[What is the Powerhouse of cells?]

Question has successfully been added!
How many answer choices would you like this question to have?

[3]

Please enter the answer choice for option number 1

[Mitochondria]

Answer choice 1 has successfully been added!
Please enter the answer choice for option number 2

[Golgi apparatus]

Answer choice 2 has successfully been added!
Please enter the answer choice for option number 3

[Endoplasmic reticulum]

Answer choice 3 has successfully been added!
A) Mitochondria
B) Golgi apparatus
C) Endoplasmic reticulum
Which answer choice is the correct answer? 1 is A, 2 is B, etc...

[a]

This is not an integer, please try again!
Which answer choice is the correct answer? 1 is A, 2 is B, etc...

[1]
Correct answer has been added to answer sheet!
Would you like to add another question for this quiz? Please enter yes or no

[no]

Please enter the number of the option that you would like to choose: 
1. Add Question
2. Remove Question
3. Exit

[3]

Please enter the number of the option that you would like to choose: 
1. Add Quiz
2. Delete Quiz
3. Manage Quiz
4. Exit

[4]

Please enter the number of the option that you would like to choose: 
1. Create Course
2. Manage Course
3. View/Grade Student Submissions
4. Exit

[4]

Thank you for logging in!
```
