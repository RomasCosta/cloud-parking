/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author romar
 */
@Service
public class ParkingService {
    
   private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {//ao invés da anotação @Autowired, é indicado o construtor.
        this.parkingRepository = parkingRepository;
    }
    
    @Transactional(readOnly  = true, propagation = Propagation.SUPPORTS)
     public List<Parking> findAll() {
         return parkingRepository.findAll();
    }

    private static String getUUID() {return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional(readOnly  = true)
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() ->//expressao lambda
                    new ParkingNotFoundException(id));
    }

    @Transactional
    public Object create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now()); 
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }
    
    @Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setState(parkingCreate.getState());
        parking.setModel(parkingCreate.getModel());
        parking.setLicense(parkingCreate.getLicense());
        parkingRepository.save(parking);
        return parking;
    }

    @Transactional
    public Parking checkOut(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        parkingRepository.save(parking);
        return parking;
    }
}
