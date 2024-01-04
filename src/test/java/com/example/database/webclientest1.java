 * Copyright (C) 2022 Allegiant Air Company - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.test.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SuppressWarnings("unchecked")
class RecommendationWebClientTest {

    @InjectMocks
    RecommendationWebClient recommendationsWebClient;
    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;
    
    @Mock
    private ClientResponse clientResponse;

    @Test
    void getRecommendations() {

        String actionToken = "dummy-token";
        String userId = "1234";
        RecommendationResponse recommendationResponse = new RecommendationResponse();
        recommendationResponse.setRecommendation(new Recommendation("Allow"));
        recommendationResponse.setId("12345");
        recommendationResponse.setRiskScore(90.3);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(Mockito.any(Function.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(RecommendationResponse.class)))
                .thenReturn(Mono.just(recommendationResponse));

        RecommendationResponse response = recommendationsWebClient.getRecommendations(actionToken, userId);
        assertNotNull(response);

    }

    @Test
    void getRecommendationsTest() {

        String actionToken = "dummy-token";
        String userId = "1234";
        RecommendationResponse recommendationResponse = new RecommendationResponse();
        recommendationResponse.setRecommendation(new Recommendation("Allow"));
        recommendationResponse.setId("12345");
        recommendationResponse.setRiskScore(90.3);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(Mockito.any(Function.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(),any(Function.class))).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(RecommendationResponse.class)))
                .thenReturn(Mono.just(recommendationResponse));

        RecommendationResponse response = recommendationsWebClient.getRecommendations(actionToken, userId);
        assertEquals(recommendationResponse.getId(), response.getId());

    }

    @Test
    public void testClientException() {

        RecommendationErrorResponseDto errorResponse = new RecommendationErrorResponseDto();
        errorResponse.setMessage("Action token is missing");
        
        Mono<RecommendationErrorResponseDto> error = Mono.just(errorResponse);

        when(clientResponse.bodyToMono(RecommendationErrorResponseDto.class)).thenReturn(error);
        
        recommendationsWebClient.buildRiskClientException(clientResponse);

        assertEquals("Action token is missing", error.block().getMessage());

    }
    
    @Test
    public void testServerException() {

        recommendationsWebClient.buildRiskServerException(clientResponse);

    }
}
