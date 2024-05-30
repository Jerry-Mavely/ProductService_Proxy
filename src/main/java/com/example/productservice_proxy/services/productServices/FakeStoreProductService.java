package com.example.productservice_proxy.services.productServices;

import com.example.productservice_proxy.Clients.FakeStore.Client.FakeStoreClient;
import com.example.productservice_proxy.Clients.FakeStore.Dto.FakeStoreProductDto;
import com.example.productservice_proxy.models.Categories;
import com.example.productservice_proxy.models.Product;

import java.util.ArrayList;
import java.util.List;

//@Service
public class FakeStoreProductService implements IProductService {

//    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductService(FakeStoreClient fakeStoreClient) {
//        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

//    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
//                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
//        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
//                HttpComponentsClientHttpRequestFactory.class
//        ).build();
//        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
//        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
//        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
//    }
    @Override
    public List<Product> getAllProducts() {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        FakeStoreProductDto[] productDtos = restTemplate.getForEntity
//                ("https://fakestoreapi.com/products",
//                        FakeStoreProductDto[].class).getBody();
//        List<Product> products = new ArrayList<>();
//        List<FakeStoreProductDto> fakeStoreProductDtos =
//                                            fakeStoreClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : fakeStoreClient.getAllProducts()) {
            Product product = getProduct(productDto);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getSingleProduct(Long productId) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        FakeStoreProductDto productDto = restTemplate.getForEntity
//                ("https://fakestoreapi.com/products/{Id}",
//                FakeStoreProductDto.class,productId).getBody();
//
//        Product product = getProduct(productDto);
        Product product = getProduct(fakeStoreClient.getSingleProduct(productId));
        return product;
    }

    @Override
    public Product addNewProduct(Product product) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        restTemplate.postForEntity("https://fakestoreapi.com/products",
//                product, Product.class);

//        Product product = getProduct((FakeStoreProductDto) productDto);
//        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto(product);
        Product savedProduct = getProduct(fakeStoreClient.
                                addNewProduct(new FakeStoreProductDto(product)));
        return savedProduct;
    }

    @Override
    public Product updateProduct(Long productId,Product product) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        restTemplate.put("https://fakestoreapi.com/products/{Id}",
//                productDto, productId);
//        FakeStoreProductDto fakeStoreProductDto =new FakeStoreProductDto(product);
//        fakeStoreProductDto.setDescription(product.getDescription());
//        fakeStoreProductDto.setCategory(product.getCategory().getName());
//        fakeStoreProductDto.setImageURL(product.getImageURL());
//        fakeStoreProductDto.setPrice(product.getPrice());
//        fakeStoreProductDto.setTitle(product.getTitle());
//        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
//                requestForEntity(
//                        HttpMethod.PATCH,
//                        "https://fakestoreapi.com/products/{Id}",
//                        fakeStoreProductDto,
//                        FakeStoreProductDto.class,
//                        productId
//                );
//        FakeStoreProductDto fakeStoreProductDto1 = fakeStoreProductDtoResponseEntity.getBody();
        Product savedProduct = getProduct(fakeStoreClient.updateProduct(
                                        productId,new FakeStoreProductDto(product)));
        return savedProduct;
    }

    @Override
    public Product deleteProduct(Long productId) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        restTemplate.delete("https://fakestoreapi.com/products/{Id}",
//                productId);
        Product deletedProduct = getProduct(fakeStoreClient.deleteProduct(productId));
        return new Product();
    }



    /**
     * This method converts FakeStoreProductDto to Product
     * @parameter: FakeStoreProductDto
     * @return Product
     */
    private Product getProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImage());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
