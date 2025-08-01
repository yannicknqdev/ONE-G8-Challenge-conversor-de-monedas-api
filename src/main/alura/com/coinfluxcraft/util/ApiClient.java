package main.alura.com.coinfluxcraft.util;

import java.net.http.HttpClient;
import java.time.Duration;

public class ApiClient {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

}
