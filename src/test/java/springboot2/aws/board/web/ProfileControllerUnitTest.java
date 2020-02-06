package springboot2.aws.board.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

public class ProfileControllerUnitTest {
	@Test
	public void real_profile() {
		//given
		String expectedProfile = "real";
		
		MockEnvironment env = new MockEnvironment();
		env.addActiveProfile(expectedProfile);
		env.addActiveProfile("oauth");
		env.addActiveProfile("real-db");
		
	    ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();

        //then
        assertThat(profile).isEqualTo(expectedProfile);
	}
	
	@Test
	public void real_profile_none_first() {
		//given
		String expectedProfile = "oauth";
		
		MockEnvironment env = new MockEnvironment();
		env.addActiveProfile(expectedProfile);
		env.addActiveProfile("real-db");
		
		ProfileController controller = new ProfileController(env);
		
		//when
		String profile = controller.profile();
		
		//then
		assertThat(profile).isEqualTo(expectedProfile);
	}
	
	@Test
	public void active_profile_none() {
		//given
		String expectedProfile = "default";
		
		MockEnvironment env = new MockEnvironment();
		
		ProfileController controller = new ProfileController(env);
		
		//when
		String profile = controller.profile();
		
		//then
		assertThat(profile).isEqualTo(expectedProfile);
	}
}
