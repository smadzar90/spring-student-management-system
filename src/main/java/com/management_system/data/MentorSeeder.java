package com.management_system.data;


import com.management_system.model.Mentor;
import com.management_system.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MentorSeeder {

    private final MentorService mentorService;

    /**
     * Seed the data to mentor table only if StudentSystemDB is empty
     * Default data will be seeded
     */
    public void setUp() {
        List<Mentor> mentors = new ArrayList<Mentor>();

        mentors.add(new Mentor("Alexander", "Williams", 'M', "alexander@gmail.com", "(919) 456-7890"));
        mentors.add(new Mentor("Benjamin", "Garcia", 'M', "benjamin@gmail.com", "(626) 257-4921"));
        mentors.add(new Mentor("Isabella", "Taylor", 'F', "isabella@gmail.com", "(919) 222-7692"));
        mentors.add(new Mentor("Oliver", "Davis", 'M', "oliver@gmail.com", "(828) 531-9897"));
        mentors.add(new Mentor("Sophia", "Martinez", 'F', "sophia@gmail.com", "(425) 765-4321"));
        mentors.add(new Mentor("James", "Lee", 'M', "jamesl@gmail.com", "(878) 461-3553"));

        mentorService.saveAllMentors(mentors);
        System.out.println("Mentor records seeded to database!");
    }
}
