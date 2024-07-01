package com.mini.autorizador.domain.repository;

import com.mini.autorizador.domain.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTransactionRepository extends JpaRepository<CreditCard, Long> {
}
