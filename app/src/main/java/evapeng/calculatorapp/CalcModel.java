package evapeng.calculatorapp;

import android.app.Activity;
import android.databinding.ObservableField;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by evapeng on 2/16/17.
 */

public class CalcModel extends AppCompatActivity implements View.OnClickListener {
    private final Activity activity;
    private Operator add = new Operator("add");
    private Operator sub = new Operator ("sub");
    private Operator div = new Operator("div");
    private Operator mul = new Operator ("mul");
    private Operator equal = new Operator("equal");

    private Operator zero = new Operator(0);
    private Operator one = new Operator(1);
    private Operator two = new Operator(2);
    private Operator three = new Operator(3);
    private Operator four = new Operator(4);
    private Operator five = new Operator(5);
    private Operator six = new Operator(6);
    private Operator seven = new Operator(7);
    private Operator eight = new Operator(8);
    private Operator nine = new Operator(9);

    public ObservableField display = new ObservableField("0");
    Stack<Operator> operators = new Stack<Operator>();

    private static final String TAG = "MyActivity";
    private boolean done = false;
    //set activity
    public CalcModel(Activity activity){
        this.activity = activity;
    }

    //reusable onClick listener
    @Override
    public void onClick(View v){
        Log.e(TAG,"clicked");
        switch (v.getId()){
            case R.id.zero:
                update(zero);
                break;
            case R.id.one:
                update(one);
                break;
            case R.id.two:
                update(two);
                break;
            case R.id.three:
                update(three);
                break;
            case R.id.four:
                update(four);
                break;
            case R.id.five:
                update(five);
                break;
            case R.id.six:
                update(six);
                break;
            case R.id.seven:
                update(seven);
                break;
            case R.id.eight:
                update(eight);
                break;
            case R.id.nine:
                update(nine);
                break;
            case R.id.div:
                update(div);
                break;
            case R.id.equal:
                update(equal);
                break;
            case R.id.mul:
                update(mul);
                break;
            case R.id.sub:
                update(sub);
                break;
            case R.id.add:
                update(add);
                break;
        }
    }

    void update(Operator op){
        if (op.type == "op" && operators.size() == 3 && operators.get(operators.size() - 1).type.equals("num")) {
            int val2 = operators.pop().num;
            String operator = operators.pop().name;
            int val1 = operators.pop().num;
            String newVal;
            switch (operator) {
                case "mul":
                    operators.add(new Operator(val1 * val2));
                    break;
                case "div":
                    if (val2 == 0){
                        Toast.makeText(activity, "Dude, you can't divide by 0", Toast.LENGTH_SHORT).show();
                        done = true;
                        operators.removeAllElements();
                    }
                    else {
                        Log.e(TAG,"SHIZ");
                        operators.add(new Operator(val1 / val2));
                    }
                    break;
                case "sub":
                    operators.add(new Operator(val1 - val2));
                    break;
                case ("add"):
                    operators.add(new Operator(val1 + val2));
                    break;
            }
            if (op.name != "equal" && (!operator.equals("div") && val2 != 0)){
                Log.e(TAG,"SHIZ");
                operators.add(op);
            }
            else {
                done = true;
            }
            displayCalc();
        }

        else if (op.type == "op" && op.name != "equal" && (operators.size() > 0) && (operators.get(operators.size() - 1).type.equals("num"))){
            operators.add(op);
            displayCalc();
        }

        else if (op.type == "num"){
            if (done){
                if (!operators.empty()){
                    operators.pop();
                }

                done = false;
            }

            if (operators.size() == 0){
                operators.add(op);
            }
            else if (operators.peek().type.equals("num")){
                Log.e(TAG,"ADD NUM");
                Operator toAdd = operators.pop();
                String added;
                added = String.valueOf(toAdd.num * 10 + op.num);
                Log.e(TAG,added);
                operators.add(new Operator(Integer.parseInt(added)));
            }
            else if (operators.peek().type.equals("op")){
                Log.e(TAG,"ADD OP");
                operators.add(op);
            }
            displayCalc();
        }
        else {
            Toast.makeText(activity, "Oh no, please click a valid operator.", Toast.LENGTH_SHORT).show();
        }
    }


    void displayCalc(){
        display.set("");
        for (int i = 0; i < operators.size(); i++){
            Log.e(TAG,"SHIZ");
            String type = operators.get(i).type;
            switch (type){
                case("num"):
                    display.set(display.get() + String.valueOf(operators.get(i).num));
                    break;
                case("op"):
                    display.set(display.get() + operators.get(i).sym );
            }
        }
    }
}
