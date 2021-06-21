package com.example.gugucoding_boot.entity.sample;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class SampleDTO {

	private long id;

	private String first;

	private String last;

	private LocalDateTime registrationDateTime;

}
