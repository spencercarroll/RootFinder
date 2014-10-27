RootFinder
==========
### About ###
RootFinder is a command line program that finds roots of polynomials. It finds roots using the [Newton–Raphson method](http://en.wikipedia.org/wiki/Newton's_method) however the most interesting part of the program is how it finds a good starting point for the search. There are many [pitfalls](http://www.cas.mcmaster.ca/~cs4te3/notes/newtons_method.pdf) to Newton's method that may cause the algorithm to miss a root. RootFinder avoids many of these problems by starting Newton's method on a polynomial, p, with the following x values:

* Slightly negative of the left-most vertex
* Slightly positive of the right-most vertex
* Just left or right of an inflection point

RootFinder finds the vertices of p by calling on itself to find the roots of the derivative of p. To find inflection points RootFinder calls itself again to find the roots of derivative of the derivative of p. It's a fantastic example of recursion! 

RootFinder also has a fantastic Polynomial class which can create derivatives and convert strings into polynomials. To simplify the code, RootFinder only allows fully expanded polynomials. RootFinder is untested on fractional/negative exponents but could be extended to allow this functionality.


### Usage ###
Run CreateJar.sh

    ./CreateJar.sh

Run the jar with 

    java -jar rootFinder.jar
and type, paste or pipe in your equation.
