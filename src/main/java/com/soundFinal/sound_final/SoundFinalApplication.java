package com.soundFinal.sound_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SoundFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoundFinalApplication.class, args);
	}

}
