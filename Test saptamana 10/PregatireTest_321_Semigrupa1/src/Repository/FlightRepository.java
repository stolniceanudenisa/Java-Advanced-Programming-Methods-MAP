package Repository;

import Domain.FlightInstrument;
import Domain.HardwareInstrument;
import Domain.SoftwareInstrument;

import java.util.ArrayList;
import java.util.List;

public class FlightRepository {
    List<FlightInstrument> flightInstrumentList = new ArrayList<>();

    public void add(FlightInstrument flightInstrument)
    {
        flightInstrumentList.add(flightInstrument);
    }

    public List<FlightInstrument> getAllEntities() {
        return flightInstrumentList;
    }
}
