package mock.project.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mock.project.backend.entities.Products;
import mock.project.backend.entities.Status;
import mock.project.backend.repository.StatusRepository;
import mock.project.backend.request.ProductDTO;
import mock.project.backend.request.StatusDTO;

@Service
@Transactional
public class StatusService {
	@Autowired
	private StatusRepository statusRepo;


	@Autowired
	private ModelMapper modelMap;
	
	public List<StatusDTO> findAllStatus( ) {
		List<Status> status = statusRepo.findAll();
		List<StatusDTO> statusDTOs = new ArrayList<>();
		for (Status s : status) {
			StatusDTO statusDTO = modelMap.map(s, StatusDTO.class);
			statusDTOs.add(statusDTO);
		}
		return statusDTOs;
	}
	
	public Status findStatusById(Integer id) {
		return statusRepo.findById(id).get();
	}

}
