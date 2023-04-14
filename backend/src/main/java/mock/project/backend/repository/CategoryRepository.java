package mock.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mock.project.backend.entities.Categories;

@Repository(value="catRepo")
public interface CategoryRepository extends JpaRepository<Categories, Integer> {

}
