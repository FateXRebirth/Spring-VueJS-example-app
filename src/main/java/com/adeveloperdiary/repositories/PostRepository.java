package com.adeveloperdiary.repositories;

import com.adeveloperdiary.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by FateXRebirth on 15/09/2017.
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
