package FinalProject.ApiMessageSystem;

import FinalProject.ApiMessageSystem.Models.Security.MyUser;
import FinalProject.ApiMessageSystem.Models.Security.Role;
import FinalProject.ApiMessageSystem.Services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ApiMessageSystemApplicationTests {
	@Autowired
	private PasswordEncoder pE;
	@Autowired
	private UserService uA;
	@Test
	void contextLoads() {
		MyUser user = new MyUser();

		user.setUsername("user");
		user.setPassword(pE.encode("123"));
		Role role = new Role();
		role.setId(2);
		role.setName("USER");
		user.setRole(role);

		uA.save(user);
		MyUser r = uA.findByUsername(user.getUsername());
		Assert.assertTrue(r.getPassword().equalsIgnoreCase(user.getPassword()));

	}

}
