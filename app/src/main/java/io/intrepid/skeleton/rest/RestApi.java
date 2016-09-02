package io.intrepid.skeleton.rest;

import io.intrepid.skeleton.models.IpModel;
import retrofit2.http.GET;
import rx.Observable;

public interface RestApi {
    @GET("/?format=json")
    Observable<IpModel> getMyIp();
}
