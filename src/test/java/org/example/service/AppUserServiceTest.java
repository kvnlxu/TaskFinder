package org.example.service;

import org.example.taskfinderapp.model.AppUser;
import org.example.taskfinderapp.repository.AppUserRepository;
import org.example.taskfinderapp.service.AppUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AppUserServiceTest {
    @Mock
    private AppUserRepository appUserRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AppUserService appUserService;

    @Test
    public void testSave() {
        AppUser user = new AppUser();
        Mockito.when(appUserRepository.save(any(AppUser.class))).thenReturn(user);
        appUserService.save(user);
        Mockito.verify(appUserRepository).save(user);
    }

    @Test
    public void testFindByUsername() {
        AppUser user = new AppUser();
        Mockito.when(appUserRepository.findByUsername(any(String.class))).thenReturn(Optional.of(user));
        appUserService.loadUserByUsername("test");
        Mockito.verify(appUserRepository).findByUsername(any(String.class));
    }
}
