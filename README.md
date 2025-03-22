1.Solar System Model – Spec 

Level 1 - Basic Solar System
A class (or classes!) to define a solar system. You must stick to the names (including exact case) and methods below or your code will not work
•	Name of Class - the primary class must be called SolarSystem: you may write sub classes to help you implement it in a better way if you wish (and there can be as many as you like - don’t over do it - and they can be called what you like). This is a hint! There is at least one ‘sensible’ additional class here (but don’t overdo it).
•	Constructor - there should be one constructor SolarSystem(String name) where name is the name you wish to give a solar system - level 1 stars do not have a luminosity so you don't need to include that.
•	addPlanet - there should be a method void addPlanet(String name, double distance) that creates a new planet with the specified name and distance from its star - level 1 planets do not have a a radius or mass so you don't need to include them. 
•	toString - there should be a method String toString() that returns a string containing:
o	The name of the solar system in the format
Star A has planets:
•	
o	where A is the name of the solar system ;
o	data for each planet in the following exact format with each line ending in a newline.
     X  is BAU from its star, and orbits in C years
where X is the name,  B is the distance from the star rounded to three decimal places, and C is the orbital period rounded to three decimal places. In all cases, B and C need to be returned rounded to three decimal places. It is up to you if you store them this way, or round them when they are output. Note that X and B are entered as data when the planet is created; C is calculated from B (see (formulae below).
•	Note that as before the exact format of the text string is critical to the correct operation of the program - you can find examples of the format in the test file (included with the coursework) Download included with the coursework)called AutoTestLevel1.java.
•	Other methods - you will need to provide other methods (and maybe constructors and other classes) too: these will be up to you and depend on how you approach the problem.
•	You should not write a main method (except perhaps as part of your own testing, which would be a good idea). The main method for this program is in the AutoTestLevel1.java class which I have given you. (I know I have said this several times, but people often still try to submit one.)
Level 2 - Extra Methods
A Level 2 solution must pass the tests for Level 1.
Level 2 is the same as Level 1 except that the following methods are added. I have been deliberately a bit vague (and have carefully written the test code) to avoid giving you too many hints - but also to avoid forcing you to do it in a particular way (there is more than one "good" way to do this). However, if you solution is technically incorrect, the automatic testing will not work (it will probably not even compile) which should help you. As with Level 1, there is a provided test file - AutoTestLevel2.java
•	equals - a method that checks if two planets are equal - that is, they have the same name and distance from the star. You use it like the equals method that checks if two strings are equal. Just checking that the two planets are equal using == will not work - the test file is written to ensure it doesn't.
•	getPlanetByName - a method that returns a planet given just it's name (or null there isn't one).
•	furthest - returns the planet furthest from the star (you cannot just assume it was the last one added).
•	closest - returns the planet closest to the star (you cannot assume it was the first one added).
Not all these method should go in the same class - you need to think about that.
Level 3 - Add in Luminosity, Mass, Radius and Habitability...
...without breaking your solution to Level 1 and 2. The 'tricky' part of this is that all the code for Level 1 and 2 must continue to work, including the methods, constructor and toString method you wrote for Level 1. Again there is a test file - AutoTestLevel3.java
A Level 3 solution must pass all the tests for Level 1 and Level 2. 
•	Constructor - there should be a constructor SolarSystem(String name, double luminosity) where name is the name you wish to give a solar system and luminosity is the star’s luminosity.
•	addPlanet - there should be a method void addPlanet(String name, double mass, double radius, double distance) that creates a new planet with the specified name, mass, radius and distance from its star.
•	There is no need to change the equals method you wrote for Level 2.
•	toString - there should be a method String toString() that returns a string containing:
o	The name of the solar system followed by its luminosity in the format
Star A has planets:
•	
o	(i.e the same as level 1);
o	data for each planet in the following exact format with each line ending in a newline.
     X has a mass of A Earths with a surface gravity of Gg, is BAU from its star, and orbits in C years: could be habitable? Z
where X is the name, A is the mass rounded to three decimal places, B is the distance from the star rounded to three decimal places, C is the orbital period rounded to three decimal places (as before). Additionally for Level 3,  G is the surface gravity also rounded to three decimal places, and the value of z is either yes or no - based on the habitability calculation. In all cases, A, B, G and C need to be returned rounded to three decimal places. It is up to you if you store them this way, or round them when they are output. Note that X, A and B are entered as data when the planet is created; C, G and Z are calculated from that data (and in the case of Z, from the luminosity as well).











Project Achievements and Key Features

Level 1 - Basic Solar System
•	Implemented a SolarSystem class that:
•	Stores the name of the solar system.
•	Allows adding planets with a specified name and distance from the star.
•	Provides a toString() method to return a formatted string representation of the system.
•	Implemented a Planet class that:
•	Stores a planet’s name and distance from its star.
•	Computes the orbital period using Kepler’s Third Law distance3\sqrt{\text{distance}^3}distance3.

Level 2 - Additional Functionalities
•	Added helper methods to the SolarSystem class: 
•	equals(): Compares two planets based on name and distance.
•	getPlanetByName(): Returns a planet given its name.
•	furthest(): Identifies the planet furthest from the star.
•	closest(): Identifies the planet closest to the star.

Level 3 - Advanced Features
•	Extended the SolarSystem class:
•	Added a luminosity attribute for stars.
•	Modified the constructor to support luminosity.
•	Enhanced the Planet class:
•	Introduced mass and radius properties.
•	Calculated surface gravity using the formula massradius2\frac{\text{mass}}{\text{radius}^2}radius2mass.
•	Determined habitability based on luminosity and distance constraints.
•	Updated toString() to include the new parameters.







Lessons Learned

•	Object-Oriented Programming (OOP) Best Practices
o	Applied encapsulation by keeping fields private and using constructors and getters.
o	Ensured abstraction by separating solar system and planet logic.
o	Designed modular code to enable scalability and ease of modification.
•	Mathematical Calculations in Programming
o	Used Kepler’s Third Law to compute planetary orbital periods.
o	Applied gravitational equations for surface gravity calculations.
o	Integrated distance and luminosity constraints to determine habitability.
•	Testing and Debugging
o	Implemented unit testing using AutoTestLevel3.java to validate functionality.
o	Ensured precise string formatting to meet test requirements.
o	Debugged and verified floating-point rounding for consistency in calculations.
o	Working with Java Collections
o	Used ArrayList to manage planetary objects efficiently.
o	Maintained dynamic references to furthest and closest planets to optimize retrieval.
o	Adhering to a Given Specification
o	Followed strict method signatures and output formats to comply with auto-test requirements.
o	Managed floating-point precision and rounding to meet specification constraints.


![image](https://github.com/user-attachments/assets/425a369c-314f-4d5b-9741-0d75ca0b4971)
