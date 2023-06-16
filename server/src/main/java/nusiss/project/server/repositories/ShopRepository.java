package nusiss.project.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nusiss.project.server.models.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer>{
  
}
