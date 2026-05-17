package com.jdc.demo.entity;

import java.time.LocalDate;

public record RegistrationId(
		LocalDate registerAt,
		int seqNumber) {

}
