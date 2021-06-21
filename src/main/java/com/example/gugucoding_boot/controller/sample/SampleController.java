package com.example.gugucoding_boot.controller.sample;

import com.example.gugucoding_boot.entity.sample.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Controller
@RequestMapping("sample")
public class SampleController {

	@GetMapping("models")
	public void sampleModelList(Model model) {
		List<SampleDTO> sampleModels =
				LongStream.rangeClosed(1, 20).mapToObj(l -> SampleDTO.builder()
						.id(l)
						.first("First..." + l)
						.last("Last..." + l)
						.registrationDateTime(LocalDateTime.now()).build()).collect(Collectors.toList());
		model.addAttribute("sampleModels", sampleModels);
	}
}
