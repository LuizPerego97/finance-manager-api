package com.luizperego.finance_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luizperego.finance_manager.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}
