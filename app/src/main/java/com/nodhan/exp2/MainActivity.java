package com.nodhan.exp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    ArrayList<String> editTextsData;
    SharedPreferences sharedPreferences;
    StaticData staticData;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE); //Getting shared pref file
        staticData = (StaticData) getApplicationContext(); //get the Context
        staticData.setCount(sharedPreferences.getInt("count", 0)); //Init the count of StaticData class from SharedPreferences

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout = (RelativeLayout) findViewById(R.id.details);
                editTextsData = new ArrayList<>();

                //getting data from EditText's
                for (int i = 0; i < relativeLayout.getChildCount(); i++)
                    if (relativeLayout.getChildAt(i) instanceof EditText)
                        if (checkForNull((EditText) relativeLayout.getChildAt(i))) {
                            editTextsData.add(((EditText) relativeLayout.getChildAt(i)).getText().toString());
                        }

                if (flag) {
                    String dataToBeAdded = "";

                    for (int i = 0; i < editTextsData.size(); i++) {
                        if (i == editTextsData.size() - 1) {
                            dataToBeAdded += editTextsData.get(i);
                        } else {
                            dataToBeAdded += editTextsData.get(i) + ",";
                        }
                    }

                    SharedPreferences.Editor edit = sharedPreferences.edit(); //editor obj to edit the data.xml file
                    Log.d("EXP2TEST", staticData.getCount() + ":Before");
                    edit.putString("data" + staticData.getCount(), dataToBeAdded);
                    staticData.incCount();//increment the count(static)
                    edit.putInt("count", staticData.getCount());//Saving the count into SharedPreferences file
                    edit.commit();
                    Log.d("EXP2TEST", staticData.getCount() + ":After");
                    startActivity(new Intent(getApplicationContext(), DisplayActivity.class).putExtra("size", editTextsData.size()));//Moving to next activity
                }
            }
        });

        findViewById(R.id.view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (staticData.getCount() > 0) {
                    startActivity(new Intent(getApplicationContext(), DisplayActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Add details to view", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Checks if EditText is filled or not
     *
     * @param editText
     * @return true if not null, false if null
     */
    public boolean checkForNull(EditText editText) {
        String text = editText.getText().toString();
        if (text.equals("") || text.equals(null)) {
            editText.setError("Cannot be empty!");
            flag = false;
            return false;
        } else {
            return true;
        }
    }
}
