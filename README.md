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


---

## Table of Contents

1. [Features](#features)
2. [Installation Instructions](#installation-instructions)
3. [Usage Guide](#usage-guide)
4. [License](#license)
5. [Feedback](#feedback)
6. [Contributions](#contributions)

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

<br/>

---

### Usage Guide
[Watch Demonstration](https://youtube.com/tutorial-link)  

---

## Installation Instructions

### Prerequisites
- Software: Java 17
- Packages: ?

---

---

## Contributions

### Our code will be closed for contribution
- Version control was utilized during development via Github

---

User stories:
Team user story (core functionality):
As a student, I want to be matched with a study buddy who is in the same course as me and is available during my study times, so that we can collaborate effectively and keep each other accountable.

Tanaya:
I want to be able to create an account and fill in my availability and personal information (such as name, courses, bio, etc.) and see a list of potential study buddy matches. I also want to be able to see the HomePage when the application is opened.

Cooper:
I want to be effectively matched with other students I can study with based on our common availability and shared courses.

Jinbo:
I want to be able to edit my profile/personal details, including name, availability, bio, etc.

Alex:
Of all study buddy matches with common availability, if no matches are found taking my same courses, I want to be able to expand my search to find other study buddies in my same program, even if we aren’t currently taking the same courses.

Harris:
Once I have made an account, I want to be able to log into my account and see my potential study buddy matches. I also want to be able to log out.
