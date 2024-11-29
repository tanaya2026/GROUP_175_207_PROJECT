# Accessibility Report: Study Buddy Finder

## Achieving the Principles of Universal Design

The *Study Buddy Finder* project strikves to incorporate key accessibility principles to ensure inclusivity and usability for all users. This report evaluates how the project meets the following principles, along with recommendations for improvements.

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

