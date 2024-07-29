Use the Strategy pattern when:

- You Have Multiple Ways to Do Something: You have several algorithms or methods to perform a specific task,
  and you want to switch between them easily.

- You Want to Avoid Conditional Statements: If your code is cluttered with many conditional statements (if, switch) to
  choose which algorithm to use, the Strategy pattern can help clean it up.

- You Want to Change Algorithms at Runtime: You want to be able to change the algorithm that your code uses while it is running.

How It Works

Think of the Strategy pattern as a way to choose between different methods of doing the same thing.
Imagine you have different ways to sort a list of numbers (e.g., Bubble Sort, Quick Sort, Merge Sort).
You can encapsulate each sorting method in its own class, and then your code can choose which sorting method to use at runtime.
