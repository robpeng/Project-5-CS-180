**some test cases (note that these test cases should be done in order from case 1 to case 4**

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
 
 Student got this question correct!
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
