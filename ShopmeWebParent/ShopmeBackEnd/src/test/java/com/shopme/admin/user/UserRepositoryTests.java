package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userAjay = new User("ajaykumar.gupta04@gmail.com", "12345", "Ajay", "Gupta");
		userAjay.addRoles(roleAdmin);
		User savedUser = userRepo.save(userAjay);
		assertThat(savedUser.getId()).isGreaterThan(0);

	}

	@Test
	public void testCreateNewUserWithTwoRoles() {

		User userRitu = new User("ritu@gmail.com", "Love", "Ritika", "Kumari");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		userRitu.addRoles(roleEditor);
		userRitu.addRoles(roleAssistant);
		User savedUser = userRepo.save(userRitu);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAllUsers() {
		Iterable<User> listUser = userRepo.findAll();
		listUser.forEach(user -> System.out.println(user));
	}

	@Test
	public void testGetUserById() {
		User userAjay = userRepo.findById(1).get();
		System.out.println(userAjay);
		assertThat(userAjay).isNotNull();
	}
	@Test
	public void testUpdateUserDetails() {
		User userAjay = userRepo.findById(1).get();
		userAjay.setEnabled(true);
		userAjay.setEmail("ajay@gmail.com");
		userRepo.save(userAjay);
		
	}
	@Test
	public void testUpdateUserRoles() {
		User userRitu = userRepo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSelsPerson=new Role(2);
		userRitu.getRoles().remove(roleSelsPerson);
		userRitu.addRoles(roleEditor);
		userRepo.save(userRitu);
	}
    @Test
    public void testDeleteUserById() {
    	Integer id=2;
    	userRepo.deleteById(id);
    }

}
