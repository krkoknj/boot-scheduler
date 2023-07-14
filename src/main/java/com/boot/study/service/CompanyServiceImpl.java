package com.boot.study.service;

import com.boot.study.entity.Company;
import com.boot.study.mapper.CompanyMapper;
import com.boot.study.repostitory.CompanyRepository;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;

    private final CompanyRepository companyRepository;

    @Override
    public Integer insertData(String data) {
        JSONObject jsonObject = new JSONObject(data);
        JSONArray jsonArray = (JSONArray) jsonObject.get("data");

        List<Company> list = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            System.out.println("i = " + i);
            JSONObject arrayData = jsonArray.getJSONObject(i);
            list = new ArrayList<>();
            String businessName = String.valueOf(arrayData.get("사업장명"));
            String businessCode = String.valueOf(arrayData.get("사업장업종코드"));
            String businessTypeCodeName = String.valueOf(arrayData.get("사업장업종코드명"));
            String businessRegistrationNumber = String.valueOf(arrayData.get("사업자등록번호"));
            String codeForClassificationOfWorkplaceType = String.valueOf(arrayData.get("사업장형태구분코드 1 법인 2 개인"));
            String businessSiteRegistrationStatus = String.valueOf(arrayData.get("사업장가입상태코드 1 등록 2 탈퇴"));
            String businessAddress = String.valueOf(arrayData.get("사업장도로명상세주소"));
            String numberOfSubscribers = String.valueOf(arrayData.get("가입자수"));
            String numberOfLostSubscribers = String.valueOf(arrayData.get("상실가입자수"));
            String numberOfNewAcquirers = String.valueOf(arrayData.get("신규취득자수"));

            Company company = Company.builder()
                    .businessName(businessName)
                    .businessCode(businessCode)
                    .businessTypeCodeName(businessTypeCodeName)
                    .businessRegistrationNumber(businessRegistrationNumber)
                    .codeForClassificationOfWorkplaceType(codeForClassificationOfWorkplaceType)
                    .businessSiteRegistrationStatus(businessSiteRegistrationStatus)
                    .businessAddress(businessAddress)
                    .numberOfSubscribers(numberOfSubscribers)
                    .numberOfLostSubscribers(numberOfLostSubscribers)
                    .numberOfNewAcquirers(numberOfNewAcquirers)
                    .build();
//            companyMapper.save(company);
        }
        String now = companyMapper.getCurrentDateTime();
        List<Company> companies = companyMapper.selectAll();
        List<Company> all = companyRepository.findAll();
        System.out.println("now = " + now);
        System.out.println("companies = " + companies);
        System.out.println("all = " + all);
        return null;
    }
}
