package com.example.dailyjournal.controller;

import com.example.dailyjournal.dto.request.FitnessRequestDto;
import com.example.dailyjournal.dto.request.MovieRequestDto;
import com.example.dailyjournal.model.Fitness;
import com.example.dailyjournal.model.Movie;
import com.example.dailyjournal.service.FitnessService;
import com.example.dailyjournal.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fitness")
public class FitnessController {
    private final FitnessService fitnessService;

    public FitnessController(FitnessService fitnessService) {
        this.fitnessService = fitnessService;
    }

    @GetMapping()
    public List<Fitness> getFitness(@RequestParam Long userId){
        System.out.println(userId);

        return fitnessService.getAllByUserId(userId);
    }
    @PostMapping("/save")
    public ResponseEntity<String> addFitness(@Valid @RequestBody FitnessRequestDto fitnessRequestDto){
        System.out.println(fitnessRequestDto.getUserId());
        if(fitnessRequestDto.getGym() == null || fitnessRequestDto.getCalories() == null){
            return new ResponseEntity<>("You must complete all fields!", HttpStatus.BAD_REQUEST);
        }
        Fitness fitness = new Fitness(fitnessRequestDto.getGym(),
                fitnessRequestDto.getWater(),
                fitnessRequestDto.getSleep(), fitnessRequestDto.getCalories(),fitnessRequestDto.getUserId(), LocalDate.now());
//        if(fitnessService.existsByName(fitnessRequestDto.getName())){
//            return new ResponseEntity<>("Movie with this name already exist!", HttpStatus.BAD_REQUEST);
//        }
        fitnessService.save(fitness);
        return new ResponseEntity<>("Fitness entry saved succesfully", HttpStatus.CREATED);
    }
}
