package com.example.response;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.R;
import com.example.ui.ErrorDialogFragment;

public class BookDownloadResponse extends AsyncTask<byte[], Void, Boolean> {

    private String book_id;
    File myDir, file;

    private Context mContext;
    
    private FragmentManager fm;
    

    public BookDownloadResponse(Context context, String book_id, FragmentActivity activity) {
        this.book_id = book_id;
        mContext = context;
        this.fm = activity.getSupportFragmentManager();

    }

    @Override
    protected Boolean doInBackground(byte[]... params) {

        myDir = new File(mContext.getExternalFilesDir(null).toString()
                + "/Book_" + book_id);


        Log.i("book download path", "" + mContext.getExternalFilesDir(null).toString()
                + "/Book_" + book_id);
        myDir.mkdirs();

        String fname = "zipped";
        file = new File(myDir, fname);
        boolean isSucess = false;

        if (file.exists() &&  unzipepub()) {
            isSucess = true;
        }

        return isSucess;
    }


    @Override
    protected void onPostExecute(Boolean result) {

        //TODO Show dialog box on download failed!
        super.onPostExecute(result);

        if(result){
            //Add Implicit intent to start your activity
            Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mContext.startActivity(intent);
       
        }else{
        	DialogFragment fragment = ErrorDialogFragment.newInstance(R.string.error, "Error occurred while downloading ");
	        fragment.show(fm, "Download Error!");
        }
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }


    @SuppressLint("NewApi")
    private boolean unzipepub() {
    	
    	boolean isSuccess = true;
    	
        String descpath = myDir.getPath() + "/";
        Log.i("desc path", descpath);

        try {
            ZipFile zipFile = new ZipFile(myDir.getPath() + "/zipped");
            zipFile.extractAll(descpath);

        } catch (Exception e) {
           
            e.printStackTrace();
            
            isSuccess = false;
        }
        
        return isSuccess;

    }



}
