package org.example.jpa.multipledb;


import org.example.jpa.multipledb.dao.product.ProductRepository;
import org.example.jpa.multipledb.dao.user.PossessionRepository;
import org.example.jpa.multipledb.dao.user.UserRepository;
import org.example.jpa.multipledb.model.product.Product;
import org.example.jpa.multipledb.model.user.Possession;
import org.example.jpa.multipledb.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MultipleDbApplication.class)
public class JpaMultipleDBIntegrationTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PossessionRepository possessionRepository;
    @Autowired
    private ProductRepository productRepository;


    @Test
    @Rollback(value = false)
    @Transactional("userTransactionManager")
    public void whenCreatingUser_thenCreated() {
        Possession p = new Possession("sample");
        p = possessionRepository.save(p);

        User user = User.builder().name("John").email("john@test.com").age(20).possessionList(Collections.singletonList(p)).build();
        user = userRepository.save(user);

        final Optional<User> result = userRepository.findById(user.getId());
        assertTrue(result.isPresent());
        System.out.println(result.get().getPossessionList());
        assertEquals(1, result.get().getPossessionList().size());

    }

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUsersWithSameEmail_thenRollback() {
        User user1 = User.builder().name("John").email("john@test.com").age(20).build();
        user1 = userRepository.save(user1);
        assertTrue(userRepository.findById(user1.getId()).isPresent());

        User user2 = User.builder().name("John").email("john@test.com").age(10).build();
        try {
            user2 = userRepository.save(user2);
            userRepository.flush();
            fail("DataIntegrityViolationException should be thrown!");
        } catch (final DataIntegrityViolationException e) {
            // Expected
        } catch (final Exception e) {
            fail("DataIntegrityViolationException should be thrown, instead got: " + e);
        }
    }

    @Test
    @Rollback(value = false)
    @Transactional("productTransactionManager")
    public void whenCreatingProduct_thenCreated() {
        Product product = Product.builder().name("Book").price(20).id(2).build();
        product = productRepository.save(product);
        assertTrue(productRepository.findById(product.getId()).isPresent());
    }

}
