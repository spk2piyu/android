package com.example.network.request;

import android.content.Context;

import com.network.request.DownloadRequest;
import com.network.request.NetworkRequest;

public class BookDownloadRequest extends DownloadRequest {

	private String id;
	

	public BookDownloadRequest(Context context, String id) {
        super(context);
        this.id = id;
        setType(NetworkRequest.GET);                                                      
        setUrl(""/*Add your server URL*/);
        setFileName(id);
    }


    public String getBookId() {
        return id;
    }

}
