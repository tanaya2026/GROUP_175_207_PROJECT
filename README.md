# Study Buddy Finder

**CSC207 Group Project**

---

## Authors and Contributors:
- [**Tanaya Datar**](https://github.com/tanaya2026)
- [**Cooper Bolinius**](https://github.com/cbolinius)
- [**Alexander Pascalev**](https://github.com/a1exp4sc)
- [**Jinbo Chang**](https://github.com/JinboChang)
- [**Harris Ahmed**](https://github.com/harriss4567)

---

## Project Summary


**Purpose**

The Study Buddy Finder project is a software tool designed to help students connect with peers for collaborative study sessions. By matching users based on shared availability and either courses or program of study, the tool promotes effective collaboration and shared accountability among students. This project addresses the common challenge of finding reliable study partners who share similar schedules and academic focuses.

<br/>

**Problem Solved**

Many students struggle to find study partners who align with their courses and availability, making collaborative learning inefficient. This software streamlines the process by automating matches based on shared courses or program, reducing the time and effort required to coordinate study sessions.

<br/>

**Target Users**  
- Students seeking structured studying/collaboration.
- Those who value accountability in their study routines.
- Individuals looking to expand their academic networks and make friends in your classes/program.

<br/>

---

## Table of Contents

1. [Features](#features)
2. [Installation Instructions](#installation-instructions)
3. [Usage Guide](#usage-guide)
4. [License](#license)
5. [Feedback](#feedback)
6. [Contributions](#contributions)

<br/>

---

## Features

**Core Features**  

1. **User Account Management**
   - **Create Account**: Users can input personal details (name, email, courses, bio, availability).
   - **Edit Profile**: Modify details such as availability, email, bio, and enrolled courses.

2. **Matching Algorithm**
   - Matches students by shared courses and availability.
   - Expands search to program-level matches if no course-specific matches exist.

3. **Scheduler Integration**
   - Leverages the *Slotify API* for scheduling and availability management.
   - Allows users to set hourly time slots (9 AM–5 PM).
   - Note that the actual calendar booking of meetings is out of the project scope.

4. **User Interface**
   - Simple and intuitive interface for account creation and match display.
   - Notifies users when no matches are found and offers expanded search options.

5. **Account Security**
   - Login and logout functionality to protect user data.

<br/>

**Technical Details for Developers**  

- **Entity Design**:
  - **User**: Contains attributes like username, name, courses, program of study, and matches, as well as storing the user's Slotify resource and scheduler UUIDs.
  - **Course**: Includes course code and title.
  - **Timeslot**: Represents availability as 1-hour blocks between 9 AM–5 PM for all seven days of the week.

- **APIs Used**:
  - [Slotify API](https://slotify.ca/api/page/introduction): Facilitates availability management and scheduling functionalities.

- **Clean Architecture Adherence**:
  - All project files are packaged by layer according to CA.
  - All code is divided into distinct use cases and respective Views.
  - Each use case includes the various files for CA, including Controllers, Interactors, Input/Output Data/Boundaries, ViewModels, Presenters, and States.
  - API calls were integrated into entities using dependency injection.

<br/>

**Example Usage**  
- **Student A** creates an account and lists their availability to be from 10 AM–12 PM on weekdays and selects *CSC207* as one of their courses.  
- The tool matches **Student A** with **Student B**, who shares the same course and is available from 11 AM–1 PM on Mondays and Thursdays.  

<br/>

This program is ideal for students who value structured, collaborative study sessions and want a tool to simplify finding compatible study partners. The integration of scheduling and academic data ensures the matches are both relevant and practical.

Below are our project's user stories, along with who in our team was primarily responsible for each.

<br/>

**User Stories** <br/><br/>
**Team user story (core functionality):** <br/>
As a student, I want to be matched with a study buddy who is in the same course as me and is available during my study times, so that we can collaborate effectively and keep each other accountable.

**Tanaya:** <br/>
I want to be able to create an account and fill in personal information (such as name, courses, bio, etc.) and view the profiles of potential study buddy matches. I also want to be able to view the HomePage when the application is opened.

**Cooper:** <br/>
I want to be effectively matched with other students I can study with based on our common availability and shared courses.

**Jinbo:** <br/>
I want to be able to edit my profile/personal details, including name, availability, bio, etc.

**Alex:** <br/>
Of all study buddy matches with common availability, if no matches are found taking my same courses, I want to be able to expand my search to find other study buddies in my same program, even if we aren’t currently taking the same courses.

**Harris:** <br/>
Once I have made an account, I want to be able to log into my account and see my potential study buddy matches. I also want to be able to log out.

<br/>

---

## Installation Instructions

**Instructions and Prerequesits**
- Software: Java 17 (JDK 17+), IntelliJ
   - As A student you can use https://www.jetbrains.com/shop/eform/students and create a InellliJ Ultimate account for free with official installation instructions here: https://www.jetbrains.com/help/idea/installation-guide.html
   - The application requires Java 17+ to run so you use the following link as a guide: https://www.jetbrains.com/help/idea/sdk.html#add_global_sdk
- Packages: Maven 3.9+
   - Installation instructions for Maven in IntelliJ: https://www.jetbrains.com/help/idea/maven-support.html 
- Libraries: JSON, Java Swing
   - Guide for installing packages in IntelliJ with JSON as an example: https://www.jetbrains.com/help/idea/json.html 

**OS Requirements**: 
The application has no OS requirements and will work on any OS system.

---

## Usage Guide

Below is a link to a brief YouTube video demonstrating the functionality of the Study Buddy Finder program.

[Watch Demonstration](https://youtu.be/9_iOO7v0kOg)  

---

## License
This project is in the **public domain** under the [CC0 1.0 Universal (Public Domain Dedication)](./LICENSE).

---

## Feedback

We value your feedback on our application Study Buddy Finder! Whether you have suggestions for improvements, found a bug, or want to share your thoughts, suggestions, we’d love to hear from you!

**How to Provide Feedback**

You can share your feedback in the following ways:
1. **Fill out our feedback form**: (https://forms.gle/GZGMxP4RrLb5YGH19)
2. **Email us directly**: tanaya.datar@mail.utoronto.ca

<br/>

**Main topics we aim to cover in the feedback form**

In the feedback form, the main questions will encompass:

- **General feedback**: Share your thoughts about the project.
- **Feature suggestions**: Recommend new features or improvements.
- **Bug reports**: Let us know if something isn’t working as expected.
- **User experience**: Tell us how easy or difficult the project was to use.
- **Additional comments**: Any other feedback you'd like to provide.

<br/>

**What We're Looking For i.e. what counts as valid feedback**

Rules for Valid Feedback:
1. The comments and suggeestions must pertain to our application.
2. The suggestions have to been within the scope of the abilities and resources of a 2nd year Computer Science Student.
3. If reporting an issue, please include information about the issue like error messages and screenshots if possible.
4. Avoid vague statesments, for example "This could be improved", instead use statements like "This can be improved by xyz[state a reason]"
5. Please state your feedback in a positive and constructive manner while being respectful.

**What to expect when submitting feedback**

1. Our team will read the feedback and reach out if required within 48 hours of submissions of feedback.
2. Our team will try our best to implement the suggestions and **WILL** fix any reported bugs.
3. Our team will maintain confidentiality of the feedback recieved.


---

## Contributing to the Project

We welcome contributions to our project and value collaboration from the community. Please read the following guidelines to understand how to contribute effectively.

## How to Contribute
1. **Fork the Repository**:
   - Navigate to the [GitHub repository](https://github.com/tanaya2026/GROUP_175_207_PROJECT.git).
   - Click the "Fork" button in the top-right corner to create your copy of the repository.

2. **Clone Your Fork**:
   - Clone your fork to your local machine using:
     ```bash
     git clone https://github.com/your-username/GROUP_175_207_PROJECT.git
     ```
   - Replace `your-username` with your GitHub username.

3. **Create a Branch**:
   - Create a feature branch for your changes:
     ```bash
     git checkout -b feature/your-feature-name
     ```

4. **Make Changes**:
   - Implement your changes in the new branch.
   - Ensure your code follows the repository's coding standards.

5. **Test Your Changes**:
   - Run tests to ensure your changes work as intended and do not introduce regressions.

6. **Submit a Pull Request**:
   - Push your changes to your fork:
     ```bash
     git push origin feature/your-feature-name
     ```
   - Navigate to the main repository and create a pull request (PR) from your fork to the main branch.

## Guidelines for Creating a Good Merge Request
- **Descriptive Title and Description**:
  Provide a meaningful title and describe the purpose of your changes in the PR description. Include references to any related issues.

- **Adhere to Standards**:
  Ensure your code adheres to the repository's coding standards and passes all tests.

- **Keep Changes Minimal**:
  Submit small, focused PRs that address a single feature or bug to make review easier.

## Protocols for Reviewing Contributions
1. **Initial Review**:
   - All PRs are reviewed within 3 business days by one or more maintainers.
   - Reviewers check for adherence to the project's goals, code quality, and potential impacts on existing functionality.

2. **Feedback and Revisions**:
   - If changes are needed, maintainers will leave comments on the PR.
   - Contributors are expected to address feedback promptly and update the PR.

3. **Final Approval and Merge**:
   - Once all feedback is resolved, the PR is approved and merged into the main branch.
   - Contributors are credited for their work in the project's change log.

## Closing Contributions
If contributions are closed, this section will state:  
*"At this time, we are not accepting external contributions to the project."*


<br/>
<br/>
