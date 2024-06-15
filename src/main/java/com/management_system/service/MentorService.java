package com.management_system.service;

import com.management_system.model.Mentor;
import com.management_system.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepository mentorRepository;

    public Boolean isEmpty() {
        return mentorRepository.count() == 0;
    }

    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    public void saveAllMentors(List<Mentor> mentors) {
        mentorRepository.saveAll(mentors);
    }

    public Mentor getMentorById(long id) {
        return mentorRepository.findById(id).orElse(null);
    }
}
