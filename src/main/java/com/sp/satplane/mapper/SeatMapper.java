package com.sp.satplane.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sp.satplane.model.Seat;
import com.sp.satplane.sat.model.SatSeat;
import com.sp.satplane.sat.model.SatSeatVariable;

@Mapper
public abstract class SeatMapper {

  public static final SeatMapper INSTANCE = Mappers.getMapper(SeatMapper.class);

  public abstract List<SatSeat> map(List<Seat> seats);

  public SatSeat mapSeat(Seat seat) {
    List<SatSeatVariable> variables = new ArrayList<>();
    addIfTrue(variables, SatSeatVariable.BY_WINDOW, seat.getByWindow());
    addIfTrue(variables, SatSeatVariable.BY_EMERGENCY_EXIT, seat.getByEmergencyExit());
    addIfTrue(variables, SatSeatVariable.BY_WING, seat.getByWing());
    addIfTrue(variables, SatSeatVariable.MORE_LEG_SPACE, seat.getMoreLegSpace());
    addIfTrue(variables, SatSeatVariable.NEAR_SEAT_TAKEN, seat.getNearSeatTaken());
    addIfTrue(variables, SatSeatVariable.BY_TOILET, seat.getByToilet());
    addIfTrue(variables, SatSeatVariable.BY_KITCHEN, seat.getByKitchen());
    addIfTrue(variables, SatSeatVariable.IS_LOUD, seat.getIsLoud());
    addIfTrue(variables, SatSeatVariable.WITH_TURBULENCE, seat.getWithTurbulence());
    addIfTrue(variables, SatSeatVariable.BY_AISLE, seat.getByAisle());
    addIfTrue(variables, SatSeatVariable.IS_SAFEST, seat.getIsSafest());
    return new SatSeat(variables); // not most efficient but more readable
  }

  protected void addIfTrue(List<SatSeatVariable> variables, SatSeatVariable variable, boolean variableStatus) {
    if (variableStatus) {
      variables.add(variable);
    }
  }
}
