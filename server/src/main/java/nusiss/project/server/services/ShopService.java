package nusiss.project.server.services;

import org.springframework.stereotype.Service;

import nusiss.project.server.models.Shop;
import nusiss.project.server.repositories.ShopRepository;

@Service
public class ShopService {

  private final ShopRepository shopRepo;

  public ShopService(ShopRepository shopRepo) {
    this.shopRepo = shopRepo;
  }
  
  public Shop saveShop(Shop shop) {
    return this.shopRepo.save(shop);
  }
}
