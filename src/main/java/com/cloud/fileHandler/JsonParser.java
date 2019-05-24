package com.cloud.fileHandler;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonParser {
    private String filePath;

    public JsonParser(String filePath) { this.filePath= filePath; }

    /**
     * This method will read the JSON file and return the JSON object
     * @return
     */
    public JSONObject getObjectfromJSON() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
            String jsonContent = IOUtils.toString(bufferedReader);
            return new JSONObject(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

