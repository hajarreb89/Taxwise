package edu.b11.tp.tax.qs.api;


import edu.b11.tp.tax.qs.service.TaxCalculator;
import edu.b11.tp.tax.qs.persistence.ITaxBracketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//http://localhost:8083/
@RequestMapping("/api/cs")
public class QuebecRestService {
@Autowired
    private ITaxBracketDAO dao;

//http://localhost:8083/api/cs/canadaTax?authority=Canada&income=25000
    @RequestMapping("/canadaTax")
     public double getCanadaTax(@RequestParam Map<String,String> allParams) {

        double income;
        try {
            income = Double.parseDouble(allParams.get("income"));
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Income must be a decimal number");
        }
        String authority = allParams.get("authority");
        TaxCalculator service = new TaxCalculator(dao);
        return service.calculateTax(authority, income);
    }
}
