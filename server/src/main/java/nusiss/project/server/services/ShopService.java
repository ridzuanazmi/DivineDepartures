package nusiss.project.server.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import nusiss.project.server.models.Shop;
import nusiss.project.server.models.ShopDto;
import nusiss.project.server.models.user.User;
import nusiss.project.server.repositories.ShopRepository;
import nusiss.project.server.repositories.UserRepository;

@Service
public class ShopService {

  private final ShopRepository shopRepo;
  private final UserRepository userRepo;

  public ShopService(
      ShopRepository shopRepo,
      UserRepository userRepo) {
    this.shopRepo = shopRepo;
    this.userRepo = userRepo;
  }

  public Shop saveShop(Shop shop) {
    return this.shopRepo.save(shop);
  }

  public Shop createShop(ShopDto shopDto) {

    Optional<User> optionalUser = userRepo.findByEmail(shopDto.getEmail());
    if (!optionalUser.isPresent()) {
        // handle this case as appropriate for your application, for example:
        throw new RuntimeException("No user found with email: " + shopDto.getEmail());
    }

    User user = optionalUser.get();
    Shop shop = new Shop();
    // set shop fields from shopDto
    shop.setUser(user);
    shopRepo.save(shop);
    return shop;
  }
}
