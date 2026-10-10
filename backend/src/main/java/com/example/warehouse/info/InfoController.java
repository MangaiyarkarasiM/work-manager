package com.example.warehouse.info;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InfoController {

    @GetMapping(path = "/info", produces = "application/json")
    public InfoResponseDTO getInfo() {
        return new InfoResponseDTO(
                "Warehouse Work Manager",
                "Hello from Warehouse Work Manager application"
        );
    }
}
