package it.polito.tdp.yelp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Review {
	private String reviewId ;
	
	// private String businessId ;
	private String businessId ; // always loaded
	private Business business ; // lazy loaded --> l'oggetto lo si carica solo al bisogno
	private String userId ; // always loaded
	private User user ; // lazy loaded
	
	private Double stars ;
	private LocalDate reviewDate ;
	private Integer votesFunny ;
	private Integer votesUseful ;
	private Integer votesCool ;
	private String reviewText ;
	
	public Review(String reviewId, String businessId, String userId, Double stars, LocalDate reviewDate,
			Integer votesFunny, Integer votesUseful, Integer votesCool, String reviewText) {
		super();
		this.reviewId = reviewId;
		this.businessId = businessId;
		this.userId = userId;
		this.stars = stars;
		this.reviewDate = reviewDate;
		this.votesFunny = votesFunny;
		this.votesUseful = votesUseful;
		this.votesCool = votesCool;
		this.reviewText = reviewText;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getStars() {
		return stars;
	}

	public void setStars(Double stars) {
		this.stars = stars;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Integer getVotesFunny() {
		return votesFunny;
	}

	public void setVotesFunny(Integer votesFunny) {
		this.votesFunny = votesFunny;
	}

	public Integer getVotesUseful() {
		return votesUseful;
	}

	public void setVotesUseful(Integer votesUseful) {
		this.votesUseful = votesUseful;
	}

	public Integer getVotesCool() {
		return votesCool;
	}

	public void setVotesCool(Integer votesCool) {
		this.votesCool = votesCool;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reviewId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(reviewId, other.reviewId);
	}
	
	
	

}
