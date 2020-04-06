package com.skupina1.urnik;

import android.app.Application;
import org.apache.commons.io.FileUtils;
import android.util.Log;
import com.example.libdata.MyData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;

public class ApplicationMy extends Application {

    private String idApp;
    private MyData data; //All data
    public static final String TAG = ApplicationMy.class.getSimpleName();
    private static final String MY_FILE_NAME = "offlineTasks.txt";
    private Gson gson;
    private File file;

    private Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }
        return gson;
    }

    private File getFile() {
        if (file == null) {
            File filesDir = getFilesDir();
            file = new File(filesDir, MY_FILE_NAME);
        }
        Log.i(TAG, file.getPath());
        return file;
    }

    private void saveToFile() {
        try {
            FileUtils.writeStringToFile(getFile(), getGson().toJson(data));
        } catch (IOException e) {
            Log.d(TAG, "Can't save " + file.getPath());
        }
    }

    private boolean readFromFile() {
        if (!getFile().exists()) return false;
        try {
            data = getGson().fromJson(FileUtils.readFileToString(getFile()), MyData.class);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public String getIdApp() {
        if (idApp == null) {
            //Try to read from s.p. or generate new id set idApp
            idApp = MyData.GetID();
        }
        return idApp;
    }

    public MyData getData() {
        if (data == null) {
            if (!readFromFile())
                data = MyData.GenerateTestTasks();
        }
        return data;
    }

    public void save() {
        saveToFile();
    }

}
