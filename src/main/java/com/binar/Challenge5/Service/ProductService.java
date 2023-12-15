package com.binar.Challenge5.Service;

import com.binar.Challenge5.DTO.Product.*;
import com.binar.Challenge5.DTO.ResponseHandling;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ResponseHandling<ProductResponseDTO> createProduct(ProductRequestDTO productRequestDTO);

    ResponseHandling<List<ProductGetResponseDTO>> getData(Pageable pageable);

    ResponseHandling<ProductResponseDTO> updateData(String productCode, ProductUpdateRequestDTO requestDTO);

    ResponseHandling<List<ProductGetResponseDTO>> getDataByName(String name);

    ProductDeleteResponseDTO deleteData(String code);
}
