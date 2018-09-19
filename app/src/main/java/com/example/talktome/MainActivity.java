package com.example.talktome;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button btnStartMicRecord;
    private TextView textRecognize;
    private final int REQ_CODE_SPEECH_OUTPUT = 143;
    //SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartMicRecord = (Button) findViewById(R.id.btnStartMicRecord);
        textRecognize = (TextView) findViewById(R.id.recogniseText);

        btnStartMicRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStartMicRecord();
            }
        });
    }

    private void btnStartMicRecord(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT," Приветствую тебя");

        try {
            startActivityForResult(intent,REQ_CODE_SPEECH_OUTPUT);
        } catch (ActivityNotFoundException e) {
            //e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_CODE_SPEECH_OUTPUT:{
                if (resultCode == RESULT_OK && data != null){
                    ArrayList<String> recognizeResult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textRecognize.setText(recognizeResult.get(0));
                }
                break;
            }
        }
    }
}
