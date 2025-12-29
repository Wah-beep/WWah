# Test Plan for `angleBetween()` in `Circle.java`

## Test Cases

Test Case 1
Description: Tests when the second circle is directly to the right of the base circle.  
Test Input: Circle(0, 0, 5), Circle(5, 0, 5)  
Expected Output: 0°  

Test Case 2 
Description: Tests when the second circle is directly above the base circle.  
Test Input: Circle(0, 0, 5), Circle(0, 5, 5)  
Expected Output: 90°  

Test Case 3 
Description: Tests when the second circle is directly to the left of the base circle.  
Test Input: Circle(0, 0, 5), Circle(-5, 0, 5)  
Expected Output: 180°  

Test Case 4
Description: Tests when the second circle is directly below the base circle.  
Test Input: Circle(0, 0, 5), Circle(0, -5, 5)  
Expected Output: 270°  

Test Case 5  
Description: Tests when the second circle is at a 45-degree angle from the base circle.  
Test Input: Circle(0, 0, 5), Circle(3, 3, 5)  
Expected Output: 45°  

Test Case 6
Description: Tests when the second circle is at a 225-degree angle from the base circle.  
Test Input: Circle(0, 0, 5), Circle(-3, -3, 5)  
Expected Output: 225°  


#Test Cases for Overlapping

Test Case 1
Description: Testing normal overlapping in each quadrants.
Test Input: Some true statements, and some false
Expected Output: Some true statements, and some false

Test Case 2
Description: Testing Stacked Overlapping. Circles on top of each others, and a circle within a circle.
Test Input: Some true statements, and some false
Expected Output: Some true statements, and some false

Test Case 3
Description: Testing Touching overlapping on x and y axis. Circles will touch on x and y axis. 
Test Input: True Statements
Expected Output: True Statements

Test Case 4
Description: Testing normal overlapping but with csv source. 
Test Input: Some true statements, and false statements
Expected Output: True Statements and false statements. 



