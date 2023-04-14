package mock.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mock.project.backend.entities.Status;

@Repository(value="statusRepo")
public interface StatusRepository extends JpaRepository<Status, Integer> {
}
