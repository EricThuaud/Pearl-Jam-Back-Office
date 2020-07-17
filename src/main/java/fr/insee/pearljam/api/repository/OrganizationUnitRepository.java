package fr.insee.pearljam.api.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.insee.pearljam.api.domain.OrganizationUnit;

public interface OrganizationUnitRepository extends JpaRepository<OrganizationUnit, String> {

	Optional<OrganizationUnit> findByIdIgnoreCase(String id);

	/**
	* This method retrieves ids of organizationUnit in param
	* 
	* @return List of all {@link String}
	*/
	@Query(value="SELECT id FROM organization_unit WHERE organization_unit_parent_id =?1", nativeQuery=true)
	List<String> findChildrenId(String orgUnitId);
	
	/**
	* This method retrieves organizationUnit of children organizationUnit in param
	* 
	* @return List of all {@link String}
	*/
	@Query("SELECT ou FROM OrganizationUnit ou WHERE ou.organizationUnitParent.id =?1")
	List<OrganizationUnit> findChildren(String orgUnitId);
}
