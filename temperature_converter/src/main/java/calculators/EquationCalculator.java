package calculators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EquationCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("""
                Enter an equation along the following examples. Separate the left and right side by an '='
                The equation can contain a variable, for example (5x = 3)
                If one side has multiple terms, separate them by spaces and have their signs next to them
                Example: (5x -2 = 3) or (3 = 5x -2)""");
        String equation = input.nextLine();
        System.out.print("Enter the variable as you entered it in the equation (i.e 'x'): ");
        char variable = input.nextLine().charAt(0);
        determineSolvingStrategy(equation, variable);
    }

    static String[] parseToEquation(String equationString) {
        String[] halves = equationString.split("=");
        for (int i = 0; i < 2; i++) {
            halves[i] = halves[i].trim();
        }
        return halves;
    }

    static void determineSolvingStrategy(String equation, char variable) {
        if(equation.contains(String.valueOf(variable))) {
            if(equation.contains("^")) {
                int varCount = 0, powCount = 0;
                for (int i = 0; i < equation.length(); i++) {
                    if(equation.charAt(i) == variable) {
                        varCount++;
                    }
                    if(equation.charAt(i) == '^') {
                        powCount++;
                    }
                }
                //System.out.printf("variables %d, powers %d\n", varCount, powCount);
                if(powCount > 0 && varCount > 1 && powCount != varCount) {
                    solveQuadratic(equation, variable);
                }
            }
        } else {
            solveNormal(equation, variable);
        }
    }

    static void solveQuadratic(String equation, char variable) {
        String[] sides = equation.split("=");
        List<String> aSquare = new ArrayList<>(), bVar = new ArrayList<>(), constant = new ArrayList<>();
        System.out.println("Equation sides: " + Arrays.toString(sides));
        for (int i = 0; i < sides.length; i++) {
            sides[i] = sides[i].trim();
        }
        String[] leftSide = sides[0].split(" "),
                rightSide = sides[1].split(" ");
        for (int i = 0; i < leftSide.length; i++) {
            leftSide[i] = leftSide[i].trim();
        }
        for (String s : leftSide) {
            if (s.contains(String.valueOf(variable))) {
                if(s.contains("^")) {
                    aSquare.add(s);
                } else {
                    bVar.add(s);
                }
            } else {
                constant.add(s);
            }
        }
        for (String s : rightSide) {
            if(s.charAt(0) == '-') s = s.replace('-', '+');
            else if (s.charAt(0) == '+') {
                s = s.replace('+', '-');
            } else s = "-" + s;
            if (s.contains(String.valueOf(variable))) {
                if(s.contains("^")) {
                    aSquare.add(s);
                } else {
                    bVar.add(s);
                }
            } else {
                constant.add(s);
            }
        }
        System.out.println("Separate sides");
        System.out.println("left: " + Arrays.toString(leftSide));
        System.out.println("right: " + Arrays.toString(rightSide));
        System.out.printf("Power: %s, variable: %s, constant: %s\n", aSquare, bVar, constant);

        String aString = aSquare.get(0).split("x")[0],
                bString = bVar.get(0).split("x")[0],
                cString = constant.get(0);
        double a, b, c;
        if(aString.isEmpty() || aString.isBlank()) a = 1;
        else a = Double.parseDouble(aString);
        if(bString.isEmpty() || bString.isBlank()) b = 1;
        else b = Double.parseDouble(bString);
        if(cString.isEmpty() || cString.isBlank()) c = 0;
        else c = Double.parseDouble(cString);

        System.out.printf("a: %f, b: %f, c: %f\n", a, b, c);
        double x1, x2, discriminant = Math.pow(b, 2) - (4*a*c);
        x1 = (-b + Math.sqrt(discriminant))/2;
        x2 = (-b - Math.sqrt(discriminant))/2;
        System.out.printf("%s is either %.3f of %.3f", variable, x1, x2);
    }

    static void solveNormal(String equationString, char variable) {
        if(!equationString.contains("=")) {
            System.err.println("No equation found. The input does not contain a '=' symbol!");
            System.exit(1);
        }
        System.out.println("Solving: " + equationString);
        String[] halves = parseToEquation(equationString);
        List<String> leftHalf = new ArrayList<>(Arrays.asList(halves[0].split(" "))),
                rightHalf = new ArrayList<>(Arrays.asList(halves[1].split(" ")));

        double result = 0;
        if(equationString.contains(String.valueOf(variable))) {
            List<String> carryItems = new ArrayList<>();
            for (String term : leftHalf) {
                if(!term.contains(String.valueOf(variable))) {
                    rightHalf.add(String.valueOf(-Double.parseDouble(term)));
                    carryItems.add(term);
                }
            }
            leftHalf.removeAll(carryItems);
            carryItems = new ArrayList<>();
            for (String term : rightHalf) {
                if(term.contains(String.valueOf(variable))) {
                    if(!term.contains("-")) {
                        leftHalf.add("-"+term.replace("\\+", ""));
                    } else {
                        leftHalf.add("+"+term.replace("-", ""));
                    }
                    carryItems.add(term);
                }
            }
            rightHalf.removeAll(carryItems);
            if (rightHalf.size() == 1) {
                result = Double.parseDouble(rightHalf.get(0));
            } else {
                for(String term : rightHalf) {
                    result += Double.parseDouble(term);
                }
            }
            if (leftHalf.size() == 1) {
                leftHalf.set(0, leftHalf.get(0).replace(String.valueOf(variable), ""));
                double factor = Double.parseDouble(leftHalf.get(0));
                result /= factor;
            }
        }
        System.out.printf("The result is: %s = %.3f\n", variable, result);
    }
}
