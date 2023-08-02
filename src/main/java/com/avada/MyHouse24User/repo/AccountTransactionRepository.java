package com.avada.MyHouse24User.repo;

import com.avada.MyHouse24User.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {
    @Query(value = "SELECT MAX(id) FROM AccountTransaction")
    Long findMaxId();

    @Query(value = "SELECT SUM(a.sum) FROM AccountTransaction a WHERE a.isIncome = true")
    Long sumWhereIsIncomeIsTrue();

    @Query(value = "SELECT SUM(a.sum) FROM AccountTransaction a WHERE a.isIncome = false")
    Long sumWhereIsIncomeIsFalse();

}
