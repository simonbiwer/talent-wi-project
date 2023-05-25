package com.example.application.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author tb 24.05.23
 * @since 24.05.23
 * Entität zum speichern der Jobs
 */


@Entity
@Table(name = "stellenanzeige", schema = "talent", catalog = "wi_projekt_mahbobi_sose2023")
public class Stellenanzeige {
    @Id
    @SequenceGenerator(name = "stellenanzeige_stellenanzeigeid_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobid")
    private int jobid;
    @Basic
    @Column(name = "titel")

    private String titel;
    @Basic
    @Column(name = "beschreibung")
    private String beschreibung;
    @Basic
    @Column(name = "url")
    private String url;
    @Basic
    @Column(name = "technologien")
    private String technologien;
    @Basic
    @Column(name = "unternehmen")
    private String unternehmen;
    @Basic
    @Column(name = "projektdauer")
    private String projektdauer;
    // Datum vielleicht als Date Value speichern
    @Basic
    @Column(name = "startdatum")
    private String startdatum;
    @Basic
    @Column(name = "qualifikation")
    private String qualifikation;

    //TODO: Reserved in Datenbank einfügen

    @ManyToOne //Fremdschlüssel userid von Entität user
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    private User userid;

    @ManyToMany
    @JoinTable(schema = "talent", name = "jobHatKeyword", joinColumns = @JoinColumn(name = "jobid"), inverseJoinColumns = @JoinColumn(name = "keywordid"))
    Set<Keyword> zugehoerigerKey;

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String name) {
        this.titel = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTechnologien() {
        return technologien;
    }

    public void setTechnologien(String technologien) {
        this.technologien = technologien;
    }

    public String getUnternehmen() {
        return unternehmen;
    }

    public void setUnternehmen(String unternehmen) {
        this.unternehmen = unternehmen;
    }

    public String getProjektdauer() {
        return projektdauer;
    }

    public void setProjektdauer(String projektdauer) {
        this.projektdauer = projektdauer;
    }

    public String getStartdatum() {
        return startdatum;
    }

    public void setStartdatum(String startdatum) {
        this.startdatum = startdatum;
    }

    public String getQualifikation() {
        return qualifikation;
    }

    public void setQualifikation(String qualifikation) {
        this.qualifikation = qualifikation;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User user) {
        this.userid = user;
    }

    public Set<Keyword> getKeywords() {
        return zugehoerigerKey;
    }

    public void addKeywords(List<Keyword> keywords) {
        zugehoerigerKey = new HashSet<>();
        zugehoerigerKey.addAll(keywords);
    }


    @Override
    public boolean equals(Object o) {   //prüft ob Entität schon vorhanden
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stellenanzeige job = (Stellenanzeige) o;
        return jobid == job.jobid && Objects.equals(job.titel, titel) == false && Objects.equals(beschreibung, job.beschreibung) && Objects.equals(url, job.url) && Objects.equals(technologien, job.technologien) && Objects.equals(unternehmen, job.unternehmen) && Objects.equals(projektdauer, job.projektdauer) && Objects.equals(startdatum, job.startdatum) && Objects.equals(qualifikation, job.qualifikation) && Objects.equals(jobid, job.jobid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobid, titel, beschreibung, url, technologien, unternehmen, projektdauer, startdatum, qualifikation);
    }
}
