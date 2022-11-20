package com.ssafy.foodtruck.db.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

	@NotNull
	@Column(length = 3000)
	private String content;

	@NotNull
	private Integer grade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "orders_id")
	private Orders orders;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "review_img_id")
	private ReviewImg reviewImg;

	public void setReviewImg(ReviewImg reviewImg) {
		this.reviewImg = reviewImg;
	}
}
