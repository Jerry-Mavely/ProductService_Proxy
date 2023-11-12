package com.example.productservice_proxy.Clients.FakeStore.Client;

import com.example.productservice_proxy.Clients.FakeStore.Dto.FakeStoreProductDto;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreClient {

    RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public List<FakeStoreProductDto> getAllProducts() {
//        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> fSProducts = requestForEntity(
                HttpMethod.GET,
                "https://fakestoreapi.com/products",
                null,
                FakeStoreProductDto[].class
        );

        return Arrays.asList(fSProducts.getBody());
    }

    public FakeStoreProductDto getSingleProduct(Long productId) {

        ResponseEntity<FakeStoreProductDto> FsProduct= requestForEntity(
                HttpMethod.GET,
                "https://fakestoreapi.com/products/{Id}",
                null,
                FakeStoreProductDto.class,
                productId
        );

        return FsProduct.getBody();
    }

    public FakeStoreProductDto addNewProduct(FakeStoreProductDto fSProduct) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> savedFsProductDto = requestForEntity(
                HttpMethod.POST,
                "https://fakestoreapi.com/products",
                fSProduct,
                FakeStoreProductDto.class
        );
        return savedFsProductDto.getBody();
    }

    public FakeStoreProductDto updateProduct(Long productId, FakeStoreProductDto fSProduct) {
        ResponseEntity<FakeStoreProductDto> savedFsProductDto = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{Id}",
                fSProduct,
                FakeStoreProductDto.class,
                productId
        );
        return savedFsProductDto.getBody();
    }

    public FakeStoreProductDto deleteProduct(Long productId){
        ResponseEntity<FakeStoreProductDto> FsProductDto = requestForEntity(
                HttpMethod.DELETE,
                "https://fakestoreapi.com/products/{Id}",
                null,
                FakeStoreProductDto.class,
                productId
        );
        return FsProductDto.getBody();
    }




}