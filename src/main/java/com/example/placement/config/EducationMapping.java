package com.example.placement.config;

import com.example.placement.entity.types.BranchType;
import com.example.placement.entity.types.CourseType;
import com.example.placement.entity.types.DomainType;

import java.util.*;

//static mapping : Branch -> Course -> Domain
public class EducationMapping {
    //ensures only valid courses show for each branch
    //static - belongs to a class not instance - can call directly - EducationMappings.getCoursesForBranch(branch)
    //Hashmap - best for mapping - O(1) - for get or put
    private static final Map<BranchType, List<CourseType>> branchToCourses = new HashMap<>();
    private static final Map<CourseType, List<DomainType>> courseToDomains = new HashMap<>();

    //static block initalizes all the mappings ONCE when the class is loaded
    static {
        branchToCourses.put(BranchType.ENGINEERING_TECHNOLOGY,
                Arrays.asList(CourseType.BTECH, CourseType.BE, CourseType.MTECH, CourseType.ME));
        branchToCourses.put(BranchType.MANAGEMENT_BUSINESS,
                Arrays.asList(CourseType.BBA, CourseType.MBA, CourseType.BMS, CourseType.PGDM));
        branchToCourses.put(BranchType.COMPUTER_APPLICATIONS_IT,
                Arrays.asList(CourseType.BCA, CourseType.MCA));

        branchToCourses.put(BranchType.LAW,
                Arrays.asList(CourseType.LLB, CourseType.BA_LLB, CourseType.BBA_LLB, CourseType.LLM));

        branchToCourses.put(BranchType.SCIENCE,
                Arrays.asList(CourseType.BSC, CourseType.MSC, CourseType.BSC_IT, CourseType.BSC_CS, CourseType.BSC_BIOTECH));

        branchToCourses.put(BranchType.ARCHITECTURE_DESIGN,
                Arrays.asList(CourseType.BARCH, CourseType.MARCH, CourseType.BDES, CourseType.MDES));

        branchToCourses.put(BranchType.PROFESSIONAL_SPECIALIZED,
                Arrays.asList(CourseType.PG_DIPLOMA, CourseType.INTEGRATED_COURSE));

        // ===== Course → Domains =====

        // ===== ENGINEERING & TECHNOLOGY =====
        courseToDomains.put(CourseType.BTECH, Arrays.asList(
                DomainType.SOFTWARE_DEVELOPMENT, DomainType.DATA_SCIENCE, DomainType.ARTIFICIAL_INTELLIGENCE,
                DomainType.CYBER_SECURITY, DomainType.CLOUD_COMPUTING, DomainType.DEVOPS,
                DomainType.EMBEDDED_SYSTEMS, DomainType.NETWORK_ENGINEERING, DomainType.BLOCKCHAIN, DomainType.IOT));
        //reuse the same domain mapping for other engineering courses - be has the same exact domain as BTech
        courseToDomains.put(CourseType.BE, courseToDomains.get(CourseType.BTECH));
        courseToDomains.put(CourseType.MTECH, courseToDomains.get(CourseType.BTECH));
        courseToDomains.put(CourseType.ME, courseToDomains.get(CourseType.BTECH));

        // ===== MANAGEMENT & BUSINESS =====
        courseToDomains.put(CourseType.BBA, Arrays.asList(
                DomainType.MARKETING, DomainType.FINANCE, DomainType.HUMAN_RESOURCES, DomainType.OPERATIONS,
                DomainType.BUSINESS_ANALYTICS, DomainType.PRODUCT_MANAGEMENT, DomainType.SALES, DomainType.ENTREPRENEURSHIP));
        courseToDomains.put(CourseType.MBA, courseToDomains.get(CourseType.BBA));
        courseToDomains.put(CourseType.BMS, courseToDomains.get(CourseType.BBA));
        courseToDomains.put(CourseType.PGDM, courseToDomains.get(CourseType.BBA));

        // ===== COMPUTER APPLICATIONS =====
        courseToDomains.put(CourseType.BCA, Arrays.asList(
                DomainType.WEB_DEVELOPMENT, DomainType.MOBILE_DEVELOPMENT, DomainType.DATABASE_MANAGEMENT,
                DomainType.SYSTEM_DESIGN, DomainType.SOFTWARE_TESTING, DomainType.UI_UX_DESIGN));
        courseToDomains.put(CourseType.MCA, courseToDomains.get(CourseType.BCA));

        // ===== LAW =====
        courseToDomains.put(CourseType.LLB, Arrays.asList(
                DomainType.CORPORATE_LAW, DomainType.CRIMINAL_LAW, DomainType.INTELLECTUAL_PROPERTY_LAW,
                DomainType.CYBER_LAW, DomainType.INTERNATIONAL_LAW, DomainType.CONSTITUTIONAL_LAW));
        courseToDomains.put(CourseType.BA_LLB, courseToDomains.get(CourseType.LLB));
        courseToDomains.put(CourseType.BBA_LLB, courseToDomains.get(CourseType.LLB));
        courseToDomains.put(CourseType.LLM, courseToDomains.get(CourseType.LLB));

        // ===== SCIENCE =====
        courseToDomains.put(CourseType.BSC, Arrays.asList(
                DomainType.PHYSICS_RESEARCH, DomainType.CHEMISTRY_RESEARCH, DomainType.MATHEMATICS,
                DomainType.ENVIRONMENTAL_SCIENCE, DomainType.BIOTECHNOLOGY, DomainType.MICROBIOLOGY));
        courseToDomains.put(CourseType.MSC, courseToDomains.get(CourseType.BSC));
        courseToDomains.put(CourseType.BSC_IT, Arrays.asList(DomainType.SOFTWARE_DEVELOPMENT, DomainType.DATABASE_MANAGEMENT));
        courseToDomains.put(CourseType.BSC_CS, courseToDomains.get(CourseType.BSC_IT));
        courseToDomains.put(CourseType.BSC_BIOTECH, Arrays.asList(DomainType.BIOTECHNOLOGY, DomainType.MICROBIOLOGY));

        // ===== ARCHITECTURE & DESIGN =====
        courseToDomains.put(CourseType.BARCH, Arrays.asList(DomainType.ARCHITECTURE_DESIGN, DomainType.URBAN_PLANNING));
        courseToDomains.put(CourseType.MARCH, courseToDomains.get(CourseType.BARCH));
        courseToDomains.put(CourseType.BDES, Arrays.asList(DomainType.INTERIOR_DESIGN, DomainType.INDUSTRIAL_DESIGN,
                DomainType.GRAPHIC_DESIGN, DomainType.PRODUCT_DESIGN));
        courseToDomains.put(CourseType.MDES, courseToDomains.get(CourseType.BDES));

        // ===== PROFESSIONAL / SPECIALIZED =====
        courseToDomains.put(CourseType.PG_DIPLOMA, Arrays.asList(DomainType.RESEARCH, DomainType.CONSULTING,
                DomainType.EDUCATION, DomainType.PUBLIC_POLICY, DomainType.NGO_DEVELOPMENT, DomainType.FREELANCING));
        courseToDomains.put(CourseType.INTEGRATED_COURSE, courseToDomains.get(CourseType.PG_DIPLOMA));

        // ===== ARTS =====
        courseToDomains.put(CourseType.BA, Arrays.asList(DomainType.CONTENT_WRITING, DomainType.JOURNALISM,
                DomainType.PSYCHOLOGY, DomainType.SOCIOLOGY, DomainType.POLITICAL_SCIENCE, DomainType.LITERATURE));
        courseToDomains.put(CourseType.MA, courseToDomains.get(CourseType.BA));

        // ===== COMMERCE =====
        courseToDomains.put(CourseType.BCOM, Arrays.asList(DomainType.ACCOUNTING, DomainType.TAXATION, DomainType.AUDITING,
                DomainType.INVESTMENT_BANKING, DomainType.ECONOMICS, DomainType.BUSINESS_LAW));
        courseToDomains.put(CourseType.MCOM, courseToDomains.get(CourseType.BCOM));    }

    //public method to get valid courses for branch
    public static List<CourseType> getCoursesForBranch(BranchType branch){
        //getOrDefault - safely returns empty list is invalid input
        return branchToCourses.getOrDefault(branch, Collections.emptyList());
    }

    public static List<DomainType> getDomainForCourse(CourseType course){
        return courseToDomains.getOrDefault(course, Collections.emptyList());
    }
}



























