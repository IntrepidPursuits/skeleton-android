package io.intrepid.skeleton.rest;

import io.intrepid.skeleton.rules.MockServerRule;

public class TestRestClient {
    public static RestApi getRestService(MockServerRule mockServer) {
        return RetrofitClient.getTestApi(mockServer.getServerUrl());
    }
}
