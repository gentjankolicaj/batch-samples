package com.example.batch_samples.batch0.batch;

import com.example.batch_samples.batch0.model.User;
import com.example.batch_samples.batch0.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomItemWriter implements ItemWriter<User> {
    private final UserRepository userRepository;

    @Autowired
    public CustomItemWriter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void write(List<? extends User> list) throws Exception {
        userRepository.saveAll(list);
    }

}
