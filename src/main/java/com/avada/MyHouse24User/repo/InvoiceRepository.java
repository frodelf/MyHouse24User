package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Page<Invoice> findByNumberContainingIgnoreCase(String search, Pageable pageable);
}
