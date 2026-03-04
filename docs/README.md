# Jenie User Guide
Jenie is a powerful desktop task manager optimised for speed and efficiency through a Command Line Interface (CLI).

  [!TIP]
  Jenie is most effective for users who prefer keeping their hands on the keyboard rather than switching to a mouse.

## Adding deadlines
Adds a task to your list that must be completed by a specific date. Jenie understands dates in the YYYY-MM-DD format and will display them in a more readable "MMM dd yyyy" style (e.g., Oct 15 2026).

Format: `deadline <DESCRIPTION> /by YYYY-MM-DD HHmm`

Example: `deadline return library book /by 2026-03-15 1400`

Expected Outcome:
Jenie confirms the addition and displays the total number of tasks in your list.

`Got it. I've added this task:
  [D][ ] return library book (by: Mar 15 2026 02:00 PM)
Now you have 1 tasks in the list.`

## Finding Tasks
Search your entire list for specific keywords.

Format: `find <KEYWORD>`

  [!IMPORTANT]
  The search is currently case-sensitive. Ensure your keyword matches the casing used during task creation.

## Adding todos
Add tasks to be done without specific deadlines.

Format: `todo <DESCRIPTION>`

## Adding events
Add tasks that have a specified duration.

Format: `event <DECRIPTION> /from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm`

## Mark and unmark tasks
Mark or unmark a task as completed/incomplete.

Format: `mark <INDEX> or unmark <INDEX>`

## Deleting tasks
Removes a task from the task list.

Format: `delete <INDEX>`

## Exiting the program
To exit the program

Format: `bye`

  [TIP!] 
  Don't worry! All tasks will be saved when you run Jenie again.
