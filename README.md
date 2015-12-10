# Why I choose this code challenge
I choose *1-800 Code Challenge* among the 2 code-challenges, based on 2 reasons:

*  1-800 Code Challenge have a clear end point of defination of done, which is given "2255.63" the solution should provide a list contains 
"CALL-ME" in return. 
while comparing to the *Vehicle Survey Code Challenge* have no clear point that I can confidently say the program works. 
*  the initial solution is formed when I looking at *1-800-code-challenge*, while for *Vehicle Survey Code Challenge* I was a little bit unclear.
I do not want to take unnecessary risk especially when this solution is important.

# System requirement
This solution is built on 
* java version "1.8.0_05"
* gradle 2.9 (optional, since the gradle wrapper is also part of the solution)

# How to build
1.  unzip the solution `tar -xvf phonenumber-convertor.tar.gz` 
2.  execute `cd phonenumber-convertor`
3.  build `./gradlew build` (or if you have gradle 2.9 installed, you could also run `gradle build`)

# Usage
*  after build sucessfully
*  `java -jar build/libs/phonenumber-convertor-1.0.jar -p <number> -d <dictionary>`

examples
*  with default dictionary, execute jar `java -jar build/libs/phonenumber-convertor-1.0.jar -p "2255.63"`
*  with specific dictionary, execute jar `java -jar build/libs/phonenumber-convertor-1.0.jar -p "2255.63" -d /Users/twer/yang/some-other-dictionary.txt`

# Design
