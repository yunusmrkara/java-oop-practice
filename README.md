# Java Project Analyzer

## Overview

This project includes a Java program that clones and analyzes a Java project from a GitHub repository link provided by the user. During the analysis, each Java file is scanned to determine specific metrics such as:

- Number of Javadoc comments
- Number of other comment lines
- Number of code lines
- Total Lines of Code (LOC)
- Number of functions
- Comment Deviation Percentage
- Class name

These metrics are used to evaluate the project's health and code quality. The results are then presented to the user, providing insights into the overall performance of the project.

© 2024 Sakarya University.  
This report is my original work. I take full responsibility for any plagiarism.  
**Keywords**: Modularity, Analysis, Evaluation, Reporting

## Features

The project is divided into the following phases:

1. **Getting the Repository Link from the User**:  
   The first step is to obtain a GitHub repository link from the user.

2. **Cloning the Repository**:  
   The provided repository is cloned into the local system using necessary classes and methods that were researched and implemented.

3. **Analyzing the Repository**:  
   This phase is modularized internally. Each Java file in the repository is analyzed to extract features like comment count, LOC, and function count. The `Features` class is used to hold these metrics.

4. **Output Results**:  
   After analyzing, the results for each Java file are displayed on the screen.

### Software Development

Initially, I broke the problem into modular steps:  
- Getting the link from the user  
- Cloning the repository  
- Analyzing the repository  
- Printing the results

For each step, I researched and implemented the necessary methods and classes. For instance, the `Features` class was designed to hold metrics such as comment counts, LOC, and function count for each Java file. Methods inside this class analyze the `File` objects and store the results.

After the analysis, a list of `Features` objects is created, with each object representing the analyzed metrics of a Java file. This list is then passed to the `Print` class, which outputs the results to the console.

### Example Output

I tested the program using repositories shared by my peers. By comparing results, I could detect inconsistencies and resolve issues by collaborating with them.

### Conclusion

This project follows a modular structure, with each class responsible for a specific task. For example:
- The `Analyze` class manages the analysis of the repository.
- The `RepoCloner` class handles cloning the repository.
- The `Features` class calculates and stores metrics for each Java file.
- The `Print` class outputs the analysis results.

This modular approach improves code readability and maintainability. Each class performs a well-defined responsibility, making it easier to update or extend specific parts of the code in the future.

By breaking down problems into smaller components, we can tackle challenges more effectively, both in software development and in real-life situations.
