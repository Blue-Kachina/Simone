package ca.otitismedia.simone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    public static ArrayList<Integer> computerCall = new ArrayList<>();
    public static ArrayList<Integer> userResponse = new ArrayList<>();

    private static String[] colourNames = {"","Green","Red","Yellow","Blue"};

    private ToggleButton btn1;
    private ToggleButton btn2;
    private ToggleButton btn3;
    private ToggleButton btn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (ToggleButton)findViewById(R.id.btn1);
        btn2 = (ToggleButton)findViewById(R.id.btn2);
        btn3 = (ToggleButton)findViewById(R.id.btn3);
        btn4 = (ToggleButton)findViewById(R.id.btn4);

        btn1.setText("");
        btn1.setTextOn("");
        btn1.setTextOff("");
        btn2.setText("");
        btn2.setTextOn("");
        btn2.setTextOff("");
        btn3.setText("");
        btn3.setTextOn("");
        btn3.setTextOff("");
        btn4.setText("");
        btn4.setTextOn("");
        btn4.setTextOff("");


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logUserButtonPress(1);
                waitASecond();
                btn1.setSelected(false);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logUserButtonPress(2);
                waitASecond();
                btn2.setSelected(false);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logUserButtonPress(3);
                waitASecond();
                btn3.setSelected(false);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logUserButtonPress(4);
                waitASecond();
                btn4.setSelected(false);
            }
        });

        //takeTurnComputer();
        //illuminateAllButtonsInSequence();


    }

    private static void initComputerCall(){
        computerCall.clear();
    }
    private static void initUserResponse(){
        userResponse.clear();
    }

    protected int getRandomNumber(){
        Random random = new Random();
        return random.nextInt(4 - 1 + 1) + 1; //Should return a number between 1 and 4 (inclusive)
    }

    protected void takeTurnComputer(){
        if (MainActivity.computerCall != null)        {
            Integer turnNumber = MainActivity.computerCall.size() + 1;
        }
        Integer newNumber = getRandomNumber();
        //Toast.makeText(getApplicationContext(), MainActivity.colourNames[newNumber], Toast.LENGTH_SHORT).show();
        //illuminateSingleButton(newNumber);
        MainActivity.computerCall.add(newNumber);
    }

    protected void logUserButtonPress(Integer buttonNumber){
        userResponse.add(buttonNumber);
        if (MainActivity.computerCall.size() >= MainActivity.userResponse.size()){
            Integer indexToCheck = MainActivity.userResponse.size() - 1;
            if (MainActivity.computerCall.get(indexToCheck) == buttonNumber){
                //Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                if(MainActivity.computerCall.size() == MainActivity.userResponse.size()){
                    initUserResponse();
                    takeTurnComputer();
                    illuminateAllButtonsInSequence();
                }

            }else{
                //Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                initComputerCall();
                initUserResponse();
                takeTurnComputer();
                illuminateAllButtonsInSequence();
            }

        }else{
            //Toast.makeText(getApplicationContext(), "Too many inputs", Toast.LENGTH_SHORT).show();
            initComputerCall();
            initUserResponse();
            takeTurnComputer();
            illuminateAllButtonsInSequence();
        }
    }

    protected void waitASecond(){
        try{
            TimeUnit.MILLISECONDS.sleep(300);
        }
        catch(InterruptedException ex) {
            System.err.println("An InterruptedException was caught: " + ex.getMessage());
        }
    }

protected void illuminateAllButtonsInSequence(){
        for (Integer i = 0 ; i < MainActivity.computerCall.size(); i++){
            illuminateSingleButton(MainActivity.computerCall.get(i));
        }
}


    protected void illuminateSingleButton(Integer buttonNumber){
        switch (buttonNumber){
            case 1:
                btn1.toggle();
                waitASecond();
                btn1.toggle();
                break;


            case 2:
                btn2.toggle();
                waitASecond();
                btn2.toggle();
                break;


            case 3:
                btn3.toggle();
                waitASecond();
                btn3.toggle();
                break;


            case 4:
                btn4.toggle();
                waitASecond();
                btn4.toggle();
                break;

        }
    }

}
