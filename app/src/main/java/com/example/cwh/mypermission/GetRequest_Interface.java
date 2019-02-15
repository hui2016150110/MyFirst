package com.example.cwh.mypermission;

import retrofit2.http.GET;
import retrofit2.Call;

/**
 * Created by hui on 2019/2/15.
 */

public interface GetRequest_Interface {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getCall();
}
