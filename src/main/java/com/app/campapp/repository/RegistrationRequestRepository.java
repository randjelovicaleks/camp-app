package com.app.campapp.repository;

import com.app.campapp.model.RegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {

    RegistrationRequest findByEmail(String email);

}
