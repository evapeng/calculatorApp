package evapeng.calculatorapp;

/**
 * Created by evapeng on 2/16/17.
 */

public class Operator {
    String name;
    String type;
    String sym;
    int num;

    Operator(int num){
        this.type = "num";
        this.num = num;
    }
    Operator(String name){
        this.name = name;
        this.type = "op";
        switch (name){
            case "mul":
                sym = "x";
                break;
            case "div":
                sym = "/";
                break;
            case "add":
                sym = "+";
                break;
            case "sub":
                sym = "-";
        }
    }


}
