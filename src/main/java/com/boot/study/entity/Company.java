package com.boot.study.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //사업장 명
    private String businessName;
    //사업장업종코드
    private String businessCode;
    //사업자 업종 코드 명
    private String businessTypeCodeName;

    //사업자 등록 번호
    private String businessRegistrationNumber;

    //사업장 형태 구분 코드 ( 1 : 법인 / 2 : 개인 )
    private String codeForClassificationOfWorkplaceType;

    //사업장 등록 상태 ( 1 : 등록 / 2 : 탈퇴 )
    private String businessSiteRegistrationStatus;

    //사업장 주소 ( 도로명 )
    private String businessAddress;
    //가입자수
    private String numberOfSubscribers;
    //상실 가입자 수
    private String numberOfLostSubscribers;
    //신규 취득자 수
    private String numberOfNewAcquirers;

    @Builder
    public Company(Long id, String businessName, String businessCode, String businessTypeCodeName, String businessRegistrationNumber, String codeForClassificationOfWorkplaceType, String businessSiteRegistrationStatus, String businessAddress, String numberOfSubscribers, String numberOfLostSubscribers, String numberOfNewAcquirers) {
        this.id = id;
        this.businessName = businessName;
        this.businessCode = businessCode;
        this.businessTypeCodeName = businessTypeCodeName;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.codeForClassificationOfWorkplaceType = codeForClassificationOfWorkplaceType;
        this.businessSiteRegistrationStatus = businessSiteRegistrationStatus;
        this.businessAddress = businessAddress;
        this.numberOfSubscribers = numberOfSubscribers;
        this.numberOfLostSubscribers = numberOfLostSubscribers;
        this.numberOfNewAcquirers = numberOfNewAcquirers;
    }
}
