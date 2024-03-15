package org.example.domain;


public class HardwareFactory implements IEntityFactory<HardwareInstrument> {


    @Override
    public HardwareInstrument createEntity(String line)  {
        String[] parts = line.split(",");
        String code = parts[0];
        boolean maintenance = Boolean.parseBoolean(parts[1]);
        String tip_de_masuratoare = parts[2];
        return new HardwareInstrument(code, maintenance, tip_de_masuratoare);
    }

    @Override
    public String createString(HardwareInstrument entity) {
        return entity.getCode() + "," + entity.getMaintenance() + "," + entity.getTipDeMasuratoare() + "," + entity.getPrice();
    }
}