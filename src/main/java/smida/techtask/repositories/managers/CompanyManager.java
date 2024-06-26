package smida.techtask.repositories.managers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import smida.techtask.entities.Company;
import smida.techtask.exceptions.notfound.CompanyNotFoundException;
import smida.techtask.mappers.CompanyMapper;
import smida.techtask.repositories.CompanyRepository;

import java.util.List;
import java.util.UUID;

/**
 * The CompanyManager class manages operations related to Company entity.
 */
@Component
@RequiredArgsConstructor
public class CompanyManager {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    /**
     * Retrieves all companies.
     *
     * @return a list of all companies
     */
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    /**
     * Retrieves a company by its ID.
     *
     * @param id the UUID of the company to retrieve
     * @return the company with the given ID
     * @throws CompanyNotFoundException if no company is found with the given ID
     */
    public Company getById(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    /**
     * Saves a new company.
     *
     * @param company the company entity to save
     * @return the saved company entity
     */
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    /**
     * Updates an existing company.
     *
     * @param id      the UUID of the company to update
     * @param company the company entity with updated data
     * @return the updated company entity
     * @throws CompanyNotFoundException if no company is found with the given ID
     */
    @Transactional
    public Company update(UUID id, Company company) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        companyMapper.update(company, existingCompany);
        return existingCompany;
    }

    /**
     * Deletes a company by its ID.
     *
     * @param id the UUID of the company to delete
     * @throws CompanyNotFoundException if no company is found with the given ID
     */
    public void deleteById(UUID id) {
        checkPresenceById(id);
        companyRepository.deleteById(id);
    }

    /**
     * Checks if a company exists by its ID.
     *
     * @param id the UUID of the company to check
     * @throws CompanyNotFoundException if no company is found with the given ID
     */
    private void checkPresenceById(UUID id) {
        if (!companyRepository.existsById(id)) {
            throw new CompanyNotFoundException(id);
        }
    }

}
