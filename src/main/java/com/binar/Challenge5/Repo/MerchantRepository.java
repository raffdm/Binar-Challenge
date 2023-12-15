package com.binar.Challenge5.Repo;

import com.binar.Challenge5.Controller.Entity.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    boolean existsByMerchName(String merchName);

    boolean existsByMerchLocation(String merchLocation);

    Optional<Merchant> findByMerchCode(String kode);

    Page<Merchant> findByDeletedIsFalseAndOpenIsTrue(Pageable pageable);

    List<Merchant> findByDeletedFalse();

    Optional<List<Merchant>> findByMerchNameContaining(String nama);
}
