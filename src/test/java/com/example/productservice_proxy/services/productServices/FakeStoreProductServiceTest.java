package com.example.productservice_proxy.services.productServices;

import com.example.productservice_proxy.Clients.FakeStore.Client.FakeStoreClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FakeStoreProductServiceTest {

    @Autowired
    private FakeStoreClient fakeStoreClient;
    @Test
    public void Test_FakseStoreClient(){

    }

}