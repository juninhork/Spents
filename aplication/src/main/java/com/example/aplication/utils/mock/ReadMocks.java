package com.example.aplication.utils.mock;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by juniorbraga on 20/03/17.
 */

public class ReadMocks {
    private Context mContext;

    public ReadMocks (Context context){
        this.mContext = context;
    }

    public String readJsonMock(int raw) {

        String result = "";

        InputStream inputStream = this.mContext.getResources().openRawResource(raw);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        result = byteArrayOutputStream.toString();
        return result;
    }
}
