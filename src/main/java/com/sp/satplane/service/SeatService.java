package com.sp.satplane.service;

import java.math.BigDecimal;
import java.util.List;

import com.sp.satplane.model.Seat;

public interface SeatService {

  List<Seat> getSeatsOfPlane(Long planeId);

  BigDecimal getPlaneBaseTicketPrice(Long planeId);
}
