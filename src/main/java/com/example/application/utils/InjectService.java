package com.example.application.utils;

import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.StellenanzeigenDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * HilfsKompontente um per Dependency-Injection in Spring das StellenanzeigenDTO zu übergeben.
 *
 * @author hh
 * @since 24.05.2023
 */

@Component
public class InjectService{
    private StellenanzeigenDTO stellenanzeige;
    private boolean addJob;

    private Filter filter;


    public InjectService(){
        filter = new Filter();
    }


    public StellenanzeigenDTO getStellenanzeige(){
        return stellenanzeige;
    }
    public void setStellenanzeige(StellenanzeigenDTO stellenanzeige){
        this.stellenanzeige = stellenanzeige;
    }



    public boolean getJobHinzufuegen(){
        return addJob;
    }
    public void setJobHinzufuegen(boolean addJob){
        this.addJob = addJob;
    }


    public Filter getFilter(){
        return filter;
    }

    public void setFilter(String filterType, String filterValue, List<KeywordDTO> keywords, boolean own, boolean reserved){
        filter.setFilterType(filterType);
        filter.setFilterValue(filterValue);
        filter.setKeywords(keywords);
        filter.setOwn(own);
        filter.setReserved(reserved);
    }


    public class Filter{

        private String filterType;
        private String filterValue;
        private List<KeywordDTO> keywords;
        private boolean own;

        private boolean reserved;

        public Filter(){
            filterType = null;
            filterValue = null;
            own = false;
            reserved = false;
            keywords = new ArrayList<>();
        }

        public String getFilterType() {
            return filterType;
        }

        public void setFilterType(String filterType) {
            this.filterType = filterType;
        }

        public String getFilterValue() {
            return filterValue;
        }

        public void setFilterValue(String filterValue) {
            this.filterValue = filterValue;
        }

        public boolean getOwn(){return own;}

        public void setOwn(boolean own){this.own = own;}

        public boolean getReserved(){return reserved;}

        public void setReserved(boolean reserved){this.reserved = reserved;}

        public List<KeywordDTO> getKeywords() {
            return keywords;
        }

        public void setKeywords(List<KeywordDTO> keywords) {
            this.keywords = keywords;
        }
    }
}
