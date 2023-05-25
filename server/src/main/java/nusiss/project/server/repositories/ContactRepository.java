package nusiss.project.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nusiss.project.server.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, String> {

}
