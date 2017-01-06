# pi
Approximating π with Java

## method
 1. Define the function for a positive half-circle of constant radius `r`
    - `f(x) = √(r^2 - x^2)`
 2. Split the horizontal radius `r` into `n` number of x-coordinates
    - `x(i) = (2 * r * i) / n - r`
 3. Use `f(x)` to find the corresponding y-coordinates
    - `f(x(i)) = √(r^2 - ((2 * r * i) / n - r)^2)`
 4. Segments joining the points defined by these x and y coordinates form an approximation of the circumference of the half-circle
    - As `n` increases, the number of points increases; the number of segments thus increases and more accurately represents the curve of a circle
    - As `n` approaches infinity, the number of points approaches infinity; the number of segments thus approaches infinity and the length of each segment becomes infinitely small, approaching 0, approaching the definition of a point
    - If `n` truly equaled infinity, the segments would in reality be an infinite number of points on the circumference of the half-circle. However, this infinite quantity is impossible to measure with a method/program such as this, so we allow `n` to equal a very large number
 5. Use the distance formula/Pythagorean theorem to find the distance between one point on the circle and the next (length of the i'th segment)
    - In terms of f, x, and i: `d(i) = √((x(i) - x(i + 1))^2 + (f(x(i)) - f(x(i + 1)))^2)`
    - Partially substituted (in terms of f and i): `d(i) = √((((2 * r * i) / n - r) - ((2 * r * (i + 1)) / n - r))^2 + (f((2 * r * i) / n - r) - f((2 * r * (i + 1)) / n - r))^2)`
    - Fully substituted (in terms of i): `d(i) = √((((2 * r * i) / n - r) - ((2 * r * (i + 1)) / n - r))^2 + (√(r^2 - ((2 * r * i) / n - r)^2) - √(r^2 - ((2 * r * (i + 1)) / n - r)^2))^2)`
 6. Sum the formula defined in step 5, beginning with `i = 0` and incrementing `i` to `n`
    - The resulting value is the circumference of the half-circle
    - Multiply by `2` to get the circumference `c` of a full circle of the same radius
 7. Divide `c` by the original diameter to get the final approximation for pi
    - `π ~= c / (2 * r)`

## precision
 - In the GUI, the radius textfield is irrelevant, as pi is a constant ratio for all circles of all sizes
    - The program defaults to a circle of radius 10
 - However, the precision textfield is very important. The integer `p` provided in that textfield determines the accuracy of the approximation to the known pi
    - `n` in the above calculations is `10^p`, and is also represented as the total number of "iterations" in the GUI
 - Within `n: (1, 10)`, the approximation of pi is generally correct to `n + (1 or 3)` decimal places

## samples
All of the below calculations use a circle of radius 10. `t` is the time in seconds of the calculation (time recorded on MacBook Pro 15-inch, 2016, 2.6 GHz Intel Core i7 processor, and 16 GB 2133 MHz LPDDR3 memory).  
 1. Precision `1`
    - `π ~= 3.1151059505583474`
    - `t = 0.001 sec`
 2. Precision `2`
    - `π ~= 3.1407605898424658`
    - `t = 0.001 sec`
 3. Precision `3`
    - `π ~= 3.1415663562164804`
    - `t = 0.004 sec`
 4. Precision `4`
    - `π ~= 3.141591822039799`
    - `t = 0.043 sec`
 5. Precision `5`
    - `π ~= 3.1415926272940218`
    - `t = 0.464 sec`
 6. Precision `6`
    - `π ~= 3.1415926527582783`
    - `t = 4.406 sec`
 7. Precision `7`
    - `π ~= 3.141592653563495`
    - `t = 45.009 sec`
 8. Precision `8`
    - `π ~= 3.141592653588962`
    - `t = 457.745 sec`
 9. Precision `9`
    - `π ~= `
    - `t = 0 sec`
 10. Precision `10`

## GUI screenshots
![screenshotA](https://raw.githubusercontent.com/anuvgupta/pi/master/screenshotA.png)  
![screenshotB](https://raw.githubusercontent.com/anuvgupta/pi/master/screenshotB.png)  
![screenshotC](https://raw.githubusercontent.com/anuvgupta/pi/master/screenshotC.png)  
