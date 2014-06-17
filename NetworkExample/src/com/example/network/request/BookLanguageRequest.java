package com.example.network.request;

import com.network.request.NetworkRequest;

public class BookLanguageRequest extends NetworkRequest {

    public BookLanguageRequest(String Bookid) {

        setType(NetworkRequest.GET);
        setCacheEnabled(true);

        setUrl("http://" + Bookid /*Add Your server URL*/);

    }


}
