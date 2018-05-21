package com.sparity.www.csvgenerator;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<VSVModel> vsvModels;
    private TextView generator;
    private File path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        path = new File(Environment.getExternalStorageDirectory() + "/Documents");

    }

    /**
     * This method is used for the initialization
     */
    private void initUI() {
        generator = findViewById(R.id.generator);
        generator.setOnClickListener(this);

        vsvModels = new ArrayList<>();
        VSVModel vsvModel = new VSVModel();
        vsvModel.setId("1");
        vsvModel.setName("Shankar");
        vsvModel.setRollNumber("07W61A0242");
        vsvModels.add(vsvModel);

        VSVModel vsvModel2 = new VSVModel();
        vsvModel2.setId("2");
        vsvModel2.setName("Manoj");
        vsvModel2.setRollNumber("07W61A0243");
        vsvModels.add(vsvModel2);

        VSVModel vsvModel3 = new VSVModel();
        vsvModel3.setId("3");
        vsvModel3.setName("Prasad");
        vsvModel3.setRollNumber("07W61A0244");
        vsvModels.add(vsvModel3);

        VSVModel vsvModel4 = new VSVModel();
        vsvModel4.setId("4");
        vsvModel4.setName("Siddharth");
        vsvModel4.setRollNumber("07W61A0245");
        vsvModels.add(vsvModel4);

        VSVModel vsvModel5 = new VSVModel();
        vsvModel5.setId("5");
        vsvModel5.setName("Satya");
        vsvModel5.setRollNumber("07W61A0246");
        vsvModels.add(vsvModel5);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.generator:
                generateCSV();
                break;
        }
    }

    /**
     * Generate csv file
     */
    private void generateCSV() {
        path.mkdirs();
        File file = new File(path, "stock.csv");
        if (file.exists()) {
            try {
                file.delete();
            } catch (Exception e) {
                throw new RuntimeException("Unable to File " + e);
            }
        }
        File filenew = new File(path, "stock.csv");
        SaveCSV sCSV = new SaveCSV(filenew, this);
        sCSV.save(vsvModels);
    }
}
