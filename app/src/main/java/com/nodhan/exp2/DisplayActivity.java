package com.nodhan.exp2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    TableLayout tableLayout;
    String[][] tokData;
    SharedPreferences sharedPreferences;
    StaticData staticData;
    int NUM_FIELDS;
    TextView textViews[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        NUM_FIELDS = getIntent().getIntExtra("size", 5);//Using Intent extra to get data
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE); //Getting shared pref file
        staticData = (StaticData) getApplicationContext(); //get the Context
        tokData = new String[staticData.getCount()][NUM_FIELDS]; //Init the array to save the data read from file
        //Reading and parsing the data from SharedPref
        for (int i = 0; i < staticData.getCount(); i++) {
            String data[] = sharedPreferences.getString("data" + i, "").split(",");
            for (int j = 0; j < NUM_FIELDS; j++) {
                tokData[i][j] = data[j];
            }
        }

        tableLayout = (TableLayout) findViewById(R.id.scrollTable); //Finding ScrollView
        textViews = new TextView[NUM_FIELDS + 1]; //TextView array

        for (int i = 0; i < staticData.getCount(); i++) {

            TableRow row[] = new TableRow[NUM_FIELDS + 1]; //Row in TableLayout
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);

            String arr[] = {"Registration Number : ", "Name : \t\t\t\t\t\t\t\t\t", "Department : \t\t\t\t\t\t", "Course : \t\t\t\t\t\t\t\t", "Year : \t\t\t\t\t\t\t\t\t\t"};

            //Init row and TextView and setting the data
            for (int j = 0; j <= NUM_FIELDS; j++) {
                row[j] = new TableRow(this);
                row[j].setLayoutParams(layoutParams);
                textViews[j] = new TextView(this);
                if (j != NUM_FIELDS) {
                    textViews[j].setText(arr[j] + tokData[i][j]);
                } else {
                    textViews[j].setText("");
                }
                textViews[j].setTextSize(20);
                row[j].addView(textViews[j]);
                tableLayout.addView(row[j]);
            }
        }
    }
}
