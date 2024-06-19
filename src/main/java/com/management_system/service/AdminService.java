package com.management_system.service;

import com.management_system.model.Admin;
import com.management_system.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    public Boolean isEmpty() {
        return adminRepository.count() == 0;
    }

    public void saveAllAdmins(List<Admin> admins) {
        adminRepository.saveAll(admins);
    }

    public Boolean authenticateAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);

        if (admin == null) {
            return false;
        }
        return encoder.matches(password, admin.getPassword());
    }


}
