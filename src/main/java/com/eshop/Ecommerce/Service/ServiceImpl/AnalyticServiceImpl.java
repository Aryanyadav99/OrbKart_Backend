package com.eshop.Ecommerce.Service.ServiceImpl;

import com.eshop.Ecommerce.Payload.AnalyticsResponse;
import com.eshop.Ecommerce.Repositories.OrderRepo;
import com.eshop.Ecommerce.Repositories.ProductRepo;
import com.eshop.Ecommerce.Service.AnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticServiceImpl implements AnalyticService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    OrderRepo orderRepo;

    @Override
    public AnalyticsResponse getAnalyticsData() {
        AnalyticsResponse response= new AnalyticsResponse();

        long totalOrder=orderRepo.count();
        long totalProduct=productRepo.count();
        Double totalRevenue=orderRepo.getTotalRevenue();
        response.setProductCount(""+totalProduct);
        response.setTotalOrders(""+totalOrder);
        response.setTotalRevenue(""+totalRevenue);
        return response;
    }
}
