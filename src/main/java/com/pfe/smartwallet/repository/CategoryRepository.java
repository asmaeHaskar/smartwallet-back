package com.pfe.smartwallet.repository;

import com.pfe.smartwallet.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // C'est ce fichier qui "donne" le findById à ton service !
}