package io.intrepid.getaprint.rest;

import io.intrepid.getaprint.rules.MockServerRule;

public class TestRestClient {
    public static RestApi getRestApi(MockServerRule mockServer) {
        return RetrofitClient.getTestApi(mockServer.getServerUrl());
    }
}
