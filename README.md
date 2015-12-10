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
1.  unzip the solution `phonenumber-convertor.zip` 
2.  execute `cd phonenumber-convertor`
3.  build `./gradlew build` (or if you have gradle 2.9 installed, you could also run `gradle build`)

# Usage
*  after build sucessfully
*  `java -jar build/libs/phonenumber-convertor-1.0.jar -p <number> -d <dictionary>`

examples
*  with default dictionary, execute jar `java -jar build/libs/phonenumber-convertor-1.0.jar -p "2255.63"`
*  with specific dictionary, execute jar `java -jar build/libs/phonenumber-convertor-1.0.jar -p "2255.63" -d /Users/twer/yang/some-other-dictionary.txt`

# Design
This solution is based on the basic cryptology knowledge I had, which in case you want
to encode something with a dictionary, first thing should be encode the dictionary then 
walking though the dictionary (as tree).

The solution basically will executing on the following order with associated class:
1. Application 's responsible for read and output result. It will read the input parameters, 
 which is *phone-number* and/or  *dictionary-location*
 
2. Application will init PhoneNumberConvertor, then pass in all the parameters.

3. PhoneNumberConvertor 's responsible for aggregate all the enconder, dictionarySource and provider a query object.

4. DictionaryEncoder will encode any DictionarySource (interface) , in this case we only have one type of source data,
which is TextSourceDictionary (handling text based data source).

5. FullMatchQuery will use the output from DictionaryEncoder, the encoded dictionary.And search all the possible match
for a given number. The result is in a chunk. 

    e.g given "2255" [[["c"],["a","c"],["ll","lk"]] ....] which internally for the program meanning
 the result for this match is a 3 dimensional list, for each item in the list contains a 2 dimensional list (obviously),
 and that 2 dimensional list represent one combination in term of each word length say , first word length is 1, 
 second is 1, third is 2. And the leaf list contains all the possible combination matches both word length and the associated 
 number.
 
6. The output for FullMatchQuery is program level result, which need MatchingResultInterpreter to interpreter it by compose 
all the combination, and filter out unqualified words (e.g two consecutive digits remains unchanged)

