package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userInput;
    private TextView textView;
    private Button button;
    private int numTimesClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInput = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        textView.setText("");
        textView.setMovementMethod(new ScrollingMovementMethod());

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numTimesClicked += 1;
                String result = "The button got tapped " + numTimesClicked
                        + "time";
                if (numTimesClicked != 1){
                    result += "s";
                }
                result += "/n";
                textView.append(result);

            }
        };

        button.setOnClickListener(onClickListener);

    }
}
