package com.sp.satplane.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.satplane.service.GetDiscomfortRationsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/satplane")
@RequiredArgsConstructor
public class MainController {

  private final GetDiscomfortRationsService getDiscomfortRationsService;

  @GetMapping(path = "/user/{userId}/plane/{planeId}")
  public List<Double> getDiscomfortRatios(@PathVariable("userId") Long userId, @PathVariable("planeId") Long planeId) {
    return getDiscomfortRationsService.getDiscomfortRatios(userId, planeId);
  }

}
