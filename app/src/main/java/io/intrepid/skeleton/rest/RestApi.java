package io.intrepid.skeleton.rest;

import io.intrepid.skeleton.models.IpModel;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestApi {
    @GET("/?format=json")
    Observable<IpModel> getMyIp();
}
