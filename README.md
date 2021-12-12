# Project-5-CS-180
## Group members

- Andrew Paul 
- Jashan Shukla - Upload Report on Vocareum
- Oviyaal Kannan Jegadesan Valsala - Upload Presentation Video 
- Robert Peng - Upload classes and document on Vocareum workspace
- Seojeong(Jennifer) Park
##  How to compile and run 

- Our program can be run by running Network Server class then running Network Client class. 
- If the gui display shows "Error with socket", change the host name to whatever your host name is of your computer.
- All the implementation is done in that class. Expected inputs and outputs are attached at the end of this document. 

## NetworkClient.java

- This class deals with the client side of the network interaction.

- This class contains class Gui which implements ActionListener

|Method|Description|
|------|------|
|public void createGUI()|method that creates the main user interface of the program|
|public void actionPerformed(ActionEvent e)|method called when a button is clicked|
|public String getText()|returns a string text|
|public void setText()|sets text with input string parameter|
|public void appendText()|appends the string with input string parameter|
|public boolean getGuiFlag()|return the boolean of guiFlag|
|public void resetGuiFlag()|sets the boolean guiFlag to false|
|public boolean getIsRunning()|return the boolean of isRunning|


- Summary of class NetworkClient that implements Runnable

```
1) main method in this class uses 
SwingUtilities.invokeLater(new Runnable() {
    public void run() {
        gui.createGUI();
    }
}); 
which is used within the thread to run code within the Event Dispatch Thread.

2) Connects itself with the server with the socket

3) Welcomes the user. Log them in.
Then, guides the user accordingly: 
- Students to take a quiz, submit it, and view their grading report after teacher grades.
- Teachers to create course, add, delete quizzes and questions for each course, and view or grade 
students' quiz submissions.
    
   
```

## NetworkServer.java

- This class deals with the server side of the network interaction.
- Summary of class NetworkServer

```
 1) The class implements interface Runnable to create a thread, starting the thread casues the 
 object's run method to be called in that separately executing thread.
 
 2) Opens up a socket and wait for clients to connect
```

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
|public LoginServer()| constructs objects with no input parameters|

- Method Summary 

|Method|Description|
|------|------|
|public boolean addUsername(String username)|creates username for a new user|
|public boolean addPassword(String password)|creates password for a new user|
|public boolean alreadyUser(String username, String password)|login of an existing user|
|public boolean numberOfExistingUsers()|returning the number of users present in the record file containing usernames|
|public boolean editUsername(String username, String newUsername)|used when users want to edit their username|
|public void editPassword(String password, String newPassword)|used when users want to edit their password|
|public boolean deleteUser(String username, String password)|used when users want to delete their account|

## StudentServer.java

- This class deals with student interaction with the program.

|Modifier and Type|Field|Description|
|------|---|---|
|private String |username|stores username|

-Constructor Summary

|Method|Description|
|------|------|
|public StudentServer()|constructs student object using default constructor|
- Method Summary 
|Method|Description|
|------|------|
|public void takeQuiz()|students can take any quiz from any courses they want|
|public void submitQuiz()|students can submit their timestamped-answer files|
|public void viewGradedQuiz()|student can view any graded quiz|
|public void takeQuizViaUpload()|student can upload their answer file to any courses|
## TeacherServer.java
- This class deals with teacher interaction with the program.
-Method Summary
|Method|Description|
|------|------|
|public File createCourse(String courseName)|teacher can create course|
|public void createQuiz(File courseName, String quizName)|teacher can create a quiz|
|public void deleteQuiz(File courseName, String quizName)|teacher can delete a quiz|
|public void addQuestionTitle(File courseName, String quizName, String question)|teacher can add question title to a quiz|
|public ArrayList< String > addAnswerChoice(File courseName, String quizName, String answer, ArrayList< String > answerChoices, int answerChoiceNumber)|prints out the answer choices of the question|
|public void addSpace(File courseName, string quizName)|teacher can add a space between the questions|
|public void chooseCorrectAnswer(File courseName, String quizName, int correctAnswer, ArrayList< String > answerChoices)|teacher can add correct answers to an answer sheet|
|public ArrayList< String > displayQuizLineNumbers(File courseName, String quizName)|the quiz file displays the line numbers|
|public void removeQuestion(int firstLine, int secondLine, ArrayList< String > quiz, File courseName, String quizName)|teacher can remove question from the question file|
|public ArrayList< String > displayLinesForAnswerSheet(File courseName, String quizName)|the answer sheet displays the line numbers|
|public void removeAnswerFromAnswerSheet(File courseName, String quizName, ArrayList< String > answerSheet, int lineNumber)|teacher can remove answer from the answer sheet when the question is removed|
|public ArrayList< String > printQuiz(File courseName, String quizName)|prints out quizzes on the quiz file|
|public int numQuestions(ArrayList< String > scores)|returns the number of questions|
|public int printIndividualQuestion(ArrayList< String > scores, int indexOfBlank)|returns the index of blank|
|public void addPoints(File courseName, String quizName, int numPoints, ArrayList< Integer > points)|add the points earned from a quiz|
|public void gradeStudent(File courseName, File studentSubmissions, String quizName, String studentAnswerSheet)|teacher can grade a student's quiz|
|public void viewStudentSubmission(File student, String studentSubmission)|teacher can view student's quiz submissions|
|public ArrayList< String > printAnswers(File studentSubmissions, String studentAnswerSheet)|stores student's answers in a String arrayList|
|public int printIndividualAnswer(ArrayList< String > studentAnswers, int indexNewLine)|return index of a new line|


