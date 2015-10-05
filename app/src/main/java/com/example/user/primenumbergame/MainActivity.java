package com.example.user.primenumbergame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int number;
    private boolean PrimeButtonClicked;

    private void RandomNumber(){
        Random RandomGenerator = new Random();
        number = RandomGenerator.nextInt(998)+2;
        String num = number + "";
        TextView random_number = (TextView) findViewById(R.id.RandNumber);
        random_number.setText(num);
    }

    public void onPrimeClicked(View view){
        PrimeButtonClicked=true;
        scoreboard();
        RandomNumber();
    }

    public void onCompositeClicked(View view){
        PrimeButtonClicked=false;
        scoreboard();
        RandomNumber();
    }

    private boolean Prime(int value){
        for(int i=2; i<=value/2; i++){
            if((value%i)==0)
                return false;
        }
        return true;
    }

    private boolean Composite(int value){
        if(Prime(value)== true){
            return false;
        }
        return true;
    }

    private void scoreboard(){
        TextView currentScore = (TextView) findViewById(R.id.currentScoreText);
        int ScoreUpdate = Integer.parseInt(currentScore.getText().toString());

        if((PrimeButtonClicked==true&&Prime(number)==true)||(PrimeButtonClicked==false&&Composite(number)==true)) {
            ScoreUpdate = ScoreUpdate + 1;
            Toast.makeText(this, "Congratulations! You are correct! +1", Toast.LENGTH_SHORT).show();
        }
        else {
            ScoreUpdate = ScoreUpdate - 5;
            Toast.makeText(this, "Sorry, You are incorrect! -5", Toast.LENGTH_SHORT).show();
        }

        String newScore = String.format("%d", ScoreUpdate);
        currentScore.setText(newScore);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RandomNumber();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
