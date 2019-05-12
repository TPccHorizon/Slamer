package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.exception.SlaUserNotFoundException;
import ch.uzh.slamer.backend.repository.JooqSlaUserRepository;
import codegen.tables.pojos.SlaUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private JooqSlaUserRepository userRepository;

    public UserDetailsServiceImpl(JooqSlaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SlaUser user;
        try {
            user = userRepository.findByUsername(username);
        } catch (SlaUserNotFoundException e) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getUsername(), user.getPassword(), emptyList());
    }

//    @Override
//    public User registerNewUserAccount(SlaUser user) {
//
//    }
}
