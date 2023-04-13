package com.uber.driverdataregistry.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uber.driverdataregistry.domain.driver.*;

public final class TestUtils {
	private TestUtils() {}

	public static DriverAggregateRoot createTestDriver(String driverIdStr, String firstname, String lastname, Date birthday, String vehicleName, String licensePlateNumber, String registeredRegisteredCity, String email, String phoneNumber) {
		DriverId driverId = new DriverId(driverIdStr);
		Vehicle currentVehicle = new Vehicle(vehicleName, licensePlateNumber, registeredRegisteredCity);
		DriverProfileEntity driverProfile = new DriverProfileEntity(firstname, lastname, birthday, currentVehicle, email, phoneNumber, new Address());
		return new DriverAggregateRoot(driverId, driverProfile);
	}

	public static String createISO8601Timestamp(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return dateFormat.format(date);
	}

	public static Date createDate(int day, int month, int year) {
		GregorianCalendar calendar =  new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		calendar.set(year, month, day, 0, 0, 0);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTime();
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}