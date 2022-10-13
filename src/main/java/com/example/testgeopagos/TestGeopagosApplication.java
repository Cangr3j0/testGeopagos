package com.example.testgeopagos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.config.SecurityConfig;
import com.example.entities.FemaleTenisPlayer;
import com.example.entities.FemaleTournament;
import com.example.entities.MaleTenisPlayer;
import com.example.entities.MaleTournament;
import com.example.entities.Player;
import com.example.entities.Tournament;
import com.example.repositories.FemTournamentRepository;
import com.example.repositories.FemalePlayerRepository;
import com.example.repositories.MalePlayerRepository;
import com.example.repositories.MaleTournamentRepository;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@SpringBootApplication(scanBasePackages={"com.example.testgeopagos",
"com.example.entities","com.example.repositories","com.example.config"},exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@ComponentScan({"com.example.entities","com.example.repositories","com.example.testgeopagos"})
@EntityScan("com.example.entities")
@EnableJpaRepositories("com.example.repositories")
public class TestGeopagosApplication {
	
    @Autowired
    DataSource dataSource;
    @Autowired
    MalePlayerRepository malePlayerRepository;
    @Autowired
    FemalePlayerRepository femalePlayerRepository;
    @Autowired
    FemTournamentRepository femTournamentRepository;
    @Autowired
    MaleTournamentRepository maleTournamentRepository;
	public static void main(String[] args) {
		SpringApplication.run(TestGeopagosApplication.class, args);
	}
//
//	@Bean
//	public OpenAPI customOpenAPI(@Value("2") String appVersion) {
//		return new OpenAPI()
//				.components(new Components())
//				.info(new Info().title("Customer accounts API").version(appVersion)
//						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
//	}
//	
	 @Bean
	    CommandLineRunner init() {
	    	return args ->{
	    		MaleTenisPlayer malePlayer=new MaleTenisPlayer("Pedro");
	    		MaleTenisPlayer malePlayer2=new MaleTenisPlayer("August");
	    		malePlayerRepository.save(malePlayer);
	    		malePlayerRepository.save(malePlayer2);
	    		ArrayList<MaleTenisPlayer>maleListPlayers=new ArrayList<MaleTenisPlayer>();
	    		maleListPlayers.add(malePlayer);
	    		maleListPlayers.add(malePlayer2);
	    		Tournament<MaleTenisPlayer> maleTournament=new MaleTournament();
	    		maleTournament.getParticipants().add(malePlayer);
	    		maleTournament.getParticipants().add(malePlayer2);
	    		maleTournamentRepository.save(maleTournament);
	    		
	    		
	    		
	    		FemaleTenisPlayer femalePlayer=new FemaleTenisPlayer("Marie", 100, 20);
	    		FemaleTenisPlayer femalePlayer2=new FemaleTenisPlayer("Sophie", 80, 10);
	    		femalePlayerRepository.save(femalePlayer);
	    		femalePlayerRepository.save(femalePlayer2);
	    		ArrayList<FemaleTenisPlayer>femaleListPlayers=new ArrayList<FemaleTenisPlayer>();
	    		femaleListPlayers.add((FemaleTenisPlayer) femalePlayer);
	     		femaleListPlayers.add((FemaleTenisPlayer) femalePlayer2);
	    		Tournament<FemaleTenisPlayer> femaleTournament=new FemaleTournament();
	    		femaleTournament.getParticipants().add(femalePlayer);
	    		femaleTournament.getParticipants().add(femalePlayer2);
	    		femTournamentRepository.save(femaleTournament);
	    		
	    	};
	    }
}
