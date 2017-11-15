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
        illuminateSingleButton(newNumber);
        MainActivity.computerCall.add(newNumber);
    }

    protected void logUserButtonPress(Integer buttonNumber){
        userResponse.add(buttonNumber);
        if (MainActivity.computerCall.size() >= MainActivity.userResponse.size()){
            Integer indexToCheck = MainActivity.userResponse.size() - 1;
            if (MainActivity.computerCall.get(indexToCheck) == buttonNumber){
                //Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                if(MainActivity.computerCall.size() == MainActivity.userResponse.size()){
                    takeTurnComputer();
                    initUserResponse();
                }

            }else{
                //Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                initComputerCall();
                initUserResponse();
                takeTurnComputer();
            }

        }else{
            //Toast.makeText(getApplicationContext(), "Too many inputs", Toast.LENGTH_SHORT).show();
            initComputerCall();
            initUserResponse();
            takeTurnComputer();
        }
    }

    protected void waitASecond(){
        try{
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        catch(InterruptedException ex) {
            System.err.println("An InterruptedException was caught: " + ex.getMessage());
        }
    }


    protected void illuminateSingleButton(Integer buttonNumber){
        switch (buttonNumber){
            case 1:
                btn1.setSelected(true);
                //btn1.setBackground(getDrawable(R.drawable.glow));
                //btn1.setBackgroundColor(getResources().getColor(R.color.color1a));
                //btn1.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.color1));
                waitASecond();
                btn1.setSelected(false);
                //btn1.setBackground(null);
                //btn1.setBackgroundColor(getResources().getColor(R.color.color1));
                //btn1.setBackgroundColor(getResources().getColor(R.color.color1));
                //btn1.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorTransparent));

                break;


            case 2:
                btn2.setSelected(true);
                //btn2.setBackground(getDrawable(R.drawable.glow));
                //btn2.setBackgroundColor(getResources().getColor(R.color.color2a));
                //btn2.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorGlow));
                waitASecond();
                btn2.setSelected(false);
                //btn2.setBackground(null);
                //btn2.setBackgroundColor(getResources().getColor(R.color.color2));
                //btn2.setBackgroundColor(getResources().getColor(R.color.color2));
                //btn2.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorTransparent));
                break;


            case 3:
                btn3.setSelected(true);
                //btn3.setBackground(getDrawable(R.drawable.glow));
                //btn3.invalidate();
                //btn3.setBackgroundColor(getResources().getColor(R.color.color3a));
                //btn3.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorGlow));
                waitASecond();
                btn3.setSelected(false);
                //btn3.setBackground(null);
                //btn3.setBackgroundColor(getResources().getColor(R.color.color3));
                //btn3.setBackgroundColor(getResources().getColor(R.color.color3));
                //btn3.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorTransparent));
                break;


            case 4:
                btn4.setSelected(true);
                //btn4.setBackground(getDrawable(R.drawable.glow));
                //btn4.invalidate();
                //btn4.setBackgroundColor(getResources().getColor(R.color.color4a));
                //btn4.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorGlow));
                waitASecond();
                btn4.setSelected(false);
                //btn4.setBackground(null);
                //btn4.setBackgroundColor(getResources().getColor(R.color.color4));
                //btn4.setBackgroundColor(getResources().getColor(R.color.color4));
                //btn4.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorTransparent));
                break;

        }
        return;
    }

}
