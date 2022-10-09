package com.alquiler.nube.service;

import com.alquiler.nube.entities.Cloud;
import com.alquiler.nube.repository.CloudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CloudService {

    @Autowired
    private CloudRepository cloudRepository;

    public List<Cloud> getAll(){
        return cloudRepository.getAll();
    }
    public Optional<Cloud> getCloud(int id){
        return cloudRepository.getCloud(id);
    }
    public Cloud save(Cloud c){
        if(c.getId()==null){
            return cloudRepository.save(c);
        }else{
            Optional<Cloud> e = cloudRepository.getCloud(c.getId());
            if(e.isPresent()){
                return c;
            }else{
                return cloudRepository.save(c);
            }
        }
    }
    public Cloud update(Cloud p){
        if(p.getId()!=null){
            Optional<Cloud> q = cloudRepository.getCloud(p.getId());
            if(q.isPresent()){
                if(p.getName()!=null){
                    q.get().setName(p.getName());
                }
                if(p.getDescription()!=null){
                    q.get().setDescription(p.getDescription());
                }
                if(p.getYear()!=null){
                    q.get().setYear(p.getYear());
                }
                if(p.getCategory()!=null){
                    q.get().setCategory(p.getCategory());
                }
                
                if(p.getBrand() != null){
                q.get().setBrand(p.getBrand());
                }

                cloudRepository.save(q.get());
                return q.get();
            }else{
                return p;
            }
        }else{
            return p;
        }
    }
    public boolean delete(int id){
        boolean flag=false;
        Optional<Cloud>p= cloudRepository.getCloud(id);
        if(p.isPresent()){
            cloudRepository.delete(p.get());
            flag=true;
        }
        return flag;

    }


}
