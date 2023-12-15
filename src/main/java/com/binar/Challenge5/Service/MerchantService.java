package com.binar.Challenge5.Service;

import com.binar.Challenge5.DTO.Merchant.MerchantDeleteResponseDTO;
import com.binar.Challenge5.DTO.Merchant.MerchantRequestDTO;
import com.binar.Challenge5.DTO.Merchant.MerchantResponseDTO;
import com.binar.Challenge5.DTO.Merchant.MerchantUpdateRequest;
import com.binar.Challenge5.DTO.ResponseHandling;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MerchantService {
    ResponseHandling<MerchantResponseDTO> createMerchant(MerchantRequestDTO merchantRequestDTO);

    ResponseHandling<List<MerchantResponseDTO>> getData(Pageable pageable);

    ResponseHandling<MerchantResponseDTO> updateMerchant(String code, MerchantUpdateRequest requestDTO);

    MerchantDeleteResponseDTO deleteData(String code);
}
