package com.example.gugucoding_boot.controller.sample;

import com.example.gugucoding_boot.entity.sample.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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

	@GetMapping("model")
	public String sampleModel(@RequestParam long id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("id", id);
		return "redirect:/sample/inline";
	}

	@GetMapping("model/{id}")
	public String sampleModel2(@PathVariable long id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("id", id);
		return "redirect:/sample/inline";
	}

	@GetMapping("inline")
	public void inlineJS(Model model, @RequestParam(value = "id", required = false) Long id) {
		if (id == null) {
			Random random = new Random();
			id = (long) (random.nextInt(30) + 1);
		}

		SampleDTO sampleModel = SampleDTO.builder()
				.id(id)
				.first("[first]Inline JS Test...")
				.last("[last]Inline JS Test...")
				.registrationDateTime(LocalDateTime.now()).build();
		model.addAttribute("sampleModel", sampleModel);
	}

}
