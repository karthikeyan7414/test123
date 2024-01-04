/*
 * Copyright (C) 2022 Allegiant Travel Company - All Rights Reserved
 *
 * This file is subject to the terms and conditions defined in file 'LICENSE.txt', which is part of this source code package.
 */
package com.allegiantair.navitaire.flight.order.client;


import com.allegiantair.navitaire.flight.order.config.client.AddCommentProperties;
import com.allegiantair.navitaire.flight.order.config.client.AddPaymentProperties;
import com.allegiantair.navitaire.flight.order.config.client.BookingFeeProperties;
import com.allegiantair.navitaire.flight.order.config.client.BookingSsrsV1Properties;
import com.allegiantair.navitaire.flight.order.config.client.BundleSellProperties;
import com.allegiantair.navitaire.flight.order.config.client.CommitBookingProperties;
import com.allegiantair.navitaire.flight.order.config.client.CommitCancelOrderProperties;
import com.allegiantair.navitaire.flight.order.config.client.DeleteDispositionPnrProperties;
import com.allegiantair.navitaire.flight.order.config.client.DeleteFlightProperties;
import com.allegiantair.navitaire.flight.order.config.client.DeletePaymentProperties;
import com.allegiantair.navitaire.flight.order.config.client.NavitaireBookingProperties;
import com.allegiantair.navitaire.flight.order.config.client.NavitaireFareAdjustmentProperties;
import com.allegiantair.navitaire.flight.order.config.client.NavitaireFeeAdjustmentProperties;
import com.allegiantair.navitaire.flight.order.config.client.NavitairePassengerTypeProperties;
import com.allegiantair.navitaire.flight.order.config.client.NavitaireSellFlightProperties;
import com.allegiantair.navitaire.flight.order.config.client.PassengerAddAddressProperties;
import com.allegiantair.navitaire.flight.order.config.client.PassengerDeleteSeatProperties;
import com.allegiantair.navitaire.flight.order.config.client.PassengerGetProperties;
import com.allegiantair.navitaire.flight.order.config.client.PassengerGetSeatProperties;
import com.allegiantair.navitaire.flight.order.config.client.PassengerPutAddressProperties;
import com.allegiantair.navitaire.flight.order.config.client.PassengerSellSeatProperties;
import com.allegiantair.navitaire.flight.order.config.client.PassengerUpdateDetailsProperties;
import com.allegiantair.navitaire.flight.order.config.client.PaymentRefundProperties;
import com.allegiantair.navitaire.flight.order.config.client.QuoteCancelOrderProperties;
import com.allegiantair.navitaire.flight.order.config.client.RetrieveBookingProperties;
import com.allegiantair.navitaire.flight.order.config.client.SsrAvailabilityProperties;
import com.allegiantair.navitaire.flight.order.config.client.SsrSellProperties;
import com.allegiantair.navitaire.flight.order.config.client.UpdatePassengerTypeProperties;
import com.allegiantair.navitaire.flight.order.model.api.BookingStatus;
import com.allegiantair.navitaire.flight.order.model.api.PaymentAction;
import com.allegiantair.navitaire.flight.order.model.api.request.Comment;
import com.allegiantair.navitaire.flight.order.model.api.request.CommitBookingRequest;
import com.allegiantair.navitaire.flight.order.model.api.request.SellBundleRequest;
import com.allegiantair.navitaire.flight.order.model.navitaire.adjustments.NavitaireCreateFeeRequest;
import com.allegiantair.navitaire.flight.order.model.navitaire.adjustments.NavitaireFareAdjustmentRequest;
import com.allegiantair.navitaire.flight.order.model.navitaire.adjustments.NavitaireFeeAdjustmentRequest;
import com.allegiantair.navitaire.flight.order.model.navitaire.booking.NavitaireBookingResponse;
import com.allegiantair.navitaire.flight.order.model.navitaire.payment.PaymentRefundRequest;
import com.allegiantair.navitaire.flight.order.model.navitaire.request.DeleteDispositionPnrRequest;
import com.allegiantair.navitaire.flight.order.model.navitaire.response.NavitaireBookingData;
import com.allegiantair.navitaire.flight.order.model.navitaire.response.NavitairePassengerTypeDetails;
import com.allegiantair.navitaire.flight.order.model.navitaire.response.NavitairePassengerTypeResponse;
import com.allegiantair.navitaire.flight.order.model.navitaire.response.NavitairePassengersResponse;
import com.allegiantair.navitaire.flight.order.model.navitaire.seats.request.NavitaireSellSeatRequest;
import com.allegiantair.navitaire.flight.order.model.navitaire.seats.response.NavitaireGetSeatsResponse;
import com.allegiantair.navitaire.flight.order.model.navitaire.sell.bundle.request.NavitaireBundleSellRequest;
import com.allegiantair.navitaire.flight.order.model.navitaire.sell.flight.request.NavitaireSellFlightRequest;
import com.allegiantair.navitaire.flight.order.model.navitaire.sell.flight.response.NavitaireSellFlightResponse;
import com.allegiantair.navitaire.flight.order.model.navitaire.sell.ssr.NavitaireGetAttachedSsrResponse;
import com.allegiantair.navitaire.flight.order.model.navitaire.ssr.request.NavitaireSsrAvailabilityRequest;
import com.allegiantair.navitaire.flight.order.model.navitaire.ssr.response.NavitaireSsrAvailabilityJourneys;
import com.allegiantair.navitaire.flight.order.model.navitaire.ssr.response.NavitaireSsrAvailabilityResponse;
import com.allegiantair.navitaire.flight.order.model.navitaire.update.passenger.NavitairePassengerDiscountRequest;
import com.allegiantair.navitaire.flight.order.model.navitaire.update.passenger.NavitaireUpdatePassengerRequest;
import com.allegiantair.navitaire.flight.order.model.navitaire.update.passenger.PassengerAddress;
import com.allegiantair.navitaire.flight.order.model.navitaire.update.passenger.PassengerTravelDocument;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SuppressWarnings("unchecked")
public class NavitaireClientTest {

    private static final String DUMMY_AUTH_TOKEN = "dummy-auth-token";

    private static final String RECORD_LOCATOR = "dummy-record-locator";

    private static final String DUMMY_URI = "dummy-uri";

    private static final String DUMMY_PASSENGER_REF = "dummy-passenger-ref";

    private static final String RESPONSE_STRING = "response-string";

    private static final String DUMMY_ADDRESS_REF = null;

    @InjectMocks
    private NavitaireClient navitaireClient;
    
    @Mock
    private NavitaireSellFlightProperties navitaireSellFlightProperties;

    @Mock
    private NavitaireBookingProperties navitaireBookingProperties;

    @Mock
    private PassengerUpdateDetailsProperties passengerUpdateDetailsProperties;

    @Mock
    private PassengerGetProperties passengerGetProperties;

    @Mock
    private PassengerAddAddressProperties passengerAddAddressProperties;

    @Mock
    private PassengerPutAddressProperties passengerPutAddressProperties;

    @Mock
    private SsrAvailabilityProperties ssrAvailabilityProperties;

    @Mock
    private SsrSellProperties ssrSellProperties;

    @Mock
    private BookingSsrsV1Properties bookingSsrsV1Properties;

    @Mock
    private CommitBookingProperties commitBookingProperties;

    @Mock
    private AddPaymentProperties addPaymentProperties;

    @Mock
    private DeletePaymentProperties deletePaymentProperties;

    @Mock
    private PassengerGetSeatProperties passengerGetSeatProperties;

    @Mock
    private PassengerSellSeatProperties passengerSellSeatProperties;

    @Mock
    private PassengerDeleteSeatProperties passengerDeleteSeatProperties;

    @Mock
    private BundleSellProperties bundleSellProperties;

    @Mock
    private RetrieveBookingProperties retrieveBookingProperties;

    @Mock
    private NavitaireFeeAdjustmentProperties feeAdjustmentProperties;

    @Mock
    private NavitaireFareAdjustmentProperties fareAdjustmentProperties;

    @Mock
    private BookingFeeProperties bookingFeeProperties;


    @Mock
    private QuoteCancelOrderProperties quoteCancelOrderProperties;

    @Mock
    private CommitCancelOrderProperties commitCancelOrderProperties;

    @Mock
    private PaymentRefundProperties paymentRefundProperties;

    @Mock
    private NavitaireFeeAdjustmentProperties navitaireFeeAdjustmentProperties;
    @Mock
    private AddCommentProperties addCommentProperties;

    @Mock
    private WebClient webClient;

    @Mock
    private RequestBodyUriSpec requestBodyUriSpec;

    @SuppressWarnings("rawtypes")
    @Mock
    private RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RequestBodySpec requestBodySpec;

    @SuppressWarnings("rawtypes")
    @Mock
    private RequestHeadersSpec requestHeadersSpec;

    @Mock
    private ResponseSpec responseSpec;

    @Mock
    private NavitaireBookingData navitaireSellFlightData;

    @Mock
    private HttpHeaders httpHeaders;

    @Mock
    private DeleteFlightProperties deleteFlightProperties;
    @Mock
    private UpdatePassengerTypeProperties updatePassengerTypeProperties;
    @Mock
    private NavitairePassengerTypeProperties navitairePassengerTypeProperties;
    @Mock
    private DeleteDispositionPnrProperties deleteDispositionPnrProperties;

    @Test
    public void sellFlight_Test() {
        RequestHeadersSpec<?> reqHeadersSpec = mock(RequestHeadersSpec.class);
        NavitaireSellFlightRequest navitaireSellFlightRequest = new NavitaireSellFlightRequest();
        NavitaireSellFlightResponse navitaireSellFlightResponse = new NavitaireSellFlightResponse();
        navitaireSellFlightResponse.setData(new NavitaireBookingData());

        when(navitaireSellFlightProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Object.class), any(Class.class))).thenReturn((RequestHeadersSpec) reqHeadersSpec);
        when(reqHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(NavitaireSellFlightResponse.class))).thenReturn(Mono.just(navitaireSellFlightResponse));
        NavitaireBookingData response = navitaireClient.sellFlight(DUMMY_AUTH_TOKEN, navitaireSellFlightRequest);
        assertNotNull(response);
    }

    @Test
    public void getBooking_Test() {

        NavitaireBookingResponse navitaireBookingResponse = new NavitaireBookingResponse();
        navitaireBookingResponse.setData(new NavitaireBookingData());

        when(navitaireBookingProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(NavitaireBookingResponse.class))).thenReturn(Mono.just(navitaireBookingResponse));

        NavitaireBookingData response = navitaireClient.getBooking(DUMMY_AUTH_TOKEN);
        assertNotNull(response);
    }

    @Test
    public void getPassengers_Test() {

        RequestBodySpec reqBodySpec = mock(RequestBodySpec.class);

        NavitairePassengersResponse navitairePassengersResponse = new NavitairePassengersResponse();
        navitairePassengersResponse.setData(new HashMap<>());

        when(passengerGetProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.headers(any(Consumer.class))).thenReturn(reqBodySpec);
        when(reqBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(NavitairePassengersResponse.class))).thenReturn(Mono.just(navitairePassengersResponse));

        NavitairePassengersResponse response = navitaireClient.getPassengers(DUMMY_AUTH_TOKEN);
        assertNotNull(response);

    }

    @Test
    public void updatePassengerDetails_Test() {

        NavitaireUpdatePassengerRequest navitaireUpdatePassengerRequest = mock(NavitaireUpdatePassengerRequest.class);

        when(passengerUpdateDetailsProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));

        String response = navitaireClient.updatePassengerDetails(DUMMY_AUTH_TOKEN, DUMMY_PASSENGER_REF, navitaireUpdatePassengerRequest);
        assertNotNull(response);
    }

    @Test
    public void addPassengerAddress_Test() {

        PassengerAddress passengerAddress = mock(PassengerAddress.class);

        when(passengerAddAddressProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));

        String response = navitaireClient.addPassengerAddress(DUMMY_AUTH_TOKEN, DUMMY_PASSENGER_REF, passengerAddress);
        assertNotNull(response);

    }

    @Test
    public void putPassengerAddress_Test() {

        PassengerAddress passengerAddress = mock(PassengerAddress.class);

        when(passengerPutAddressProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));

        String response = navitaireClient.putPassengerAddress(DUMMY_AUTH_TOKEN, DUMMY_PASSENGER_REF, DUMMY_ADDRESS_REF, passengerAddress);
        assertNotNull(response);

    }

    @Test
    public void getAvailableSsrs_Test() {

        NavitaireSsrAvailabilityRequest navitaireSsrAvailabilityRequest = mock(NavitaireSsrAvailabilityRequest.class);

        NavitaireSsrAvailabilityResponse navitaireSsrAvailabilityResponse = new NavitaireSsrAvailabilityResponse();
        navitaireSsrAvailabilityResponse.setData(new NavitaireSsrAvailabilityJourneys());

        when(ssrAvailabilityProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(NavitaireSsrAvailabilityResponse.class))).thenReturn(Mono.just(navitaireSsrAvailabilityResponse));

        NavitaireSsrAvailabilityJourneys response = navitaireClient.getAvailableSsrs("", navitaireSsrAvailabilityRequest);
        assertNotNull(response);
    }

    @Test
    public void addPassengerTravelDocuments_Test() {

        PassengerTravelDocument passengerTravelDocument = mock(PassengerTravelDocument.class);

        when(passengerAddAddressProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));

        String response = navitaireClient.addPassengerTravelDocuments("", "", passengerTravelDocument);
        assertNotNull(response);

    }

    @Test
    public void updatePassengerTravelDocuments_Test() {

        PassengerTravelDocument passengerTravelDocument = mock(PassengerTravelDocument.class);

        when(passengerAddAddressProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));

        String response = navitaireClient.updatePassengerTravelDocuments("", "", "", passengerTravelDocument);
        assertNotNull(response);

    }

    @Test
    public void getSsrDetails_Test() {

        RequestBodySpec reqBodySpec = mock(RequestBodySpec.class);
        NavitaireGetAttachedSsrResponse navitaireGetAttachedSsrResponse = new NavitaireGetAttachedSsrResponse();

        when(bookingSsrsV1Properties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.headers(any(Consumer.class))).thenReturn(reqBodySpec);
        when(reqBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(NavitaireGetAttachedSsrResponse.class))).thenReturn(Mono.just(navitaireGetAttachedSsrResponse));

        NavitaireGetAttachedSsrResponse response = navitaireClient.getSsrDetails("");
        assertNotNull(response);
    }

    @Test
    public void commitBooking_Test() {
        CommitBookingRequest commitBookingRequest = new CommitBookingRequest();
        when(commitBookingProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));
        navitaireClient.commitBooking(commitBookingRequest, "", BookingStatus.PENDING);
    }

    @Test
    public void deletePayment_Test() {

        RequestBodySpec reqBodySpec = mock(RequestBodySpec.class);

        when(deletePaymentProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(reqBodySpec);
        when(((RequestHeadersSpec<?>) reqBodySpec).headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        navitaireClient.deletePayment("", "");
    }

    @Test
    public void getPassengerSeats_Test() {

        RequestBodySpec reqBodySpec = mock(RequestBodySpec.class);

        NavitaireGetSeatsResponse navitaireGetSeatsResponse = new NavitaireGetSeatsResponse();

        when(passengerGetSeatProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.headers(any(Consumer.class))).thenReturn(reqBodySpec);
        when(reqBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(NavitaireGetSeatsResponse.class))).thenReturn(Mono.just(navitaireGetSeatsResponse));

        NavitaireGetSeatsResponse response = navitaireClient.getPassengerSeats("", "");
        assertNotNull(response);

    }

    @Test
    public void sellPassengerSeat_Test() {

        Object reqHeadersSpec = mock(RequestHeadersSpec.class);

        NavitaireSellSeatRequest navitaireSellSeatRequest = new NavitaireSellSeatRequest("", "");

        when(passengerSellSeatProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn((RequestHeadersSpec) reqHeadersSpec);
        when(((RequestHeadersSpec<?>) reqHeadersSpec).retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));

        String response = navitaireClient.sellPassengerSeat("", "", "", navitaireSellSeatRequest);
        assertNotNull(response);
    }

    @Test
    public void deletePassengerSeat_Test() {

        RequestBodySpec reqBodySpec = mock(RequestBodySpec.class);

        when(passengerDeleteSeatProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(reqBodySpec);
        when(((RequestHeadersSpec<?>) reqBodySpec).headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));
        String response = navitaireClient.deletePassengerSeat("", "", "");
        assertNotNull(response);
    }


    @Test
    public void sellBundle_Test() {
        NavitaireBundleSellRequest navitaireBundleSellRequest = new NavitaireBundleSellRequest(new SellBundleRequest("Dummy-Bundle-Code", Collections.emptyList()));
        when(bundleSellProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));
        navitaireClient.sellBundle("", navitaireBundleSellRequest, "");
    }

    @Test
    public void retrieveBookingByRecordLocator_Test() {

        RequestBodySpec reqBodySpec = mock(RequestBodySpec.class);

        NavitaireBookingResponse navitaireBookingResponse = new NavitaireBookingResponse();
        navitaireBookingResponse.setData(new NavitaireBookingData());

        when(retrieveBookingProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.headers(any(Consumer.class))).thenReturn(reqBodySpec);
        when(reqBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(NavitaireBookingResponse.class))).thenReturn(Mono.just(navitaireBookingResponse));

        NavitaireBookingData response = navitaireClient.retrieveBookingByRecordLocator(DUMMY_AUTH_TOKEN, RECORD_LOCATOR);
        assertNotNull(response);
    }

    @Test
    @Disabled(value = "this passes only sometimes ... need to refactor into something that tests something real")
    public void adjustFee_Test() {
        NavitaireFeeAdjustmentRequest overrideFeesRequest = new NavitaireFeeAdjustmentRequest();
        when(navitaireFeeAdjustmentProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(eq(HttpMethod.PUT))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(DUMMY_URI)).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(), eq(NavitaireFeeAdjustmentRequest.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));
        navitaireClient.adjustFees(overrideFeesRequest, "token");
    }

    @Test
    public void adjustFare_Test() {
        NavitaireFareAdjustmentRequest navFareRequest = mock(NavitaireFareAdjustmentRequest.class);
        when(fareAdjustmentProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));
        navitaireClient.adjustFare(navFareRequest, "flight-ref", DUMMY_AUTH_TOKEN);
    }

    @Test
    public void deleteJourneyTest() {
        RequestBodySpec reqBodySpec = mock(RequestBodySpec.class);
        when(quoteCancelOrderProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(reqBodySpec);
        when(((RequestHeadersSpec<?>) reqBodySpec).headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));
        navitaireClient.deleteJourney("", "");
    }

    @Test
    public void saveModifiedOrderTest() {
        CommitBookingRequest commitBookingRequest = new CommitBookingRequest();
        when(commitCancelOrderProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));
        navitaireClient.saveModifiedOrder(commitBookingRequest, "");
    }

    @Test
    public void paymentRefundOrderTest() {
        PaymentRefundRequest paymentRefundRequest = new PaymentRefundRequest();
        when(paymentRefundProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(Mono.class), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));
        navitaireClient.paymentRefundOrder("", paymentRefundRequest, true, PaymentAction.REFUND);
    }

    @Test
    public void reapplySystemFees_Test() {
        RequestBodySpec reqBodySpec = mock(RequestBodySpec.class);
        when(bookingFeeProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(reqBodySpec);
        when(((RequestHeadersSpec<?>) reqBodySpec).headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));
        String response = navitaireClient.reapplySystemFees("", "");
        assertNotNull(response);
    }

    @Test
    public void createFee_Test() {
        NavitaireCreateFeeRequest navCreateFeeRequest = mock(NavitaireCreateFeeRequest.class);
        when(bookingFeeProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));
        navitaireClient.createFee(navCreateFeeRequest, DUMMY_AUTH_TOKEN);
    }

    @Test
    public void deleteByFlightRefTest() {
        RequestBodySpec reqBodySpec = mock(RequestBodySpec.class);
        when(deleteFlightProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(reqBodySpec);
        when(((RequestHeadersSpec<?>) reqBodySpec).headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));
        navitaireClient.deleteFlightByFlightRef("", "");
    }

    @Test
    public void getNavitairePassengerTypesTest() {
        List<NavitairePassengerTypeDetails> navitairePassengerTypeDetailsList =
                Collections.singletonList(new NavitairePassengerTypeDetails().setMaximumAge(18));
        NavitairePassengerTypeResponse navitairePassengerTypeResponse = new NavitairePassengerTypeResponse();
        navitairePassengerTypeResponse.setData(navitairePassengerTypeDetailsList);

        when(navitairePassengerTypeProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(NavitairePassengerTypeResponse.class))).thenReturn(Mono.just(navitairePassengerTypeResponse));

        List<NavitairePassengerTypeDetails> response = navitaireClient.getNavitairePassengerTypes(DUMMY_AUTH_TOKEN);
        assertNotNull(response);
        assertEquals(18, response.get(0).getMaximumAge());
    }

    @Test
    public void passengerDiscount_Test() {

        NavitairePassengerDiscountRequest navitairePassengerDiscountRequest = mock(NavitairePassengerDiscountRequest.class);

        when(passengerUpdateDetailsProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(), any(Class.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));

        String response = navitaireClient.passengerDiscount(DUMMY_AUTH_TOKEN, DUMMY_PASSENGER_REF, navitairePassengerDiscountRequest);
        assertNotNull(response);
    }

    @Test
    public void deletePnrFromDispositionQueue() {
        String bookingKey = "SC199X";
        Mono<Void> mockVoid = Mockito.mock(Mono.class);
        Object reqHeadersSpec = mock(RequestHeadersSpec.class);
        Map<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("bookingKey", bookingKey);
        DeleteDispositionPnrRequest deleteDispositionPnrRequest = new DeleteDispositionPnrRequest().setQueueCode("SKDMOV");

        when(deleteDispositionPnrProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(any(HttpMethod.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(DUMMY_URI, pathParams)).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(), any(Class.class))).thenReturn((RequestHeadersSpec) reqHeadersSpec);
        when(((RequestHeadersSpec<?>) reqHeadersSpec).headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Void.class)).thenReturn(mockVoid);
        when(mockVoid.doOnError(any())).thenReturn(mockVoid);

        assertDoesNotThrow(() -> navitaireClient.deletePnrFromDispositionQueue(DUMMY_AUTH_TOKEN, bookingKey, deleteDispositionPnrRequest));
    }

    @Test
    public void saveComment(){
        Comment comment = new Comment().setText("comment");
        String confirmationNumber = "A3HB3P";
        Object reqHeadersSpec = mock(RequestHeadersSpec.class);
        when(addCommentProperties.getEndpoint()).thenReturn(DUMMY_URI);
        when(webClient.method(HttpMethod.POST)).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(any(Function.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(), any(Class.class))).thenReturn((RequestHeadersSpec)reqHeadersSpec);
        when(((RequestHeadersSpec<?>) reqHeadersSpec).headers(any(Consumer.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just(RESPONSE_STRING));
        navitaireClient.saveComment(comment, DUMMY_AUTH_TOKEN, confirmationNumber);
        assertEquals("comment", comment.getText());
    }
}
