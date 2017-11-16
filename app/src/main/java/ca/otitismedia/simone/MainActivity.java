package ca.otitismedia.simone;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {

    public static ArrayList<Integer> computerCall = new ArrayList<>();
    public static ArrayList<Integer> userResponse = new ArrayList<>();
    public static Boolean callInProgress = false;


    private static String[] colourNames = {"","Green","Red","Yellow","Blue"};
    MediaPlayer beatBox1 = null;
    MediaPlayer beatBox2 = null;
    MediaPlayer beatBox3 = null;
    MediaPlayer beatBox4 = null;
    private ToggleButton btn1;
    private ToggleButton btn2;
    private ToggleButton btn3;
    private ToggleButton btn4;

    private static void initComputerCall() {
        computerCall.clear();
    }

    private static void initUserResponse() {
        userResponse.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        beatBox1 = MediaPlayer.create(getApplicationContext(), R.raw.beatbox_sound1);
        beatBox2 = MediaPlayer.create(getApplicationContext(), R.raw.beatbox_sound2);
        beatBox3 = MediaPlayer.create(getApplicationContext(), R.raw.beatbox_sound3);
        beatBox4 = MediaPlayer.create(getApplicationContext(), R.raw.beatbox_sound4);

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
                if (!MainActivity.callInProgress)
                    logUserButtonPress(1);
                //waitASecond();
                btn1.setSelected(false);
                beatBox1.start();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MainActivity.callInProgress)
                    logUserButtonPress(2);
                //waitASecond();
                btn2.setSelected(false);
                beatBox2.start();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MainActivity.callInProgress)
                    logUserButtonPress(3);
                //waitASecond();
                btn3.setSelected(false);
                beatBox3.start();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MainActivity.callInProgress)
                    logUserButtonPress(4);
                //waitASecond();
                btn4.setSelected(false);
                beatBox4.start();
            }
        });

        beatBox1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btn1.setChecked(false);
            }
        });

        beatBox2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btn2.setChecked(false);
            }
        });

        beatBox3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btn3.setChecked(false);
            }
        });

        beatBox4.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btn4.setChecked(false);
            }
        });


        takeTurnComputer();
        illuminateAllButtonsInSequence();


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
        MainActivity.computerCall.add(newNumber);
    }

    protected void logUserButtonPress(Integer buttonNumber){
        userResponse.add(buttonNumber);
        if (MainActivity.computerCall.size() >= MainActivity.userResponse.size()){
            Integer indexToCheck = MainActivity.userResponse.size() - 1;
            if (MainActivity.computerCall.get(indexToCheck) == buttonNumber){
                //User Pressed Correct Button
                if(MainActivity.computerCall.size() == MainActivity.userResponse.size()){
                    initUserResponse();
                    takeTurnComputer();
                    illuminateAllButtonsInSequence();
                }

            }else{
                //User Pressed Incorrect Button
                initComputerCall();
                initUserResponse();
                takeTurnComputer();
                illuminateAllButtonsInSequence();
            }

        }else{
            //User has pressed too many buttons
            initComputerCall();
            initUserResponse();
            takeTurnComputer();
            illuminateAllButtonsInSequence();
        }
    }

    protected void waitASecond(){
        try{
            Thread.sleep(300);
        }
        catch(InterruptedException ex) {
            System.err.println("An InterruptedException was caught: " + ex.getMessage());
        }
    }

protected void illuminateAllButtonsInSequence(){
    MainActivity.callInProgress = true;
        for (Integer i = 0 ; i < MainActivity.computerCall.size(); i++){
            illuminateSingleButton(MainActivity.computerCall.get(i));
        }
    MainActivity.callInProgress = false;
}


    protected void illuminateSingleButton(final Integer buttonNumber){

        ToggleButton dynamicButton = null;


        // Pick Which Button We'll Be Working With
        switch (buttonNumber) {
            case 1:
                dynamicButton = btn1;
                break;

            case 2:
                dynamicButton = btn2;
                break;

            case 3:
                dynamicButton = btn3;
                break;

            case 4:
                dynamicButton = btn4;
                break;

        }

        //Toggle It
        try {
            dynamicButton.performClick();
        } catch (NullPointerException ex) {
            System.err.println("A NullPointerException was caught: " + ex.getMessage());
        }



    /*
        Handler mHandler = new Handler();



        Runnable codeToRun = new Runnable() {
            @Override
            public void run() {
                //LinearLayout llBackground = (LinearLayout) findViewById(R.id.background);
                //llBackground.setBackgroundColor(0x847839);

                ToggleButton dynamicButton = null;


                // Pick Which Button We'll Be Working With
        switch (buttonNumber){
            case 1:
                dynamicButton = btn1;
                break;

            case 2:
                dynamicButton = btn2;
                break;

            case 3:
                dynamicButton = btn3;
                break;

            case 4:
                dynamicButton = btn4;
                break;

        }

        //Toggle It
                dynamicButton.toggle();
                waitASecond();
                dynamicButton.toggle();

            }
        };
        mHandler.postDelayed(codeToRun, 3000);

*/
    }

}
