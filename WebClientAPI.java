  return recommendationsWebClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam(RecommendationConstants.ACTION_TOKEN, actionToken)
                                .queryParam(RecommendationConstants.USER_ID, userId)
                                .build())
                        .retrieve()
                        .onStatus(HttpStatus::is4xxClientError, this::buildRiskClientException)
                        .onStatus(HttpStatus::is5xxServerError, this::buildRiskServerException)
                        .bodyToMono(RecommendationResponse.class)
                        .block();
    }

    public Mono<Throwable> buildRiskClientException(ClientResponse response) {
        return response.bodyToMono(RecommendationErrorResponseDto.class)
                .flatMap(errorResponse -> Mono.error(new RecommendationBadException(response.statusCode(), errorResponse,
                        new Throwable(errorResponse.getMessage()))));
    }

public class RecommendationBadException extends RuntimeException {
    @Getter
    private final HttpStatus httpStatus;
    @Getter
    private final String messages;

    public RecommendationException(HttpStatus httpStatus, RecommendationErrorResponseDto errorResponseDto,
            Throwable cause) {
        super(cause);
        this.httpStatus = httpStatus;
        this.messages = errorResponseDto.getMessage();
    }
}
