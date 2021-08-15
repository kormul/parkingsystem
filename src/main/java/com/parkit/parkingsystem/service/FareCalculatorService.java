package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket, int count){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        long inTime = ticket.getInTime().getTime();
        long outTime = ticket.getOutTime().getTime();
        double priceRate = 1.0;
     
        //TODO: Some tests are failing here. Need to check if this logic is correct
        double duration = (outTime - inTime-1800000.0)/3600000.0;
        duration = duration <0 ? 0 : duration;
        if(count>3)
        	priceRate = 0.95;
        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR * priceRate);
                break;
            }
            case BIKE: {
                ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR * priceRate);
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
    }
}