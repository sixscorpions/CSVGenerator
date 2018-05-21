package com.sparity.www.csvgenerator;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveCSV {
    File file;
    MainActivity mainActivity;

    public SaveCSV(File file, MainActivity mainActivity) {
        this.file = file;
        this.mainActivity = mainActivity;
    }

    public void save(List<VSVModel> list) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {

                throw new RuntimeException("Unable to File " + e);
            }
        }
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < list.size(); i++) {
                VSVModel vsvModel = list.get(i);
                writer.write(vsvModel.getId());
                writer.write(',');

                writer.write(vsvModel.getName());
                writer.write(',');


                writer.write(vsvModel.getRollNumber());
                writer.write('\n');
            }
            writer.flush();
            writer.close();


            Uri uri = FileProvider.getUriForFile(mainActivity,
                    mainActivity.getApplicationContext().getPackageName() + ".provider", file);
            Intent viewIntent = new Intent();
            viewIntent.setAction(Intent.ACTION_VIEW);
            viewIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            viewIntent.setDataAndType(uri, "application/vnd.ms-excel");
            mainActivity.startActivity(viewIntent);

        } catch (IOException e) {
            throw new RuntimeException("Unable to write to File " + e);
        }
    }
}
