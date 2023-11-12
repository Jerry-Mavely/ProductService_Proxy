package com.example.productservice_proxy.services;

import com.example.productservice_proxy.Clients.FakeStore.Dto.FakeStoreProductDto;
import com.example.productservice_proxy.Clients.IClientProductDto;
import com.example.productservice_proxy.dtos.ProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] productDtos = restTemplate.getForEntity
                ("https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : productDtos) {
            Product product = getProduct(productDto);
            products.add(product);
        }

        return products;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto productDto = restTemplate.getForEntity
                ("https://fakestoreapi.com/products/{Id}",
                FakeStoreProductDto.class,productId).getBody();

        Product product = getProduct(productDto);

        return product;
    }



    @Override
    public Product addNewProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.postForEntity("https://fakestoreapi.com/products",
                product, Product.class);

//        Product product = getProduct((FakeStoreProductDto) productDto);
        return product;
    }

    @Override
    public Product updateProduct(Long productId,Product product) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        restTemplate.put("https://fakestoreapi.com/products/{Id}",
//                productDto, productId);

        FakeStoreProductDto fakeStoreProductDto =new FakeStoreProductDto();

        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImageURL(product.getImageURL());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                requestForEntity(
                        HttpMethod.PATCH,
                        "https://fakestoreapi.com/products/{Id}",
                        fakeStoreProductDto,
                        FakeStoreProductDto.class,
                        productId
                );
        FakeStoreProductDto fakeStoreProductDto1 = fakeStoreProductDtoResponseEntity.getBody();
        return getProduct(fakeStoreProductDto1);
    }

    @Override
    public Product deleteProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{Id}",
                productId);
        return new Product();
    }

    private Product getProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
