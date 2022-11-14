package com.ssafy.foodtruck.model.service;

import com.ssafy.foodtruck.constant.OrdersErrorMessage;
import com.ssafy.foodtruck.db.entity.Survey;
import com.ssafy.foodtruck.db.entity.User;
import com.ssafy.foodtruck.db.entity.UserType;
import com.ssafy.foodtruck.db.repository.SurveyRepository;
import com.ssafy.foodtruck.db.repository.UserRepository;
import com.ssafy.foodtruck.dto.request.FindSurveyReq;
import com.ssafy.foodtruck.dto.request.SurveyReq;
import com.ssafy.foodtruck.dto.response.SurveyRes;
import com.ssafy.foodtruck.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SurveyService {

	private final UserRepository userRepository;
	private final SurveyRepository surveyRepository;

	public void submitSurvey(int customerId, SurveyReq surveyReq) {
		User user = userRepository.findById(customerId)
			.orElseThrow(() -> new NotFoundException(OrdersErrorMessage.NOT_FOUND_USER));

		Survey survey = Survey.builder()
			.address(surveyReq.getAddress())
			.category(surveyReq.getCategory())
			.latitude(surveyReq.getLatitude())
			.longitude(surveyReq.getLongitude())
			.user(user)
			.build();
		surveyRepository.save(survey);
	}

	public List<SurveyRes> getSurvey(int ceoId, FindSurveyReq findSurveyReq) {
		if (userRepository.findById(ceoId).get().getUserType() != UserType.CEO) {
			throw new NotFoundException(OrdersErrorMessage.NOT_FOUND_USER);
		}
		List<SurveyRes> surveyResList = new ArrayList<>();

		for (Survey survey : surveyRepository.findSurveyNearby(findSurveyReq.getLatitude(), findSurveyReq.getLongitude())) {
			surveyResList.add(
				SurveyRes.builder()
					.category(survey.getCategory())
					.latitude(survey.getLatitude())
					.longitude(survey.getLongitude())
					.address(survey.getAddress())
					.build());
		}
		return surveyResList;
	}
}
