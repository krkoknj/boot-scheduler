package com.boot.study.mapper;

import com.boot.study.entity.Company;
import com.boot.study.mapper.provider.CompanyProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyMapper {
    //    @Select("SELECT * FROM company")
    @SelectProvider(method = "findAll", type = CompanyProvider.class)
    List<Company> selectAll();

    @Insert("INSERT INTO " +
            "company (business_address, business_code, business_name, business_registration_number, business_site_registration_status, business_type_code_name, code_for_classification_of_workplace_type, number_of_lost_subscribers, number_of_new_acquirers, number_of_subscribers) "
            + "VALUES (#{company.businessAddress},#{company.businessCode}, #{company.businessName}, #{company.businessRegistrationNumber}, #{company.businessSiteRegistrationStatus}, #{company.businessTypeCodeName}, #{company.codeForClassificationOfWorkplaceType}, #{company.numberOfLostSubscribers}, #{company.numberOfNewAcquirers}, #{company.numberOfSubscribers})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(@Param("company") final Company company);

    String getCurrentDateTime();
}
