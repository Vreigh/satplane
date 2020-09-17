package com.sp.satplane.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.sp.satplane.model.Ticket;
import com.sp.satplane.sat.service.clausegenerator.ClauseGeneratorType;
import com.sp.satplane.service.GetTicketsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/satplane")
@RequiredArgsConstructor
public class MainController {

  private final GetTicketsService getTicketsService;

  @GetMapping(path = "/user/{userId}/plane/{planeId}/tickets")
  public List<Ticket> getTickets(@PathVariable("userId") Long userId,
                                 @PathVariable("planeId") Long planeId,
                                 @RequestParam(value = "clauseGenerator", required = false, defaultValue = "SIMPLE")
                                   ClauseGeneratorType clauseGeneratorName) {
    return getTicketsService.getTickets(userId, planeId, clauseGeneratorName);
  }

}
