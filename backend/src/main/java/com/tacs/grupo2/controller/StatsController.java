package com.tacs.grupo2.controller;

import com.tacs.grupo2.dto.StatisticsDTO;
import com.tacs.grupo2.mapper.ApiResponse;
import com.tacs.grupo2.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {
    private final StatisticsService statisticsService;
    private final StatisticsService statsService;

    @GetMapping("/unique-users")
    public ResponseEntity<ApiResponse<Integer>> uniqueUsers(){

        ApiResponse<Integer> response = new ApiResponse<>();
        response.setMessage("Amount of unique users interactions ");
        response.setValue(statsService.getUniqueUsersCount());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hourly-sales")
    public ResponseEntity<ApiResponse<Double>> hourlySales(){
        ApiResponse<Double> response = new ApiResponse<>();
        response.setMessage("Amount of hourly sales");
        response.setValue(statsService.getHourlyRevenue());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/daily-sales")
    public ResponseEntity<ApiResponse<Double>> dailySales(){
        ApiResponse<Double> response = new ApiResponse<>();
        response.setMessage("Amount of daily sales");
        response.setValue(statsService.getDailyRevenue());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/weekly-sales")
    public ResponseEntity<ApiResponse<Double>> weeklySales(){
        ApiResponse<Double> response = new ApiResponse<>();
        response.setMessage("Amount of weekly sales");
        response.setValue(statsService.getWeeklyRevenue());
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<StatisticsDTO> getStatistics() {
        StatisticsDTO statisticsDTO = statisticsService.calculateStatistics();
        return ResponseEntity.ok(statisticsDTO);
    }
}
