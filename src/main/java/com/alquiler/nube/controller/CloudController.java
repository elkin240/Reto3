package com.alquiler.nube.controller;

import com.alquiler.nube.entities.Cloud;
import com.alquiler.nube.service.CloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Cloud")
public class        CloudController {

    @Autowired
    private CloudService cloudService;

    @GetMapping("/all")
    public List<Cloud> getAll() {return cloudService.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cloud save(@RequestBody Cloud c) {
        return cloudService.save(c);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Cloud update(@RequestBody Cloud c) {
        return cloudService.update(c);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return cloudService.delete(id);
    }
}
