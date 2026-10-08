package it.studyhub.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.studyhub.backend.dto.MaterialRequest;
import it.studyhub.backend.dto.MaterialResponse;
import it.studyhub.backend.service.MaterialService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/materials")
public class MaterialController {
    private final MaterialService materialService;
    
    public MaterialController(MaterialService materialService){
        this.materialService = materialService;
    }

    @GetMapping 
    public List<MaterialResponse> getAllMaterials(){
        return  materialService.getAllMaterials();
    }

    @GetMapping("/{id}")
    public MaterialResponse getMaterialById(@PathVariable  Long id){
        return materialService.getMaterialById(id);
    }

    @PostMapping 
    public MaterialResponse createMaterial(@Valid @RequestBody MaterialRequest request){
        return materialService.createMaterial(request);
    }

    @PutMapping("/{id}")
    public MaterialResponse updateMaterial(@PathVariable Long id, @Valid @RequestBody MaterialRequest request){
        return materialService.updateMaterial(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable Long id){
        materialService.deleteMaterial(id);
    }
}
