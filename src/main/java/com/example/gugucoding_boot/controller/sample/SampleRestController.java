package com.example.gugucoding_boot.controller.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sample")
public class SampleRestController {

	@GetMapping("hello")
	public String[] Hello() {
		return new String[]{"Hello", "World"};
	}

}
