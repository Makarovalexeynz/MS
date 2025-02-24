package com.example.licensing_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.licensing_service.service.LicenseService;
import com.example.licensing_service.model.License;

import java.util.Locale;

@RestController
@RequestMapping(value="v1/organization/{organizationId}/license")
public class LicenceController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping(value="/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {

        License license = licenseService
                .getLicense(licenseId,organizationId);
        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<String> updateLicense(
            @PathVariable("organizationId")
            String organizationId,
            @RequestBody License request) {
        return ResponseEntity.ok(licenseService.updateLicense(request, organizationId));
    }

    @PostMapping
    public ResponseEntity<String> createLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request,
            @RequestHeader(value = "Accept-Language",required = false)
                    Locale locale)
    {
        return ResponseEntity.ok(licenseService.createLicense(request,
                organizationId, locale));
    }

    @DeleteMapping(value="/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId,
                organizationId));
    }
}
