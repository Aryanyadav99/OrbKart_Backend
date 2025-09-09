package com.eshop.Ecommerce.Controller;

import com.eshop.Ecommerce.Payload.AnalyticsResponse;
import com.eshop.Ecommerce.Service.AnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AnalyticsController {

    @Autowired
    AnalyticService  analyticService;

    @GetMapping("/admin/app/dashboard")
    public ResponseEntity<AnalyticsResponse> getAnalytics(){
        AnalyticsResponse repsonse= analyticService.getAnalyticsData();
        return ResponseEntity.ok(repsonse);
    }
}
