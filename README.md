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

