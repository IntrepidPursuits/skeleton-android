package io.intrepid.getaprint.rest;

import io.intrepid.getaprint.models.IpModel;
import retrofit2.http.GET;
import rx.Observable;

public interface RestApi {
    @GET("/?format=json")
    Observable<IpModel> getMyIp();
}
