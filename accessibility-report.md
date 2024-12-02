# Accessibility Report: Study Buddy Finder

## Achieving the Principles of Universal Design

The *Study Buddy Finder* project strives to incorporate key accessibility principles to ensure inclusivity and usability for all users. This report evaluates how the project meets the following principles, along with recommendations for improvements.

### 1. **Tolerance for Error**

This principle focuses on minimizing the risk and consequences of mistakes and also providing clear options for mistake correction.

- **Error Prevention in Input**:
  - Users are guided through creating profiles with clear labels and checkbox time slots to clearly indicate availability, reducing the risk of invalid data entry.
  - Availability selection uses predefined 1-hour blocks, ensuring users can only input valid time ranges (e.g., 9 AM–5 PM).

- **Feedback on Errors**:
  - If no matches are found based on the selected availability and courses, the system provides a clear notification and offers users the option to expand their search criteria.
  - Error messages are descriptive and guide users on how to correct issues, e.g. "No users found in matching courses" and providing a button to match based on program of study.

- **Undo Options**:
  - Profile (e.g., updating availability, courses, or bio) can be modified at any time, allowing users to correct errors (or simply update outdated informtion) without starting over.

<br/>

### 2. **Size and Space for Approach and Use**

This principle ensures that designs accommodate users with varying physical abilities, devices, or screen sizes.

- **Simple Interface**:
  - The program interface is simple and not not contain any superfluous information, graphics, or areas that may confuse users and detract from achieving the core purpose of the program.
  - All elements are visible on the screen with clearly labelled buttons so any seated or standing user can easily navigate the program.

- **Current Limitations**:
  - The project can only be run as a Java application on a computer, limiting its accessibility to users who rely on other devices, such as smartphones or tablets.
  - The interface operates with a fixed window size, which does not adjust for different screen sizes or user preferences.
  - No touch-friendly controls or support for assistive technologies like screen readers are currently implemented.

- **Future Enhancements**:
  - **Responsive Design**: Modify the interface to dynamically adjust its layout and size based on the user’s device or screen resolution.
  - **Touch-Friendly Interaction**: Increase the size of clickable elements and optimize them for touch input, enabling usability on mobile devices and tablets.
  - **Keyboard and Assistive Technology Support**: Ensure that all functionalities are accessible via keyboard navigation and compatible with screen readers.
  - **Cross-Platform Deployment**: Expand the project to run on multiple platforms, including web and mobile applications, to reach a wider audience.
 
### 3. **Equitable Use**

This principle ensures that the design is useful and marketable to people with diverse abilities.

- **Simple Interface**:
  - As the application can be used by any laptop or tablet device regardless of brand it provides equitable use.
  - The application does not segregate or stigmatize users based on any difference and only provides you with matches based on people with similar courses and time availabilities as you.  

- **Current Limitations**:
  - The project requires the Java application on a computer making it limited in equitable use for all as most people do not have Java installed.
  - The project requires you to download/fork from GitHub which also limits accessibility.
  - The project is limited to UofT students.
  
- **Future Enhancements**:
  - Cross Platform Deployment for greater accessibility, not just limited to app stores and such, but for users that struggle with standard desktop and mobile applications.
  - Universality of the program by making it a general program for university students outside of UofT or even for anyone that isn't a university student, but wants to find someone to study with.

### 6. **Low Physical Effort**

The application Study Buddy Finder has been designed in such a way that it can be used efficiently and comfortably with minimum fatigue.

- As the application can be accessed on a laptop or tablet device, the user is able to use this application while maintaining a neutral spine position.

- To minimize repetitive actions, our application has unique buttons like 'Logout', 'Create Account', 'Back to HomePage'.

- To minimize sustained effort, our application can give you potential matches within a few seconds.

 - The amount of physical effort required to run our program is low, allowing a diversity of people to utilize the application.

## Who is this program for?

If we were to sell or license our program, we would market it to university students. In particular, these would be 
group-oriented students seeking structured study/collaboration with students in the same course or program. An 
alternative option would be, rather than marketing our product to students individually, seeking to license our product 
to universities, who have very large bases of precisely the users we are targeting with this product, to facilitate 
collaboration within their student community.

## Accessibility among varied demographics

From an accessibility standpoint, it is possible that our product will be less-likely used by individuals with certain
disabilities, namely those impairing gross and fine motor skills. Individuals with impairments to their ability to use
their hands and digits may have a limitation with regard to navigating, especially because our program relies upon usage of a mouse
for its functionality (e.g. clicking buttons, accessing text fields). Ultimately, our design is largely accessible as it
satisfies many of the Principles of Universal Design, but its low flexibility for use and low tolerance for error render
it potentially inaccessible to the disabled. Demographically, individuals in lower-income areas are less likely to have 
the technological access or literacy necessary to install and utilize the program. The program is also not intended for 
a wide demographic, as it would not be useful to individuals who are not in school. Within the population of able-bodied university students, we do not expect any demographic limitations.