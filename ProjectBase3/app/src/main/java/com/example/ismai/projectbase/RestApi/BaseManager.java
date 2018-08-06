package com.example.ismai.projectbase.RestApi;

public class BaseManager {
    protected RestApi getRestApiClient() {
        RestApiClient restApiClient = new RestApiClient(BaseUrl.bilgi_URL);
        return restApiClient.getRestApi();
    }
}
