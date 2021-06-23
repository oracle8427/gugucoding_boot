package com.example.gugucoding_boot.entity.sample;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="memo")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Memo implements Comparable<Memo> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 200, nullable = false)
	private String memoText;


	@Override
	public int compareTo(Memo o) {
		if (id > o.id)
			return 1;
		else if(id == o.id)
			return 0;
		return -1;
	}
}
