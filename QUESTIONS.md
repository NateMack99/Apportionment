# Reflection Questions

Answer these questions thoroughly, using examples from your code. Good answers will be 1-2 paragraphs that cite specific code examples and show a meaningful reflection on how your development went, and how it could be improved in the future.

## Question 1

In part 2, you now have to support two different inputs, CSV and XLSX. Imagine we asked you to also support reading in another file format, such as JSON. How much code would you need to add/change to enable this? Cite specific existing functions/classes that would need to change.

## Answer

I think I would want to make a more general file reader method instead of rewriting a lot of the code from the other two formats. write now, both the csv reader method and the xlsx reader methods are very similar and could probably be combined into a single method to make the code more concise and readable. To make my program compatible with json files, I would change my csv reader to first put all all the data into a 2D array and then run the parsing logic. From there, all I would need to do is read the json into a 2D array and use my preexisting parser. Luckily I already have a way of detaching the extension and would just need to add another case to my if block (which I would probably turn into a switch statement).

## Question 2

Looking back, which part was more difficult? Part 1, where you had to start from scratch, or Part 2, where you had to change existing code. Explain your answer, citing any specific challenges. In hindsight, would you do anything differently?

## Answer

I'd say part 1 was the most difficult for me because I ran into a lot of bugs in my apportionment algorithm. It helped that I made my code pretty generalizable from the start, as I started out with an Apportionment class that could be extended to fit the Hunter Hill method without having to rewrite my data parsing or toString. I'm also glad I made a custom ErrorHandler class to deal with creating standardized error messages for my code that could be reused and implemented quickly.

In hindsight, as I mentioned it question 1, I wish I had combined my csv and xlsx readers into a single function so that I could reuse the parsing code for both of them. I ran into an bug where I changed the parsing function in one but forgot to do it for the other, which could've been avoided if I only had a single function. Overall, though, I'm pretty happy with how I structured my project to take advantage of object-oriented programming and will likely do similar structure for future projects.
