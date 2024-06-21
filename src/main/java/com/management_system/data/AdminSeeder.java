package com.management_system.data;

import com.management_system.model.Admin;
import com.management_system.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminSeeder {

    private final AdminService adminService;

    /**
     * Seed the data to admin table only if StudentSystemDB is empty
     * Default data will be seeded
     */
    public void setUp() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword1 = encoder.encode("access123@");
        String encodedPassword2 = encoder.encode("admin123@");
        String encodedPassword3 = encoder.encode("mypass123@");

        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin("admin1", encodedPassword1, "admin1@gmail.com"));
        admins.add(new Admin("admin2", encodedPassword2, "admin2@gmail.com"));
        admins.add(new Admin("admin3", encodedPassword3, "admin3@gmail.com"));

        adminService.saveAllAdmins(admins);
        System.out.println("Admin records seeded to database!");
    }
}