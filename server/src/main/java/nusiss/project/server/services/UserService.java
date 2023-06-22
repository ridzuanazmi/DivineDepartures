package nusiss.project.server.services;

import java.util.List;

import org.springframework.stereotype.Service;

import nusiss.project.server.models.user.User;
import nusiss.project.server.repositories.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepo;

  public UserService(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  public List<User> getAllUsers() {
    return userRepo.findAll();
  }
}
