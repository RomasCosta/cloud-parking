/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package one.digitalinnovation.parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.parking.controller.mapper.ParkingMapper;
import one.digitalinnovation.parking.controller.dto.ParkingDTO;
import java.util.List;
import lombok.var;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author romar
 */
@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {
    
    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }
    //injeção de dependências por construtor
    //as versões novas do spring recomendam utilizar construtor a Autowired para evitar problemas
    
    @GetMapping
    @ApiOperation("Find all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList =  parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        Parking parking =  parkingService.findById(id);
        if(parking == null) {
            return ResponseEntity.notFound().build();
        }
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
        
    }
    
     @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        parkingService.delete(id);
        return ResponseEntity.notFound().build();
        }
    
    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
       var parkingCreate =  parkingMapper.toParkingCreate(dto);
       var parking = parkingService.create(parkingCreate);
       var result = parkingMapper.toParkingDTO((Parking) parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
        
    }
    
     @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto) {
       var parkingCreate =  parkingMapper.toParkingCreate(dto);
       var parking = parkingService.update(id, parkingCreate);
       var result = parkingMapper.toParkingDTO((Parking) parking);
        return ResponseEntity.status(HttpStatus.OK).body(result);
        
    }
    
    @PostMapping("/{id}/exit")
    public ResponseEntity<ParkingDTO> checkOut(@PathVariable String id) {
       Parking parking = parkingService.checkOut(id);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
        
    }
}
