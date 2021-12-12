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
- Summary of class NetworkClient

```
 1) The class implements interface Runnable to create a thread, starting the thread casues the 
 object's run method to be called in that separately executing thread.
 
 2) Opens up a socket and wait for clients to connect
```




