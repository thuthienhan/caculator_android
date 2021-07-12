package com.example.caculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public EditText display;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display= findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }

        });
        
    }

    public void btn1(View view){
        updateText("1");
    }

    public void btn2(View view){
        updateText("2");
    }

    public void plus(View view){
        updateText("+");
    }

    public void minus(View view){
        updateText("-");
    }

    public void timefortwonumber(View view){
       updateText("x");
    }

    public void getbacktoempty(View view){
        display.setText("");
    }

    public void divine(View view){
        updateText("/");
    }

    private void updateText(String strtoadd){
        String oldstr= display.getText().toString();
        int curpos=display.getSelectionStart();
        String leftstr = oldstr.substring(0,curpos);
        String rightstr=oldstr.substring(curpos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strtoadd);
            display.setSelection(curpos+1);
        }
        else{
            display.setText(String.format("%s%s%s",leftstr,strtoadd,rightstr));
            display.setSelection(curpos+1);
        }

    }

    public void equalbtn(View view){
        String userexp =display.getText().toString();
        userexp=userexp.replace("/","/");
//        userexp =userexp.replaceAll(regex:"X",replacement:"*")
        userexp=userexp.replace("x","*");
        Expression exp = new Expression(userexp);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }
}