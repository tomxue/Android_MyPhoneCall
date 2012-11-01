package com.example.myactioncall;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;    
import android.content.Intent;  
import android.net.Uri;  
import android.view.View;  
import android.widget.Button;  
import android.widget.EditText;  
import android.widget.Toast;  

public class MainActivity extends Activity {
	
	private Button button;  
    private EditText text;  

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        text = (EditText)findViewById(R.id.editText1);
        button = (Button)findViewById(R.id.button1);  
          
        button.setOnClickListener(new Button.OnClickListener(){
  
            @Override  
            public void onClick(View v) {  
                try {
                    String inputStr = text.getText().toString();  
                    if(isPhoneNumberValid(inputStr) == true){
                        Intent myIntentDial = new Intent(  
                                Intent.ACTION_CALL,Uri.parse("tel:"+inputStr)  
                        );  
                          
                        startActivity(myIntentDial);  
                          
                        text.setText("calling...");  
                    }else{  
                        text.setText("wrong number");  
                        Toast.makeText(MainActivity.this, "电话格式不对", Toast.LENGTH_LONG).show();  
                    }  
                } catch (Exception e) {  
                	Toast.makeText(MainActivity.this, "exception!", Toast.LENGTH_LONG).show();  
                    System.out.println(e.getMessage());  
                }  
            }  
              
        });  
    }  
      
    public static boolean isPhoneNumberValid(String phoneNumber){
          
        boolean isValid = false;  
//        String expression = "^//(?(//d{3})//)?[- ]?(//d{3})[- ]?(//d{5})$";
//        String expression2 = "^//(?(//d{3})//)?[- ]?(//d{4})[- ]?(//d{4})$";
        String expression = "13*";
        String expression2 = "10086*";
        CharSequence inputStr = phoneNumber;  
          
        Pattern pattern = Pattern.compile(expression);            
        Matcher matcher = pattern.matcher(inputStr);  
          
        Pattern pattern2 = Pattern.compile(expression2);            
        Matcher matcher2 = pattern2.matcher(inputStr);  
          
        if(matcher.matches()||matcher2.matches()){
            isValid = true;
        }
          
        return isValid;
    }
      
      
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
