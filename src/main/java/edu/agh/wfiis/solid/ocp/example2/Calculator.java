package edu.agh.wfiis.solid.ocp.example2;

import java.util.Map;
import java.util.TreeMap;

public class Calculator {
    Map<String, MathematicalOperationStrategy> operations = new TreeMap<>();
    public int calculate(String[] args) {
        int val1 = Integer.parseInt(args[0]);
        int val2 = Integer.parseInt(args[2]);
        String operator = args[1];

        MathematicalOperationStrategy operation = operations.get(operator);
        if (operation != null){
            int result = operation.execute(val1,val2);
            System.out.println(result);
            return result;
        }
        throw new IllegalArgumentException(operator + " is not supported");
    }
    public void addOperation(String symbol, MathematicalOperationStrategy operation){
        operations.put(symbol, operation);
    }
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.addOperation("+",new AdditionStrategy());
        calculator.addOperation("-",new SubtractionStrategy());
        calculator.addOperation("**",new PowerStrategy());
        calculator.calculate(args);
    }


}

interface MathematicalOperationStrategy {
    int execute(int val1, int val2);
}

class AdditionStrategy implements MathematicalOperationStrategy {
    public int execute(int val1, int val2){
        return val1 + val2;
    }
}
class SubtractionStrategy implements MathematicalOperationStrategy {
    public int execute(int val1, int val2){
        return val1 - val2;
    }
}
class PowerStrategy implements MathematicalOperationStrategy {
    public int execute(int val1, int val2){
        return (int) Math.pow(val1,val2);
    }
}