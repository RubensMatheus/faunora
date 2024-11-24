package br.com.faunora.services;

import br.com.faunora.domain.dto.PetRecordDto;
import br.com.faunora.domain.models.PetModel;
import br.com.faunora.domain.models.UserModel;
import br.com.faunora.infra.exceptions.NenhumPetEncontradoException;
import br.com.faunora.infra.exceptions.PetNaoEncontradoException;
import br.com.faunora.repositories.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Transactional
    public PetModel savePet(PetRecordDto petRecordDto) {
        PetModel petModel = new PetModel();
        BeanUtils.copyProperties(petRecordDto, petModel);

        /*!!provisório até fazer o security!!*/
        petModel.setTutor(new UserModel());

        return petRepository.save(petModel);
    }

    public PetModel findById(UUID id) {
        return petRepository.findById(id)
                .orElseThrow(PetNaoEncontradoException::new);
    }

    public List<PetModel> findAll() {
        List<PetModel> petModels = petRepository.findAll();

        if (petModels.isEmpty()) {
            throw new NenhumPetEncontradoException();
        }

        return petModels;
    }

    @Transactional
    public PetModel updatePet(UUID id, PetRecordDto petRecordDto) {
        PetModel petModel = petRepository.findById(id)
                .orElseThrow(PetNaoEncontradoException::new);

        BeanUtils.copyProperties(petRecordDto, petModel);
        return petRepository.save(petModel);
    }

    @Transactional
    public void deleteById(UUID id) {
        PetModel petModel = petRepository.findById(id)
                .orElseThrow(PetNaoEncontradoException::new);

        petRepository.delete(petModel);
    }
}

