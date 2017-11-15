package ca.otitismedia.simone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {

    public static ArrayList<Integer> computerCall = new ArrayList<>();
    public static ArrayList<Integer> userResponse = new ArrayList<>();

    private static String[] colourNames = {"","Red","Yellow","Blue","Green"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button)findViewById(R.id.btn1);
        Button btn2 = (Button)findViewById(R.id.btn2);
        Button btn3 = (Button)findViewById(R.id.btn3);
        Button btn4 = (Button)findViewById(R.id.btn4);

        takeTurnComputer();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logUserButtonPress(1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logUserButtonPress(2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logUserButtonPress(3);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logUserButtonPress(4);
            }
        });

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
        Toast.makeText(getApplicationContext(), MainActivity.colourNames[newNumber], Toast.LENGTH_SHORT).show();
        MainActivity.computerCall.add(newNumber);
    }

    protected void logUserButtonPress(Integer buttonNumber){
        userResponse.add(buttonNumber);
        if (MainActivity.computerCall.size() >= MainActivity.userResponse.size()){
            Integer indexToCheck = MainActivity.userResponse.size() - 1;
            if (MainActivity.computerCall.get(indexToCheck) == buttonNumber){
                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                if(MainActivity.computerCall.size() == MainActivity.userResponse.size()){
                    takeTurnComputer();
                    initUserResponse();
                }

            }else{
                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                initComputerCall();
                initUserResponse();
                takeTurnComputer();
            }

        }else{
            Toast.makeText(getApplicationContext(), "Too many inputs", Toast.LENGTH_SHORT).show();
            initComputerCall();
            initUserResponse();
            takeTurnComputer();
        }
    }

}
