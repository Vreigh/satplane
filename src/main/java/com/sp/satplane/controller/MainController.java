package com.sp.satplane.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.satplane.model.Ticket;
import com.sp.satplane.service.GetTicketsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/satplane")
@RequiredArgsConstructor
public class MainController {

  private final GetTicketsService getTicketsService;

  @GetMapping(path = "/user/{userId}/plane/{planeId}/tickets")
  public List<Ticket> getTickets(@PathVariable("userId") Long userId, @PathVariable("planeId") Long planeId) {
    return getTicketsService.getTickets(userId, planeId);
  }

}
